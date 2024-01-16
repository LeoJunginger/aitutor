import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import API from '../api';
import '../styling/Homepage.css';

function Homepage() {
    const [courses, setCourses] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchCourses = async () => {
            try {
                const response = await API.get('/courses');
                setCourses(response.data);
            } catch (error) {
                console.error('Error fetching courses', error);
            }
        };

        fetchCourses();
    }, []);

    const goToCourses = () => {
        navigate('/courses');
    };
    const goToChatPage = () => {
        navigate('/chat');
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
                <button style={{ margin: '30px', width:'100px', height: '50px' }}>Profile</button> {/* Non-clickable for now */}
            </div>

            <div className="chat-icon-container" onClick={goToChatPage}>
                <img src="../resources/chat-bot.svg" alt="Chat Bot" className="chat-icon" />
            </div>
        </div>
    );
}
export default Homepage;