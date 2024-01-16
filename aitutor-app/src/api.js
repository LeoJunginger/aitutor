import axios from 'axios';

// Create an Axios instance with a base URL
const API = axios.create({
  baseURL: 'http://localhost:9080/aitutor/api', // URL of your backend server
  // You can add more global settings here
});

// Optional: Interceptors for requests and responses
API.interceptors.request.use(request => {
  // Modify the request before sending it
  return request;
});

API.interceptors.response.use(response => {
  // Modify the response before passing it to the calling component
  return response;
}, error => {
  // Handle errors
  return Promise.reject(error);
});

// Function to fetch courses
export const fetchCourses = () => {
  return API.get('/courses');
  // return { data: ['EAE','Test2'] };
  // return ['EAE','Test','Test2'];
};

export default API;

// Function to send a question to GPT API
export const askGPT = (courseName, question, filePaths) => {
  return API.post('/gptprompting/ask', {
    // 'courseName': courseName,
    'question': question,
    'filePaths': filePaths
  });
};