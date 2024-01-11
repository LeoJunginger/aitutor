import logo from './logo.svg';
import './App.css';
// useState lets add state to functional components
import React, { useState } from 'react';
import ChatComponent from './ChatComponent.js'; 

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        {/* Add ChatComponent to the App */}
        <ChatComponent />
      </header>
    </div>
    
  );
}

export default App;
