// actions/authActions.js

export const loginUser = (userData) => (dispatch) => {
    // Use axios or another HTTP client to make the request
    axios.post('/api/auth/login', userData)
      .then(res => {
        // Get token from response
        const { token } = res.data;
  
        // Save token to localStorage
        localStorage.setItem('jwtToken', token);
  
        // Dispatch success or other actions as necessary...
      })
      .catch(err => {
        // Handle errors...
      });
  };
  