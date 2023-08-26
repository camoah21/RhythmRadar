import axios from 'axios';

const api = axios.create({
  baseURL: 'http://your-api-url.com'
});

if (localStorage.jwtToken) {
  api.defaults.headers.common['Authorization'] = `Bearer ${localStorage.jwtToken}`;
} else {
  delete api.defaults.headers.common['Authorization'];
}

export default api;

