import axios from 'axios';

// Set the JWT as the default auth header
if (localStorage.jwtToken) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.jwtToken}`;
}

axios.interceptors.response.use(null, error => {
    if (error.response.status === 401) {
      return handleUnauthorizedError(error);
    }
    return Promise.reject(error);
  });

function handleUnauthorizedError(error) {
    return refreshToken().then(newToken => {
      // Update the request with the new token and retry it
      error.response.config.headers['Authorization'] = 'Bearer ' + newToken;
      return axios(error.response.config);
    });
  }
  
function refreshToken() {
    const refreshToken = localStorage.getItem('refresh_token');
    return axios.post('/api/auth/refresh-token', { refresh_token: refreshToken })
      .then(response => {
        const newAccessToken = response.data.access_token;
        localStorage.setItem('jwtToken', newAccessToken);
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + newAccessToken; // Set the default header
        return newAccessToken;
      });
  }

function refreshToken() {
    const refreshToken = localStorage.getItem('refresh_token');
    return axios.post('/api/auth/refresh-token', { refresh_token: refreshToken })
      .then(response => {
        const newAccessToken = response.data.access_token;
        localStorage.setItem('jwtToken', newAccessToken);
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + newAccessToken; // Set the default header
        return newAccessToken;
      })
      .catch(error => {
        // Handle the scenario when refresh token is also expired or invalid.
        // Redirect to login or show a relevant message to the user.
        return Promise.reject(error);
      });
  }
  
  



export default axios;
