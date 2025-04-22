import React from 'react';
import { useTheme } from '../context/ThemeContext';
import { useForm } from 'react-hook-form';
import axios from 'axios';
import { Link } from 'react-router-dom';
import "../../assets/fonts.css"

export default function Register() {
  const { theme } = useTheme();
  console.log(theme);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onFormSubmit = async (data) => {
    try {
      console.log('Form Data:', data);
      const response = await axios.post("http://localhost:8080/register", data, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      console.log(response.data);
      alert("Registration successful!");
    } catch (err) {
      console.error("Error:", err);
      alert("Failed to register.");
    }
  };

  return (
    <div
      style={{
        height: "105vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        background: `${theme === 'light' ? 'linear-gradient(to right, #fdfd96, #c1fba4)' : 'linear-gradient(to right, #8e44ad, #e74c3c)'}`,
        padding: "2rem",
        marginTop: "6rem",
      }}
      className='winky-rough'
    >
      <div
        style={{
          height: "auto",
          width: "40vw",
          background: theme === 'light' ? "#f8f9fa" : "#2c3e50", // Soft light or dark color depending on theme
          borderRadius: "20px",
          boxShadow: theme === 'light'
            ? "0px 8px 20px rgba(0, 0, 0, 0.2)" // Subtle shadow for light theme
            : "0px 8px 20px rgba(0, 0, 0, 0.7)", // Deeper shadow for dark theme
          padding: "2rem",
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          marginTop: "2rem",
        }}
      >
        <h2 className='caveat' style={{ marginBottom: "1.5rem", color: `${theme === 'light' ? '#333' : '#fff'}` }}>Register Here!</h2>
        <form
          onSubmit={handleSubmit(onFormSubmit)}
          style={{
            width: "100%",
            display: "flex",
            flexDirection: "column",
            gap: "1.5rem",
          }}
          
        >
          {/* Name Field */}
          <div>
            <label htmlFor="name" style={{ fontSize: "1.2rem", color: `${theme === 'light' ? '#666' : '#ccc'}` }}>
              Name
            </label>
            <input
              type="text"
              id="name"
              style={{
                width: "100%",
                padding: "0.8rem",
                borderRadius: "10px",
                border: `1px solid ${theme === 'light' ? '#ccc' : '#555'}`,
                marginTop: "0.5rem",
                background: theme === 'light' ? "#fff" : "#34495e",
                color: theme === 'light' ? "#000" : "#fff",
              }}
              {...register("name", {
                required: "Name is required",
                minLength: {
                  value: 3,
                  message: "Name must be at least 3 characters long",
                },
              })}
            />
            {errors.name && <p style={{ color: "red" }}>{errors.name.message}</p>}
          </div>

          {/* Email Field */}
          <div>
            <label htmlFor="email" style={{ fontSize: "1.2rem", color: `${theme === 'light' ? '#666' : '#ccc'}` }}>
              Email
            </label>
            <input
              type="email"
              id="email"
              style={{
                width: "100%",
                padding: "0.8rem",
                borderRadius: "10px",
                border: `1px solid ${theme === 'light' ? '#ccc' : '#555'}`,
                marginTop: "0.5rem",
                background: theme === 'light' ? "#fff" : "#34495e",
                color: theme === 'light' ? "#000" : "#fff",
              }}
              {...register("email", {
                required: "Email is required",
                pattern: {
                  value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
                  message: "Enter a valid email address",
                },
              })}
            />
            {errors.email && <p style={{ color: "red" }}>{errors.email.message}</p>}
          </div>

          {/* Mobile Number Field */}
          <div>
            <label htmlFor="mobileNumber" style={{ fontSize: "1.2rem", color: `${theme === 'light' ? '#666' : '#ccc'}` }}>
              Mobile Number
            </label>
            <input
              type="tel"
              id="mobileNumber"
              style={{
                width: "100%",
                padding: "0.8rem",
                borderRadius: "10px",
                border: `1px solid ${theme === 'light' ? '#ccc' : '#555'}`,
                marginTop: "0.5rem",
                background: theme === 'light' ? "#fff" : "#34495e",
                color: theme === 'light' ? "#000" : "#fff",
              }}
              {...register("mobileNumber", {
                required: "Mobile number is required",
                pattern: {
                  value: /^[0-9]{10}$/,
                  message: "Mobile number must be 10 digits",
                },
              })}
            />
            {errors.mobileNumber && (
              <p style={{ color: "red" }}>{errors.mobileNumber.message}</p>
            )}
          </div>

          {/* Password Field */}
          <div>
            <label htmlFor="pwd" style={{ fontSize: "1.2rem", color: `${theme === 'light' ? '#666' : '#ccc'}` }}>
              Password
            </label>
            <input
              type="password"
              id="pwd"
              style={{
                width: "100%",
                padding: "0.8rem",
                borderRadius: "10px",
                border: `1px solid ${theme === 'light' ? '#ccc' : '#555'}`,
                marginTop: "0.5rem",
                background: theme === 'light' ? "#fff" : "#34495e",
                color: theme === 'light' ? "#000" : "#fff",
              }}
              {...register("pwd", {
                required: "Password is required",
                pattern: {
                  value: /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).{8,}$/,
                  message:
                    "Password must contain at least 8 characters, including uppercase, lowercase, numeric, and special characters",
                },
              })}
            />
            {errors.pwd && <p style={{ color: "red" }}>{errors.pwd.message}</p>}
          </div>

          {/* Role Field */}
          <div>
            <label htmlFor="role" style={{ fontSize: "1.2rem", color: `${theme === 'light' ? '#666' : '#ccc'}` }}>
              Role
            </label>
            <select
              id="role"
              {...register("role", { required: "Role is required" })}
              defaultValue="user"
              style={{
                width: "100%",
                padding: "0.8rem",
                borderRadius: "10px",
                border: `1px solid ${theme === 'light' ? '#ccc' : '#555'}`,
                marginTop: "0.5rem",
                background: theme === 'light' ? "#fff" : "#34495e",
                color: theme === 'light' ? "#000" : "#fff",
              }}
            >
              <option value="user">User</option>
              <option value="admin">Admin</option>
            </select>
            {errors.role && <p style={{ color: "red" }}>{errors.role.message}</p>}
          </div>

          {/* Submit Button */}
          <button
            type="submit"
            style={{
              width: "100%",
              padding: "1rem",
              background: "linear-gradient(to right, #6a11cb, #2575fc)",
              color: "white",
              fontSize: "1.2rem",
              border: "none",
              borderRadius: "10px",
              cursor: "pointer",
              transition: "transform 0.2s",
            }}
            onMouseOver={(e) => (e.target.style.transform = "scale(1.05)")}
            onMouseOut={(e) => (e.target.style.transform = "scale(1)")}
          >
            Register
          </button>
        </form>
        <div>
                <Link to={"/login"}
            style={{
              display: "inline-block",
              marginTop: "1rem",
              fontSize: "1.2rem",
              fontWeight: "bold",
              color: theme === 'light' ? "#007bff" : "#9b59b6",
              textDecoration: "none",
              transition: "color 0.3s ease",
            }}onMouseOver={(e) => (e.target.style.transform = "scale(1.05)")}
            onMouseOut={(e) => (e.target.style.transform = "scale(1)")}>Already a user? Sign up</Link>
            </div>
      </div>

            

    </div>
  );
}

