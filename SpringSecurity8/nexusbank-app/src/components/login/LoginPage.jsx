import React, { useEffect } from "react";
import { useTheme } from "../context/ThemeContext";
import { Link, useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import axios from "axios";
import Footer from "../footer/Footer";
import { UseXcrfToken } from "../../hooks/UseXcrfToken";

export default function LoginPage() {
  const { theme } = useTheme();
  const navigate = useNavigate();
  const { register, handleSubmit, formState: { errors } } = useForm();

  useEffect(() => {
    axios.get("http://localhost:8080/api/auth/csrf-token", { withCredentials: true })
      .then(() => console.log("CSRF Token retrieved successfully"))
      .catch(() => console.log("Error while retrieving CSRF Token"))
  }, []);
  
  const csrfToken = UseXcrfToken();
  console.log("CSRF Token", csrfToken);

  const onFormSubmit = async (data) => {
    try {
      const response = await axios.post("http://localhost:8080/auth/login", data, {
        headers: { "X-XSRF-TOKEN": csrfToken },
        withCredentials: true,
      });
      alert("Login successful!");
      navigate("/profile");
    } catch (err) {
      console.error("Error:", err);
      alert("Failed to login. Please check your credentials.");
    }
  };

  return (
    <div>
      <div style={{
        minHeight: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        background: theme === "light"
          ? "linear-gradient(to right, #fff3eb, #ffe5d9)"  // Updated light theme
          : "linear-gradient(to right, #7e2d94, #c0392b)", // Dark theme
        padding: "3rem",
        width: "100%",
        position: "relative",
        overflow: "hidden"
      }} className="caveat">
        
        {/* Star Animation */}
        <style>
          {`
            @keyframes twinkle {
              0% { opacity: 0.2; }
              50% { opacity: 1; }
              100% { opacity: 0.2; }
            }

            .stars::before {
              content: '';
              position: absolute;
              top: 0;
              left: 0;
              width: 100%;
              height: 100%;
              background: transparent;
              pointer-events: none;
            }

            .stars span {
              position: absolute;
              background: #fff;
              border-radius: 50%;
              animation: twinkle 1.5s infinite;
            }

            ${theme === 'dark' ? `
              .stars span {
                width: 2px;
                height: 2px;
              }
            ` : ''}
          `}
        </style>

        {theme === 'dark' && (
          <div className="stars">
            {[...Array(150)].map((_, index) => (
              <span 
                key={index}
                style={{
                  top: `${Math.random() * 100}%`,
                  left: `${Math.random() * 100}%`,
                  width: `${Math.random() * 2 + 1}px`,
                  height: `${Math.random() * 2 + 1}px`
                }}
              />
            ))}
          </div>
        )}

        <form onSubmit={handleSubmit(onFormSubmit)}
          style={{
            width: "400px",
            borderRadius: "12px",
            backgroundColor: theme === "light" ? "#fff" : "#1c1c1c",
            boxShadow: theme === "light"
              ? "0px 8px 20px rgba(0, 0, 0, 0.2)"
              : "0px 8px 20px rgba(255, 255, 255, 0.2)",
            padding: "2rem",
            display: "flex",
            flexDirection: "column",
            gap: "1.5rem",
            position: "relative",
            zIndex: 1
          }}
        >
          {/* Rest of your form remains exactly the same */}
          <h2 className="fs-1 fw-bold text-center" style={{ color: theme === "light" ? "#333" : "#f8f9fa" }}>
            Login
          </h2>

          <div>
            <label className="fs-4 fw-bold" htmlFor="email" style={{ color: theme === "light" ? "#333" : "#f8f9fa" }}>
              Email Address
            </label>
            <input
              type="email"
              id="email"
              className="form-control fs-5"
              style={{
                borderRadius: "8px",
                padding: "10px",
                backgroundColor: theme === "light" ? "#f8f9fa" : "#333",
                color: theme === "light" ? "#000" : "#fff",
                border: theme === "light" ? "1px solid #ccc" : "1px solid #555",
              }}
              {...register("email", { required: "Email is required" })}
            />
            {errors.email && <p style={{ color: "red", fontSize: "1rem" }}>{errors.email.message}</p>}
          </div>

          <div>
            <label className="fs-4 fw-bold" htmlFor="password" style={{ color: theme === "light" ? "#333" : "#f8f9fa" }}>
              Password
            </label>
            <input
              type="password"
              id="password"
              className="form-control fs-5"
              style={{
                borderRadius: "8px",
                padding: "10px",
                backgroundColor: theme === "light" ? "#f8f9fa" : "#333",
                color: theme === "light" ? "#000" : "#fff",
                border: theme === "light" ? "1px solid #ccc" : "1px solid #555",
              }}
              {...register("password", {
                required: "Password is required",
                pattern: {
                  value: /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).{8,}$/,
                  message: "Invalid Password",
                },
              })}
            />
            {errors.password && <p style={{ color: "red", fontSize: "1rem" }}>{errors.password.message}</p>}
          </div>

          <button type="submit" className="btn btn-primary fs-3 fw-bold"
            style={{
              padding: "10px",
              borderRadius: "8px",
              backgroundColor: theme === "light" ? "#007bff" : "#17a2b8",
              border: "none",
              color: "#fff",
              cursor: "pointer",
              transition: "background 0.3s",
            }}
          >
            Login
          </button>

          <p className="fs-5 text-center" style={{ color: theme === "light" ? "#333" : "#f8f9fa" }}>
            Don't have an account?{" "}
            <Link to="/register" className="fw-bold"
              style={{ color: theme === "light" ? "#007bff" : "#17a2b8" }}>
              Register
            </Link>
          </p>
        </form>
      </div>
      <Footer />
    </div>
  );
}