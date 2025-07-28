import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function Accounts() {
  const csrfToken = sessionStorage.getItem("XSRF-TOKEN");
  const jwtToken = sessionStorage.getItem("authorization");

  const [account, setAccount] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    if (!jwtToken || !csrfToken) {
      console.error("JWT or CSRF token missing");
      return;
    }

    axios.get("http://localhost:8080/auth/accounts", {
      headers: {
        Authorization: jwtToken,
        "X-XSRF-TOKEN": csrfToken
      },
      withCredentials: true
    }).then(response => {
      console.log("Accounts fetched:", response.data);
      setAccount(response.data);
    }).catch(err => {
      console.error("Error fetching accounts", err);
      alert("Session expired. Please log in again.");
      navigate("/login");
    });
  }, []);

  return (
    <div className="container mt-4">
      <button className="btn btn-primary mb-3">
        <Link to={"/home"} className="text-white text-decoration-none">Home</Link>
      </button>

      <h3>Account Details</h3>
      {account ? (
        <div className="card shadow p-4">
          <h5>Account Number: {account.accountNumber}</h5>
          <p><strong>Account Type:</strong> {account.accountType}</p>
          <p><strong>Branch Address:</strong> {account.branchAddress}</p>
          <p><strong>Created Date:</strong> {new Date(account.createdDate).toLocaleString()}</p>
          <hr />
          <h5>Customer Info</h5>
          <p><strong>Name:</strong> {account.customer.name}</p>
          <p><strong>Email:</strong> {account.customer.email}</p>
          <p><strong>Mobile:</strong> {account.customer.mobileNumber}</p>
          <p><strong>Role:</strong> {account.customer.role}</p>
          <p><strong>Joined:</strong> {new Date(account.customer.createdDate).toLocaleString()}</p>
        </div>
      ) : (
        <p>Loading account information...</p>
      )}
    </div>
  );
}
