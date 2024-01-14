import React, { useState, useEffect } from 'react';

// Functional component for Chat
export default function ChatComponent() {
    // State for the current user input
    const [input, setInput] = useState('');

        const [conversation, setConversation] = useState([
        { message: "Loading available courses...", sender: "ChatGPT" }
    ]);

    // Mock function to simulate fetching course names
    const getCoursenames = async () => {
        // Simulate a delay like an API call
        await new Promise(resolve => setTimeout(resolve, 1000));

        // Return a list of course names
        return ['EAE', 'SPM', 'DPM', 'TAM', 'DL'];
    };

    // Function to fetch course names via API Endpoint
    // Delete above testing function after implementing API Endpoint
    /* const getCoursenames = async () => {
        try {
            const response = await fetch('/api/courses'); // TODO: Replace with  API endpoint
            if (!response.ok) {
                throw new Error('Network response failed');
            }
            const courses = await response.json();
            return courses.map(course => course.name); // Assuming each course has 'name' property
        } catch (error) {
            console.error('Failed to fetch courses:', error);
            return ['Unable to fetch courses'];
        }
    }; */

    // Fetch course information when component mounts
    useEffect(() => {
        fetch('http://localhost:8080/api/courses')
            .then(response => response.json())
            .then(data => {
                // Add course information to conversation
                setConversation([...conversation, ...data]);
            })
            .catch(error => console.error('Error:', error));
    }, []);
    
    

    // useEffect to fetch course names on component mount
    useEffect(() => {
        getCoursenames().then(courseNames => {
            const courseList = courseNames.join(', ');
            setConversation([
                { message: `Do you have a specific question to a course? Available courses are: ${courseList}`, sender: "ChatGPT" }
            ]);
        });
    }, []); // Empty dependency array ensures this runs once on mount

    const extractCourseName = (userInput) => {
        const courseNames = ['EAE', 'SPM', 'DPM', 'TAM', 'DL'];

        // Create a regular expression to find these course names in the input
        const courseNameRegex = new RegExp(courseNames.join('|'), 'i');

        // Search for a course name in the user input
        const match = userInput.match(courseNameRegex);

        // If a course name is found, return it, otherwise return null
        return match ? match[0] : null;
    };



    // State to indicate if the AI is "typing" or processing a response
    const [isLoading, setIsLoading] = useState(false);

    // State to determine if the chatbot is currently asking for a course name
    const [askingCourse, setAskingCourse] = useState(true);

    // State to store the name of the course once provided
    const [courseName, setCourseName] = useState('');

    // Handler for input field changes
    const handleInputChange = (e) => {
        setInput(e.target.value); // Update input state with the value from the input field
    };

    // Handler for submitting the user input
    const handleSubmit = async () => {
        const userInput = input.trim();
        if (!userInput) return; // Ignore empty input
    
        // Add user's message to the conversation history
        setConversation([...conversation, { message: userInput, sender: "user", direction: "outgoing" }]);
        setInput(''); // Clear the input field
        setIsLoading(true); // Set loading state to indicate processing
    
        // Check if currently asking for a course name
        if (askingCourse) {
            if (userInput.toLowerCase() === 'no') {
                // User indicates no specific course question
                setConversation(convo => [...convo, { sender: 'ai', message: "Alright, feel free to ask any general questions or about learning strategies!" }]);
                setAskingCourse(false); // Stop asking for course name
            } else {
                // Extract the course name from user input
                const extractedCourseName = extractCourseName(userInput);
        
                if (extractedCourseName) {
                    // User provides a valid course name
                    setCourseName(extractedCourseName);
                    setConversation(convo => [...convo, { sender: 'ai', message: `Got it! What's your question regarding ${extractedCourseName}?` }]);
                    setAskingCourse(false); // Stop asking for course name
                } else {
                    // Course name not found, keep asking for a course name
                    setConversation(convo => [...convo, { sender: 'ai', message: "Sorry, I couldn't find that course. Please provide a valid course name." }]);
                    // Do not set askingCourse to false here
                }
            }
        } else {
            // Handle questions related to the course or general questions
            // TODO: Send userInput and courseName to GPT API for processing
            const aiResponse = 'Response from GPT...'; // Placeholder response
            setConversation(convo => [...convo, { sender: 'ai', message: aiResponse }]);
        }

        setIsLoading(false); // Reset loading state after response
    };

    return (
        <div style={{position: "relative", height: "800px", width: "700px"}}>
            <div className="chat-container">
                <div className="message-list">
                        {conversation.map((msg, index) => (
                            <div key={index} className={`message ${msg.sender === "user" ? "outgoing" : "incoming"}`}>
                            {msg.message}
                        </div>
                        ))}
                    </div>
                    {isLoading && <div className="typing-indicator">Waiting for response...</div>}
                <div className="message-input">
                    <input
                        type="text"
                        value={input}
                        onChange={handleInputChange}
                        placeholder="Type your message here"
                    />
                    <button onClick={handleSubmit}>Send</button>
                </div>
            </div>
        </div>
    );
}