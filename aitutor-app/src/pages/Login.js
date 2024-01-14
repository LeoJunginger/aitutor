import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import API from '../api'; 


function Login() {
    const navigate = useNavigate();
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

    return (
        <div>
            <form onSubmit={handleLogin}>
                <h2>Login</h2>
                <div>
                    <label>Username:</label>
                    <input 
                        type="text" 
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Password:</label>
                    <input 
                        type="password" 
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Login</button>
            </form>
        </div>
    );
}
export default Login;