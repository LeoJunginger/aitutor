// AI Chat component

import React, { useState } from 'react';

function ChatComponent(){
    // useState hook to manage the current user input
    const [input, setInput] = useState('');
    // useState hook to manage the conversation history
    const [conversation, setConversation] = useState([]);

    // Handler for change in input field
    const handleInputChange = (e) => {
        setInput(e.targe.value);
    };

    // Handler for submission of user input
    const handleSubmit = async () => {
        const userInput = input.trim();
        if (!userInput) return; // Ignore empty imput

        // Update conversation with user input
        setConversation([...conversation, {sender: 'user', text: userInput}]);

        // TODO: send userInput to GPT API (GPTprompting)
        // For testing, placeholder response
        const aiResponse = 'Response from GPT...'; 

          // Update the conversation with the AI's response
    setConversation(convo => [...convo, { sender: 'user', text: userInput }, { sender: 'ai', text: aiResponse }]);

        setInput(''); // Clear input field after submission
    }

    // The return statement renders the UI of the component
    return (
        <div>
        {/* Chat UI elements */}
        {/* Display the conversation */}
        <div className="conversation">
            {conversation.map((msg, index) => (
                 <p key={index} className={msg.sender}>
                    {msg.text}
                </p>
            ))}
        </div>
        {/* Input field for user message */}
        <input value={input} onChange={handleInputChange} />

        {/* Submitt message */}
        <button onClick={handleSubmit}>Send</button>

        </div>
    );
}

export default ChatComponent;