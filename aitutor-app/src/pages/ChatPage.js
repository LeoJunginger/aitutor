// ChatPage.js
import React from 'react';
import ChatComponent from '../components/ChatComponent.js'; 
import '../components/ChatStyle.css'; // Update the path as per your directory structure

const ChatPage = () => {
    return (
        <div className="chat-page">
            <h1>Welcome to the AI Tutor</h1>
            <ChatComponent />
        </div>
    );
}

export default ChatPage;
