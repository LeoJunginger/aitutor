import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import API from '../api';

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
        navigate('/courses'); // Assuming '/courses' is the route for the courses page
    };

    return (
        <div>
             <div style={{ textAlign: 'left' }}>
                <button style={{ margin: '10px' }}>Profile</button> {/* Non-clickable for now */}
            </div>
            <h1>Welcome to the AITutor Homepage</h1>
            <div>
                {courses.map(course => (
                    <div key={course.id}>{course.name}</div> // Adjust according to your course data structure
                ))}
            </div>
            <button onClick={goToCourses}>Zu den Kursen</button> {/* Button to navigate to courses */}
            {/* ... existing chatbot icon */}
        </div>
    );
}

export default Homepage;