import React from 'react';

function CourseComponent({ name, lecturer, description }) {
    return (
        <div className="course-item">
            <h3>{name}</h3>
            <p>Lecturer: {lecturer}</p>
            <p>Description: {description}</p>
        </div>
    );
}

export default CourseComponent;