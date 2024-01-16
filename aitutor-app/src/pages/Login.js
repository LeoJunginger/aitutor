import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import API from '../api';
import CreateLecturerPopup from '../components/CreateLecturerPopup';
import '../styling/Login.css'; // Import the CSS file for styling

function Login() {
    const navigate = useNavigate();
    const [showPopup, setShowPopup] = useState(false);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            //const response = await API.post('/login', { username, password });
            // Handle the response, e.g., store the auth token, if any
            console.log(`Login attempt with username: ${username} and password: ${password}`);
            navigate('/homepage');
        } catch (error) {
            console.error('Login failed', error);
        }
    };

    const handleCreateLecturer = async (user) => {
        try {
            const response = await API.post('/users', { ...user, role: 'LECTURER' });
            console.log(`Created lecturer with username: ${user.username}`);
            navigate('/homepage');
        } catch (error) {
            console.error('Failed to create lecturer', error);
        }
    };

    return (
        <div className="login-container">
            <form onSubmit={handleLogin} className="login-form">
                <h2>Login</h2>
                <div className="form-group">
                    <label>Username:</label>
                    <input 
                        type="text" 
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Password:</label>
                    <input 
                        type="password" 
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="button" onClick={() => setShowPopup(true)}>Create Lecturer</button>
                <button type="submit">Login</button>
            </form>
            {showPopup && <CreateLecturerPopup onClose={() => setShowPopup(false)} onCreate={handleCreateLecturer} />}
        </div>
    );
}

export default Login;
