import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './pages/Login';
import Homepage from './pages/Homepage';
import CourseDashboard from './pages/CourseDashboard';
import ChatPage from './pages/ChatPage';

function App() {
  return (    
  <Router>
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/homepage" element={<Homepage />} />
      <Route path="/courses" element={<CourseDashboard />} />
      <Route path="/chat" element={<ChatPage />} />
    </Routes>
  </Router>
);
}
export default App;

