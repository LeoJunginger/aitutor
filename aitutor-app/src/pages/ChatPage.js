// ChatPage.js
import React from 'react';
import '../styling/ChatStyle.css'; 
import ChatComponent from '../components/ChatComponent.js'; 

const ChatPage = () => {
    return (
        <div className="chat-page">
            <h1>Welcome to the AI Tutor</h1>
            <ChatComponent />
        </div>
    );
}

export default ChatPage;
