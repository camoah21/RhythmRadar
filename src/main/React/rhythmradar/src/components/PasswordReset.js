import React, { useState } from 'react';
import axios from 'axios';

function PasswordReset(props) {
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const token = new URLSearchParams(props.location.search).get('token');

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      alert('Passwords do not match!');
      return;
    }

    try {
      const response = await axios.post('/api/reset-password', {
        token: token,
        newPassword: password
      });
      // Handle successful password reset (e.g., navigate to login page, show success message)
    } catch (error) {
      // Handle errors (e.g., show an error message to the user)
    }
  }

  return (
    <div>
      <h2>Reset Password</h2>
      <form onSubmit={handleSubmit}>
        <input 
          type="password"
          placeholder="New Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <input 
          type="password"
          placeholder="Confirm Password"
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
        />
        <button type="submit">Reset Password</button>
      </form>
    </div>
  );
}

export default PasswordReset;
