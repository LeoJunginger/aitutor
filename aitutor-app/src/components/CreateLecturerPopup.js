import React, { useState } from 'react';
import {createUser} from '../api';

function CreateLecturerPopup({ onClose }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [firstname, setFirstname] = useState('');
    const [lastname, setLastname] = useState('');


    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const user = { username, password, firstname, lastname, role: 'LECTURER' };
            const response = await createUser(user);
            console.log(`Created lecturer with username: ${username}`);
            onClose();
        } catch (error) {
            console.error('Failed to create lecturer', error);
        }
    };

    return (
        <div>
            <h2>Create Lecturer</h2>
            <form onSubmit={handleSubmit}>
            <label>Username:</label>
                <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} required />
                <label>Password:</label>
                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                <label>First Name:</label>
                <input type="text" value={firstname} onChange={(e) => setFirstname(e.target.value)} required />
                <label>Last Name:</label>
                <input type="text" value={lastname} onChange={(e) => setLastname(e.target.value)} required />
                <button type="submit">Create</button>
                <button type="button" onClick={onClose}>Cancel</button>
            </form>
        </div>
    );
}

export default CreateLecturerPopup;