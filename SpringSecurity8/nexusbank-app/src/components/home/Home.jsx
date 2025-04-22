import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { UseXcrfToken } from '../../hooks/UseXcrfToken';
import { useNavigate } from 'react-router-dom';

export default function Home() {
  const [user, setUser] = useState(null);

  const navigate =useNavigate();

  const csrfToken = UseXcrfToken();
  console.log("CSRF Token", csrfToken);
  

  // Put your API call inside useEffect to avoid re-running on every render.
  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await axios.get("http://localhost:8080/user",{
          headers:{"X-XSRF-TOKEN":csrfToken},
          withCredentials: true  // Ensure cookies/session are sent
        });

        if (response.data != null) {
          console.log(response.data.data.email);  // Should print the user's email if authenticated
          setUser(response.data); // Optionally update state with user details
        }
      } catch (err) {
        console.log("Error: You haven't authenticated yet", err);
        navigate("/login"); // Redirect to login if not authenticated
      }
    };

    fetchUser();
  }, []); // The empty dependency array ensures the effect runs once.

  return (
    <div>
      <h1>home</h1>
      {/* Display user info if available */}
      {user && <p>Welcome, {user.email}</p>}
    </div>
  );
}