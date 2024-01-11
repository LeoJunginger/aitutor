// AI Chat component

import React, { useState } from 'react';

function ChatComponent(){
     // useState hook to manage the 'question' and 'response' state, initializing it to an empty string
    const [question, setQuestion] = useState('');
    const [response, setRespone] = useState('');


    // Handler for when the user submits their question
    const handleSubmit = () => {
        // TO DO: Add Chat functionality, i.e.
        // Aks for course
        // Ask for Question
        // GPT prompting
        // Answer recieving

        // For testing purpose using console
        console.log("Submitted question:", question);

        // Clear the input field after submission
        setQuestion('');
    };

    // The return statement renders the UI of the component
    return (
        <div>
        {/* Chat UI elements */}

        {/* Text input for the user's question, with the value bound to the 'question' state */}
        <input 
            type="text" 
            value={question} 
            onChange={e => setQuestion(e.target.value)} 
            placeholder="Ask your question here"
        />
        {/* Submit button, TO DO: the onClick handler needs to be implemented to handle the question submission */}
        <button onClick={handleSubmit}>Submit</button>
        <div>
            <p>AI Response:</p>
            {response}
        </div>
        </div>
    );
}

export default ChatComponent;