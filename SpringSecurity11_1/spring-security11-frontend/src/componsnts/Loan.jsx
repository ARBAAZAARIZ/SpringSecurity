import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function Loan() {
  const [loans, setLoans] = useState([]); 
  const navigate = useNavigate();

  const csrfToken = sessionStorage.getItem("XSRF-TOKEN");
  const jwtToken = sessionStorage.getItem("authorization");

  console.log("JWT Token in Loan component:", jwtToken);
  console.log("CSRF Token in Loan component:", csrfToken);

  useEffect(() => {
    if (!jwtToken || !csrfToken) {
      console.error("JWT or CSRF token missing");
      return;
    }

    axios.get("http://localhost:8080/auth/loans", {
      headers: {
        Authorization: jwtToken,
        'X-XSRF-TOKEN': csrfToken
      },
      withCredentials: true
    })
    .then(response => {
      console.log("Loans fetched:", response.data);
      setLoans(response.data);
    })
    .catch(err => {
      console.error("Error fetching loans", err);
      alert("Session expired. Please log in again.");
      navigate("/login");
    });
  }, []);

  return (
    <div className="container mt-4">
      <Link to={"/home"}>
        <button className="btn btn-primary mb-3">Back to Home</button>
      </Link>

      <h3>Your Loans</h3>
      {loans.length > 0 ? (
        <div className="row">
          {loans.map((loan, index) => (
            <div key={index} className="card mb-3 p-3 shadow-sm">
              <p><strong>Loan Number:</strong> {loan.loanNumber}</p>
              <p><strong>Loan Type:</strong> {loan.loanType}</p>
              <p><strong>Total Loan:</strong> ₹{loan.totalLoan}</p>
              <p><strong>Amount Paid:</strong> ₹{loan.amountPaid}</p>
              <p><strong>Outstanding:</strong> ₹{loan.outstandingAmount}</p>
              <p><strong>Start Date:</strong> {loan.startDt}</p>
            </div>
          ))}
        </div>
      ) : (
        <p>Loading or no loans available...</p>
      )}
    </div>
  );
}
