// AI Chat component
import React, { Component } from 'react';
import { useState } from 'react';
// see https://github.com/chatscope/chat-ui-kit-react for documentation
import '@chatscope/chat-ui-kit-styles/dist/default/styles.min.css';
import {
    MainContainer,
    ChatContainer,
    MessageList,
    Message,
    MessageInput,
    TypingIndicator
} from "@chatscope/chat-ui-kit-react";

// Functional component for Chat
export default function ChatComponent() {
    // State for the current user input
    const [input, setInput] = useState('');

    // State for the conversation history
    const [conversation, setConversation] = useState([
        { 
            message: "Hello, how can I help you today?",
            sender: "ChatGPT"
        }
    ]);

    // State to indicate if the AI is "typing" or processing a response
    const [isLoading, setIsLoading] = useState(false);

    // Handler for input field changes
    const handleInputChange = (e) => {
        setInput(e.target.value); // Update input state with new value
    };

    // Handler for submitting the user input
    const handleSubmit = async () => {
        const userInput = input.trim();
        if (!userInput) return; // Ignore empty input

        // Create a new message object for user input
        const newMessage = {
            message: userInput,
            sender: "user",
            direction: "outgoing" // Message direction for styling
        };

        // Update conversation state with new user message
        setConversation([...conversation, newMessage]);

        // Set loading state to true while processing the response
        setIsLoading(true);

        // Placeholder for API response
        const aiResponse = 'Response from GPT...'; 

        // Update conversation state with AI response
        setConversation(convo => [...convo, { sender: 'ai', text: aiResponse }]);
        setInput(''); // Clear the input field
        setIsLoading(false); // Set loading state to false as response is received
    };

    return (
        <div>
            {/* Chat interface container */}
            <div style={{position: "relative", height: "800px", width: "700px"}}>
                <MainContainer>
                    <ChatContainer>
                        {/* Message list to display conversation */}
                        <MessageList typingIndicator={isLoading && <TypingIndicator content="Waiting for response" />}>
                            {/* Map through conversation array to display each message */}
                            {conversation.map((msg, index) => (
                                <Message key={index} model={{
                                    message: msg.message,
                                    direction: msg.sender === "user" ? "outgoing" : "incoming"
                                }} />
                            ))}
                        </MessageList>
                        {/* Input field for user to type message */}
                        <MessageInput placeholder='Ask your question here' onSend={() => handleSubmit()}/>
                    </ChatContainer>
                </MainContainer>
            </div>
        </div>
    );
}
