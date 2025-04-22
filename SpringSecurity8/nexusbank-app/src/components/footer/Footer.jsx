import React from "react";
import { useTheme } from "../context/ThemeContext";
import { FaLinkedin, FaGithub, FaFacebook, FaInstagram } from "react-icons/fa"; // Importing icons
import "../../assets/fonts.css"
export default function Footer() {
  const { theme } = useTheme();

  return (
    <footer
      style={{
        width: "100%",
        padding: "1.5rem",
        textAlign: "center",
        background: theme === "light" ? "#f5f5dc" : "#2c3e50",
        color: theme === "light" ? "#333" : "#fff",
        borderTop: "2px solid rgba(0, 0, 0, 0.1)",
        position: "relative",
      }}
      className='winky-rough'
    >
      <p style={{ fontSize: "1rem", fontWeight: "bold" }}>
        © {new Date().getFullYear()} Nexus Bank. All Rights Reserved.
      </p>

      {/* Links Section */}
      <div style={{ marginTop: "1rem", display: "flex", justifyContent: "center", gap: "1rem" }}>
        <a
          href="#"
          style={{
            textDecoration: "none",
            fontSize: "1rem",
            color: theme === "light" ? "#007bff" : "#f39c12",
          }}
        >
          Privacy Policy
        </a>
        <a
          href="#"
          style={{
            textDecoration: "none",
            fontSize: "1rem",
            color: theme === "light" ? "#007bff" : "#f39c12",
          }}
        >
          Terms of Service
        </a>
      </div>

      {/* Social Media Icons Section */}
      <div style={{ marginTop: "1.5rem", display: "flex", justifyContent: "center", gap: "1rem" }}>
        <a href="www.linkedin.com/in/arbaazaariz" target="_blank" rel="noopener noreferrer">
          <FaLinkedin size={30} color={theme === "light" ? "#0077b5" : "#f1c40f"} />
        </a>
        <a href="https://github.com/ARBAAZAARIZ" target="_blank" rel="noopener noreferrer">
          <FaGithub size={30} color={theme === "light" ? "#333" : "#fff"} />
        </a>
        <a href="https://www.facebook.com/YOUR_PROFILE" target="_blank" rel="noopener noreferrer">
          <FaFacebook size={30} color={theme === "light" ? "#1877f2" : "#e84393"} />
        </a>
        <a href="https://www.instagram.com/lu__ci_fer_" target="_blank" rel="noopener noreferrer">
          <FaInstagram size={30} color={theme === "light" ? "#E1306C" : "#FFD700"} />
        </a>
      </div>
    </footer>
  );
}