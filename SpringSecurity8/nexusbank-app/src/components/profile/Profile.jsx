import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { UseXcrfToken } from "../../hooks/UseXcrfToken";

export default function Profile() {
  const navigate = useNavigate();
  
  // Manage profile data and loading state
  const [profile, setProfile] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const csrfToken =UseXcrfToken();

  // Fetch profile details
  const fetchProfile = async () => {
    try {
      const response = await axios.get("http://localhost:8080/profile",
        {
            headers:{"X-XSRF-TOKEN":csrfToken},
            withCredentials: true  // Ensure cookies/session are sent
          },
      );

      console.log("User Profile Data:", response.data);
      setProfile(response.data);
    } catch (err) {
      console.error("Error fetching profile:", err);
      setError("Failed to load profile. Redirecting to login...");
      setTimeout(() => navigate("/login"), 100); // Redirect to login after delay
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchProfile();
  }, []);

  // Handle loading state
  if (loading) {
    return <h2 style={{ textAlign: "center", marginTop: "50px" }}>Loading Profile...</h2>;
  }

  // Handle error state
  if (error) {
    return <h2 style={{ textAlign: "center", color: "red", marginTop: "50px" }}>{error}</h2>;
  }

  return (
    <div style={{
      maxWidth: "600px",
      margin: "50px auto",
      padding: "20px",
      borderRadius: "10px",
      boxShadow: "0px 8px 20px rgba(0, 0, 0, 0.2)",
      background: "#fff",
      textAlign: "center",
      marginTop: "8rem",
    }}>
      <h2>Welcome, {profile.name}!</h2>

      {/* Account Info */}
      {profile.account && (
        <div>
          <h3>Account Details:</h3>
          <p><strong>Account Number:</strong> {profile.account.accountNumber}</p>
          <p><strong>Balance:</strong> ${profile.account.balance}</p>
        </div>
      )}

      {/* Card Info */}
      {profile.card && (
        <div>
          <h3>Card Details:</h3>
          <p><strong>Card Number:</strong> {profile.card.cardNumber}</p>
          <p><strong>Expiry:</strong> {profile.card.expiryDate}</p>
          <p><strong>Card Type</strong>{profile.card.cardType}</p>
          <p><strong>Total Limit</strong>{profile.card.totalLimit}</p>
          <p><strong>Available Ammount </strong>{profile.card.availableAmount}</p>
        </div>
      )}

      {/* Loan Info */}
      {profile.loans && profile.loans.length > 0 ? (
        <div>
          <h3>Loans:</h3>
          {profile.loans.map((loan, index) => (
            <p key={index}>
              <strong>Loan ID:</strong> {loan.loanId} | <strong>Amount:</strong> ${loan.amount}
            </p>
          ))}
        </div>
      ) : <p>No loans found.</p>}

      {/* Transaction Info */}
      {profile.accountTransactions && profile.accountTransactions.length > 0 ? (
        <div>
          <h3>Recent Transactions:</h3>
          {profile.accountTransactions.slice(0, 5).map((transaction, index) => (
            <p key={index}>
              <strong>Type:</strong> {transaction.transactionType} | <strong>Amount:</strong> ${transaction.amount}
            </p>
          ))}
        </div>
      ) : <p>No recent transactions.</p>}

      <p style={{ marginTop: "20px", color: "green", fontWeight: "bold" }}>{profile.message}</p>
    </div>
  );
}