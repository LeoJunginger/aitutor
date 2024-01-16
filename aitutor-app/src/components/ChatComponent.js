import React, { useState, useEffect, useRef } from 'react';
import { fetchCourses, askGPT } from '../api';


// Functional component for Chat
export default function ChatComponent() {
    // State for the current user input
    const [input, setInput] = useState('');

    // State to determine the available courses in the db
    const [courses, setCourses] = useState([]);
    const [coursefiles, setCourseFiles] = useState('');
    const [courseList, setCourseList] = useState([]);

    // State for the conversation history
    const [conversation, setConversation] = useState([
        { message: "Loading available courses...", sender: "ChatGPT" }
    ]);

    useEffect(() => {
        const getCourses = async () => {
            try {
                const response = await fetch('http://localhost:9080/aitutor/api/courses', {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        // Add any additional headers if needed
                    },
                });
                if (!response.ok) {
                    throw new Error(`Failed to fetch courses. Status: ${response.status}`);
                }
                const data = await response.json();
                setCourses(data);
                const courseNames = data.map(course => course.courseName);
                setCourseList(courseNames);
                setConversation([
                    { message: `Do you have a specific question about a course? Available courses are: ${courseNames.join(', ')}`, sender: "ChatGPT" }
                ]);
            } catch (error) {
                console.error('Error fetching courses:', error);
                const defaultCourses = ['EAE', 'TAM'];
                setCourses(defaultCourses);
                setCourseList(defaultCourses);
                setConversation([
                    { message: `There was an error fetching the most current available courses. I can still answer questions regarding: ${defaultCourses.join(', ')}`, sender: "ChatGPT" }
                ]);
            }
        }

        getCourses();
    }, []);

    // Function to extract course name from user input
    const extractCourseName = (userInput) => {
        // courseList is comma-separated string
        var match = false
        courses.map(course => {
            const courseName = course.courseName;
            if (userInput.toLowerCase().includes(courseName.toLowerCase())) {
                match = courseName;
            }
        });
        return match;
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

                console.log("exact course name:"+extractedCourseName);

                if (extractedCourseName) {
                    // User provides a valid course name
                    setCourseName(extractedCourseName);
                    setConversation(convo => [...convo, { sender: 'ai', message: `Got it! What's your question regarding ${extractedCourseName}?` }]);
                    const cc = courses;

                    const choosenCourse = cc.find(obj => obj['courseName'] === extractedCourseName);
                    
                    console.log(courses);
                    console.log(choosenCourse);

                    setCourseFiles(choosenCourse.materialPath)
                    setAskingCourse(false); // Stop asking for course name
                } else {
                    // Course name not found, keep asking for a course name
                    setConversation(convo => [...convo, { sender: 'ai', message: "Sorry, I couldn't find that course. Please provide a valid course name." }]);
                    // Do not set askingCourse to false here
                }}
            } else {
                // Call the askGPT API function with the course name and user question
                try {
                    const response = await askGPT(courseName, userInput, coursefiles);
                    if (response.data) {
                        setConversation(convo => [...convo, { sender: 'ai', message: response.data }]);
                    } else {
                        setConversation(convo => [...convo, { sender: 'ai', message: "Sorry, I couldn't process your question." }]);
                    }
                } catch (error) {
                    console.error('Error:', error);
                    setConversation(convo => [...convo, { sender: 'ai', message: "An error occurred while contacting the server." }]);
                }
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