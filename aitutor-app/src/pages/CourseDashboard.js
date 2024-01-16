import React, { useEffect, useState } from 'react';
import api, { fetchCourses, fetchLecturers, createCourse } from '../api';
import { useNavigate } from 'react-router-dom';
import CourseComponent from '../components/CourseComponent';
import CreateCoursePopup from '../components/CreateCoursePopup';

const CourseDashboard = () => {
  const [courses, setCourses] = useState([]);
  const [isPopupOpen, setPopupOpen] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    // Check if the user is authenticated (valid token)
    const authToken = localStorage.getItem('authToken');
    if (!authToken) {
      // If there is no valid token, redirect to the login page
      navigate('/login');
      return;
    }

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
          // Handle non-successful response here if needed
          throw new Error(`Failed to fetch courses. Status: ${response.status}`);
        }

        const data = await response.json();
        // Assuming setCourses is a state-setting function like in React
        setCourses(data);
      } catch (error) {
        console.error('Error fetching courses:', error);
      }
    }

    getCourses();
  }, [isPopupOpen]);

  const handleCreateCourse = () => {
    setPopupOpen(true);
  };

  const handleCreateCourseSubmit = async (newCourseData) => {
    try {
      await createCourse(newCourseData);
      const updatedCourses = await fetchCourses();
      setCourses(updatedCourses.data);
      setPopupOpen(false); // close Popup
    } catch (error) {
      console.error('Error creating course:', error);
      setPopupOpen(false); // close Popup even if there is an error
    }
  };

  const closePopup = () => {
    setPopupOpen(false);
  };

  const goBack = () => {
    navigate('/Homepage');
  };

  return (
    <div>
      <h1>Course Dashboard</h1>
      <button className="create-course-button" onClick={handleCreateCourse}>
        +
      </button>

      {isPopupOpen && (
        <CreateCoursePopup
          onCreateCourse={handleCreateCourseSubmit}
          onClose={closePopup}
        />
      )}

      <div className="course-list">
        {courses.map(course => (
          <CourseComponent
            key={course.id}
            name={course.courseName}
            lecturer={course.lecturer}
            description={course.description}
          />
        ))}
      </div>

      <div>
        <button onClick={goBack}>Back to Homepage</button>
      </div>
    </div>
  );
};

export default CourseDashboard;