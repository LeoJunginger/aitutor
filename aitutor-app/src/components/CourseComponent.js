import React from 'react';
import '../styling/Course.css'; // Import your CSS file for styling

function CourseComponent({ name, lecturer, description, onClick }) {
  return (
    <div className="course-item" onClick={onClick}>
      <div className="course-header">
        <h2 className="course-name">{name}</h2>
        <p className="lecturer-name">
          Lecturer: {lecturer}
        </p>
      </div>
      <div className="course-description">
        <p>{description}</p>
      </div>
    </div>
  );
}

export default CourseComponent;