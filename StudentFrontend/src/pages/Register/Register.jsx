import React, { useState } from 'react';
import { Link } from 'react-router-dom';

function Register() {
  const [name, setName] = useState('');
  const [age, setAge] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [className, setClassName] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    alert(`Name: ${name}\nAge: ${age}\nEmail: ${email}\nPassword: ${password}\nClass: ${className}`);
  };

  return (
    <div className="d-flex justify-content-center align-items-center vh-100">
      <div className="register-form p-4 shadow rounded" style={{ maxWidth: '400px', width: '100%' }}>
        <h2 className="mb-4 text-center">Register</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="name" className="form-label">Name</label>
            <input
              id="name"
              type="text"
              className="form-control"
              value={name}
              onChange={e => setName(e.target.value)}
              required
              placeholder="Enter your name"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="age" className="form-label">Age</label>
            <input
              id="age"
              type="number"
              className="form-control"
              value={age}
              onChange={e => setAge(e.target.value)}
              required
              placeholder="Enter your age"
              min="0"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">Email address</label>
            <input
              id="email"
              type="email"
              className="form-control"
              value={email}
              onChange={e => setEmail(e.target.value)}
              required
              placeholder="Enter your email"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="password" className="form-label">Password</label>
            <input
              id="password"
              type="password"
              className="form-control"
              value={password}
              onChange={e => setPassword(e.target.value)}
              required
              placeholder="Enter a password"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="class" className="form-label">Class</label>
            <input
              id="class"
              type="text"
              className="form-control"
              value={className}
              onChange={e => setClassName(e.target.value)}
              required
              placeholder="Enter your class"
            />
          </div>
          <button type="submit" className="btn btn-success w-100">Register</button>
        </form>
        <p className="mt-3 text-center">
          Already registered?{' '}
          <Link to="/" className="text-decoration-none">
            Login here
          </Link>
        </p>
      </div>
    </div>
  );
}

export default Register;
