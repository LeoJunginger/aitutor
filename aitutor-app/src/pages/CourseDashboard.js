import React, { useEffect, useState } from 'react';
import { fetchCourses, createCourse } from '../api'; // Füge die createCourse-Funktion hinzu
import { useNavigate } from 'react-router-dom';
import CourseComponent from '../components/CourseComponent';
import CreateCoursePopup from '../components/CreateCoursePopup'; // Füge die CreateCoursePopup-Komponente hinzu

const CourseDashboard = () => {
  const [courses, setCourses] = useState([]);
  const [lecturers, setLecturers] = useState([]); // Füge die State-Variable für Lecturers hinzu
  const [isPopupOpen, setPopupOpen] = useState(false);
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

    // Lade auch die Lecturers beim ersten Rendern der Komponente
    const getLecturers = async () => {
      try {
        const response = await fetchLecturers();
        setLecturers(response.data);
      } catch (error) {
        console.error('Error fetching lecturers:', error);
      }
    };

    getCourses();
    getLecturers();
  }, []);

  const handleCreateCourse = () => {
    setPopupOpen(true);
  };

  const handleCreateCourseSubmit = async (newCourseData) => {
    try {
      await createCourse(newCourseData);
      const updatedCourses = await fetchCourses();
      setCourses(updatedCourses.data);
    } catch (error) {
      console.error('Error creating course:', error);
    } finally {
      setPopupOpen(false); // close  Popup
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
      <button onClick={handleCreateCourse}>Create New Course</button>

      {isPopupOpen && (
        <CreateCoursePopup
          lecturers={lecturers}
          onCreateCourse={handleCreateCourseSubmit}
          onClose={closePopup}
        />
      )}

      <div>
        {courses.map(course => (
          <CourseComponent
            key={course.id}
            name={course.courseName}
            lecturer={course.lecturer ? `${course.lecturer.firstname} ${course.lecturer.lastname}` : 'N/A'}
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