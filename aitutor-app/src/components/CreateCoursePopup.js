import React, { useState } from 'react';
import { createCourse, fetchCourses } from '../api';

const CreateCoursePopup = ({ onClose }) => {
  const [courseName, setCourseName] = useState('');
  const [description, setDescription] = useState('');
  const [lecturer, setLecturer] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();

    // Check if a course with the same name already exists
    const existingCourses = await fetchCourses();
    if (existingCourses.data.some(course => course.courseName === courseName)) {
      alert('A course with the same name already exists!');
      return;
    }

    // Create the new course
    const newCourse = { courseName, description, lecturer };
    await createCourse(newCourse);

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
        <button type="submit">Create Course</button>
        <button type="button" onClick={onClose}>Close</button>
      </form>
    </div>
  );
};

export default CreateCoursePopup;