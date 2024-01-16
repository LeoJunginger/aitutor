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
  return API.get('/courses'); // Update path
};

// Function to create a course
export const createCourse = (newCourse) => {
  return API.post('/courses', newCourse); // Update path
};

// Function to update a course
export const updateCourse = (courseId, updatedCourse) => {
  return API.put(`/courses${courseId}`, updatedCourse); // Update path
};

// Function to fetch users
export const fetchUsers = () => {
  return API.get('/users/all'); // Update path
};

// Function to create a user
export const createUser = (newUser) => {
  return API.post('/users/create', newUser); // Update path
};

export const fetchLecturers = async () => {
  const response = await API.get('/users/all'); // Update path
  const lecturers = response.data.filter(user => user.role === 'LECTURER');
  return lecturers;
};

// Function to update a user
export const updateUser = (id, updatedUser) => {
  return API.put(`/users/update/${id}`, updatedUser); // Update path
};

export default API; 