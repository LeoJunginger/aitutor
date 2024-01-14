// ChatPage.js
import React from 'react';
import '../components/ChatStyle.css'; 
import ChatComponent from '../components/Chat_Component.js'; 

const ChatPage = () => {
    return (
        <div className="chat-page">
            <h1>Welcome to the AI Tutor</h1>
            <ChatComponent />
        </div>
    );
}

export default ChatPage;
