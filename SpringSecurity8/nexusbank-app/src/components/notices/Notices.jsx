import React, { useState, useEffect } from 'react';
import { useTheme } from '../context/ThemeContext';
import axios from 'axios';
import "../../assets/fonts.css"
import Footer from '../footer/Footer';

export default function Notices() {
  const { theme } = useTheme();
  const [notices, setNotices] = useState([]);

  useEffect(() => {
    const fetchNotices = async () => {
      try {
        const response = await axios.get("http://localhost:8080/notices");
        setNotices(response.data);
        console.log("Notices:", response.data);
      } catch (err) {
        console.error("Error:", err);
      }
    };

    fetchNotices();
  }, []);

  return (
    <div>
      <div
        style={{
          minHeight: "100vh",
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          background: theme === 'light' 
            ? "linear-gradient(to right, #fff3eb, #ffe5d9)" // Updated light theme
            : "linear-gradient(to right, #7e2d94, #c0392b)",
          padding: "3rem",
          
          position: "relative",
          overflow: "hidden"
        }}
        className='winky-rough'
      >
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
            {[...Array(200)].map((_, index) => (
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

        <h1 style={{ 
          fontSize: "2rem", 
          fontWeight: "bold", 
          color: theme === "light" ? "#333" : "#fff", 
          marginBottom: "2rem",
          zIndex: 1
        }}>
          Latest Notices
        </h1>

        <div style={{ position: "relative", zIndex: 1, width: "100%" }}>
          {notices && notices.data && notices.data.map((notice, index) => (
            <div
              key={index}
              className="card text-center"
              style={{
                width: "80%",
                maxWidth: "700px",
                background: theme === "light" ? "rgba(255, 255, 255, 0.9)" : "rgba(44, 62, 80, 0.9)",
                color: theme === "light" ? "#000" : "#fff",
                borderRadius: "15px",
                boxShadow: "0px 4px 10px rgba(0,0,0,0.1)",
                padding: "1.5rem",
                marginBottom: "1.5rem",
                marginLeft: "auto",
                marginRight: "auto"
              }}
            >
              <div
                className="card-header"
                style={{
                  fontSize: "1.4rem",
                  fontWeight: "bold",
                  background: "transparent",
                  padding: "1rem",
                  borderRadius: "10px 10px 0px 0px",
                }}
              >
                {notice.noticeSummary}
              </div>

              <div className="card-body" style={{ padding: "1.2rem" }}>
                <h5 className="card-title" style={{ fontSize: "1.5rem", fontWeight: "bold" }}>
                  {notice.noticeDetails || "Special title treatment"}
                </h5>
              </div>

              <div
                className="card-footer"
                style={{
                  padding: "0.8rem",
                  fontSize: "0.9rem",
                  fontStyle: "italic",
                  background: "transparent",
                  borderRadius: "0px 0px 10px 10px",
                  color: theme === "light" ? "#333" : "#fff"
                }}
              >
                Valid till - {notice.noticeEndDt || "2 days ago"}
              </div>
            </div>
          ))}
        </div>
      </div>
      <Footer/>
    </div>
  );
}