import React, { useEffect, useState } from 'react';
import { fetchCourses } from '../api'; 
import { useNavigate } from 'react-router-dom';
import CourseComponent from '../components/CourseComponent';

function CourseDashboard() {
    const [courses, setCourses] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const getCourses = async () => {
            try {
                const response = await fetchCourses();
                setCourses(response.data);
            } catch (error) {
                console.error('Error fetching courses:', error);
            }
        };
        getCourses();
    }, []);

    const handleCreateCourse = () => {
        // TODO: Implement logic to handle course creation
        // This could be showing a form or redirecting to a course creation page
        console.log('Create new course button clicked');
    };

    const goBack = () => {
        navigate('/Homepage'); // Navigates back to the homepage
    };

    return (
        <div>
            <h1>Course Dashboard</h1>
            <button onClick={handleCreateCourse}>Create New Course</button>
            <div>
                {courses.map(course => (
                    <CourseComponent
                        key={course.id}
                        name={course.courseName}
                        lecturer={course.lecturer ? course.lecturer.name : 'N/A'}
                        description={course.description}
                    />
                ))}
            </div>
            <div>
            <button onClick={goBack}>Back to Homepage</button>
        </div>
        </div>
    );
}

export default CourseDashboard;