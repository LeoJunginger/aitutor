import React, { useState } from 'react';
import { createCourse, fetchCourses } from '../api';

const CreateCoursePopup = ({ onClose }) => {
  const [courseName, setCourseName] = useState('');
  const [description, setDescription] = useState('');
  const [lecturer, setLecturer] = useState('');
  const [materialPath, setMaterialPath] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();

    // Check if a course with the same name already exists
    const existingCourses = await fetchCourses();
    if (existingCourses.data.some(course => course.courseName === courseName)) {
      alert('A course with the same name already exists!');
      return;
    }

    // Create the new course
    const newCourse = { courseName, description, lecturer, materialPath };

    try {
      const response = await fetch('http://localhost:9080/aitutor/api/courses', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newCourse),
      });

      if (response.ok) {
        // Course created successfully
        // You might want to add additional logic or notifications here
      } else {
        // Handle course creation error (e.g., course name already taken)
        const data = await response.json();
        console.error(data);
      }
    } catch (error) {
      console.error('Course creation failed:', error);
    }

    // Close the popup
    onClose();
  };

  return (
    <div className="popup">
      <form onSubmit={handleSubmit}>
        <label>
          Coursename:
          <input type="text" value={courseName} onChange={e => setCourseName(e.target.value)} required />
        </label>
        <label>
          Description:
          <input type="text" value={description} onChange={e => setDescription(e.target.value)} required />
        </label>
        <label>
          Lecturer:
          <input type="text" value={lecturer} onChange={e => setLecturer(e.target.value)} required />
        </label>
        <label>
          Material Path:
          <input type="text" value={materialPath} onChange={e => setMaterialPath(e.target.value)} required />
        </label>
        <button type="submit">Create Course</button>
        <button type="button" onClick={onClose}>Close</button>
      </form>
    </div>
  );
};

export default CreateCoursePopup;