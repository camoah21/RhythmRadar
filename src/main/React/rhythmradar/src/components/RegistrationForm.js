import React, { useState } from 'react';
import axios from 'axios';

const RegistrationForm = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (password !== confirmPassword) {
            setError("Passwords don't match");
            return;
        }

        try {
            await axios.post('http://localhost:8080/api/auth/register', {
                username,
                email,
                password,
            });
            // Registration successful, maybe redirect to login or a welcome page
        } catch (err) {
            // Handle registration errors (e.g., duplicate email or username)
            setError(err.response.data.message || 'Registration failed');
        }
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Username:</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </div>
                <div>
                    <label>Email:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div>
                    <label>Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <div>
                    <label>Confirm Password:</label>
                    <input
                        type="password"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                    />
                </div>
                {error && <div style={{ color: 'red' }}>{error}</div>}
                <button type="submit">Register</button>
            </form>
        </div>
    );
};

export default RegistrationForm;
