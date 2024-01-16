import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import API from '../api';
import chatBotIcon from '../resources/chat-bot.svg';
import '../styling/Homepage.css';

function Homepage() {
    const [courses, setCourses] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        // Check if the user is authenticated (valid token)
        const authToken = localStorage.getItem('authToken');
        if (!authToken) {
            // If there is no valid token, redirect to the login page
            navigate('/login');
            return;
        }

        const fetchCourses = async () => {
            try {
                const response = await API.get('/courses');
                setCourses(response.data);
            } catch (error) {
                console.error('Error fetching courses', error);
            }
        };

        fetchCourses();
    }, [navigate]);

    const goToCourses = () => {
        navigate('/courses');
    };

    const goToChatPage = () => {
        navigate('/chat');
    };

    const handleLogout = () => {
        // Clear user authentication token from local storage
        localStorage.removeItem('authToken');
        navigate('/login');
    };

    return (
        <div className="homepage-container">
            <h1>Welcome to the AITutor Homepage</h1>

            <button className="go-to-courses-button" onClick={goToCourses}>
                Go to Courses
            </button>

            <div className="news-boxes">
                {/* Example news boxes, adjust content as needed */}
                <div className="news-box">News 1: AI advancements in education</div>
                <div className="news-box">News 2: University achievements</div>
                <div className="news-box">News 3: Upcoming events</div>
            </div>

            <div>
                <button style={{ margin: '30px', width: '100px', height: '50px' }} onClick={handleLogout}>
                    Logout
                </button>
            </div>

            <div className="chat-icon-container" onClick={goToChatPage}>
                <img src={chatBotIcon} alt="Chat Bot" className="chat-icon" />
            </div>
        </div>
    );
}

export default Homepage;
