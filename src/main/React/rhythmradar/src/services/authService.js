import axios from './axiosConfig';  // using the axios instance you configured

export async function refreshToken() {
    // Get the refresh token from wherever you've stored it
    const storedRefreshToken = localStorage.getItem('refresh_token');
  
    // Send a request to the server's "refresh token" endpoint
    try {
      const response = await axios.post('/refresh-token', {
        refresh_token: storedRefreshToken
      });
  
      // If successful, the response should contain the new access token
      const newAccessToken = response.data.access_token;
  
      // Store the new access token wherever you're keeping it (e.g., in local storage)
      localStorage.setItem('access_token', newAccessToken);
  
      return newAccessToken;
  
    } catch (error) {
      // Handle any errors, such as the refresh token being invalid
      console.error('Error refreshing token', error);
      throw error;
    }
}
