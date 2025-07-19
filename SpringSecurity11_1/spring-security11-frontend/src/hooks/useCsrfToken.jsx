import { useState, useEffect } from 'react';
import axios from 'axios';

export const useCsrfToken = () => {
  const [csrfToken, setCsrfToken] = useState(null);

  useEffect(() => {
    axios.get('http://localhost:8080/api/auth/csrf', { withCredentials: true })
      .then(() => {
        const token = document.cookie
          .split('; ')
          .find(row => row.startsWith('XSRF-TOKEN='));
        
        if (token) {
          setCsrfToken(token.split('=')[1]);
          console.log("CSRF token fetched and stored:", token.split('=')[1]);
        }
      })
      .catch((err) => {
        console.error("Failed to fetch CSRF token:", err);
      });
  }, []);

  return csrfToken;
};
