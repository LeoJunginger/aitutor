import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './pages/Login';
import Homepage from './pages/Homepage';
import CourseDashboard from './pages/CourseDashboard';
import ChatPage from './pages/ChatPage';
import Logout from './pages/Logout';
import Register from './pages/Register';

function App() {
  return (    
  <Router>
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path="/homepage" element={<Homepage />} />
      <Route path="/courses" element={<CourseDashboard />} />
      <Route path="/chat" element={<ChatPage />} />
      <Route path="/logout" element={<Logout />} />
      <Route path="/register" element={<Register />} />
    </Routes>
  </Router>
);
}
export default App;
