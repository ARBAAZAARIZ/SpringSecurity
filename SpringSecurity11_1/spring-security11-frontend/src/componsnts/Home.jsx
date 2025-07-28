import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

export default function Home() {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  // ✅ Extract CSRF token from cookie
  const getCsrfTokenFromCookie = () => {
    const cookie = document.cookie
      .split('; ')
      .find(row => row.startsWith('XSRF-TOKEN='));
    return cookie ? cookie.split('=')[1] : null;
  };

  useEffect(() => {
    const jwtToken = sessionStorage.getItem("authorization");
    const csrfToken = getCsrfTokenFromCookie();

    if (!jwtToken || !csrfToken) {
      console.error("JWT or CSRF token missing");
      return;
    }

    const response = axios.get("http://localhost:8080/auth/user", {
      headers: {
        Authorization: jwtToken, // already contains "Bearer ..."
        'X-XSRF-TOKEN': csrfToken
      },
      withCredentials: true
    })
      .then(response => {
        console.log(response.data);

        setUser(response.data);
      })
      .catch(err => {
        console.error("Error fetching user", err);
        alert("Session expired. Please log in again.");
        navigate("/login");
      });
  }, []);

  // ✅ Logout function
  const handleLogout = () => {
    sessionStorage.clear(); // clear all auth info
    navigate("/login");
  };

  return (
    <div className="container mt-4">
      <h2>Home</h2>

      <button className="btn btn-primary me-2">
        <Link to="/login" className="text-white text-decoration-none">Go to Login Page</Link>
      </button>

      <button className="btn btn-danger" onClick={handleLogout}>Logout</button>

      <br />
      <br />
      <button className="btn btn-warning me-2">
        <Link to="/loans" className="text-white text-decoration-none">loans</Link>
      </button>
      <button className="btn btn-info">
        <Link to="/accounts" className="text-white text-decoration-none">Accounts</Link>
      </button>
      <hr />

      {user ? (
        <div className="card p-3 shadow">
          <h4>Welcome, {user.name}</h4>
          <p><strong>Email:</strong> {user.email}</p>
          <p><strong>Role:</strong> {user.role}</p>
          <p><strong>Mobile:</strong> {user.mobileNumber}</p>
          <p><strong>Joined:</strong> {new Date(user.createdDate).toLocaleString()}</p>
        </div>
      ) : (
        <p>Loading user details...</p>
      )}
    </div>
  );
}
