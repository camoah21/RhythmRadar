// reducers/authReducer.js

const initialState = {
    token: localStorage.getItem('jwtToken'),
    isAuthenticated: null,
    loading: true,
    user: null
  };
  
  function authReducer(state = initialState, action) {
    switch(action.type) {
      // Handle different action types...
      default:
        return state;
    }
  }
  