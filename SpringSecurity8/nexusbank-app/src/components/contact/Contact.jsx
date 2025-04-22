import React from 'react';
import { useTheme } from '../context/ThemeContext';
import { FaPhoneAlt, FaEnvelope, FaMapMarkerAlt } from "react-icons/fa";
import { useForm } from 'react-hook-form';
import axios from 'axios';
import Footer from '../footer/Footer';
import "../../assets/fonts.css"

export default function Contact() {
    const { theme } = useTheme();
    const { register, handleSubmit, formState: { errors } } = useForm();

    const onFormSubmit = async (data) => {
        const response = await axios.post("http://localhost:8080/contact", data, {
            headers: { "Content-Type": "application/json" }
        }).catch((err) => {
            console.log(err);
            alert("Failed to send message.");
        });
        alert("Message sent successfully!");
    };

    return (
        <div style={{  position: "relative", minHeight: "100vh" }}>
            <div className='winky-rough'
                style={{
                    minHeight: "calc(100vh - 4.5rem)",
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "center",
                    background: theme === 'light' 
                        ? "linear-gradient(to right, #fff3eb, #ffe5d9)"  // Updated light theme
                        : "linear-gradient(to right, #7e2d94, #c0392b)",
                    padding: "3rem",
                    position: "relative",
                    overflow: "hidden"
                }}
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

                <div className="container text-center" style={{ position: "relative", zIndex: 1 }}>
                    <div className="row" style={{ display: "flex", justifyContent: "center", gap: "7rem" }}>
                        
                        {/* Left-Side Form with Submit Button */}
                        <div className="col" style={{
                            background: theme === "light" ? "rgba(255, 255, 255, 0.9)" : "rgba(52, 73, 94, 0.9)",
                            padding: "2rem",
                            borderRadius: "10px",
                            boxShadow: "0px 4px 12px rgba(0,0,0,0.1)",
                            width: "80%",
                            maxWidth: "450px"
                        }}>
                            <form onSubmit={handleSubmit(onFormSubmit)} style={{ display: "flex", flexDirection: "column", gap: "1.5rem" }}>
                                <div className="form-floating">
                                    <input {...register("contactName")} type="text" className="form-control" placeholder="Name" />
                                    <label>Name</label>
                                </div>
                                <div className="form-floating">
                                    <input {...register("contactEmail")} type="email" className="form-control" placeholder="Email" />
                                    <label>Email (name@gmail.com)</label>
                                </div>
                                <div className="form-floating">
                                    <input {...register("subject")} type="text" className="form-control" placeholder="Subject" />
                                    <label>Subject</label>
                                </div>
                                <div className="form-floating">
                                    <textarea {...register("message")} className="form-control" placeholder="Message" style={{ height: "120px" }}></textarea>
                                    <label>Message</label>
                                </div>
                                <button type="submit" style={{
                                    width: "100%",
                                    padding: "1rem",
                                    background: "linear-gradient(to right, #6a11cb, #2575fc)",
                                    color: "white",
                                    fontSize: "1.2rem",
                                    border: "none",
                                    borderRadius: "10px",
                                    cursor: "pointer",
                                    transition: "transform 0.2s",
                                    fontWeight: "bold"
                                }} onMouseOver={(e) => (e.target.style.transform = "scale(1.05)")}
                                    onMouseOut={(e) => (e.target.style.transform = "scale(1)")}>
                                    Send Message
                                </button>
                            </form>
                        </div>

                        {/* Right-Side Contact Details */}
                        <div className="col" style={{
                            display: "flex",
                            flexDirection: "column",
                            alignItems: "flex-start",
                            justifyContent: "flex-start",
                            background: theme === "light" ? "rgba(255, 255, 255, 0.9)" : "rgba(44, 62, 80, 0.9)",
                            padding: "2rem",
                            borderRadius: "10px",
                            boxShadow: "0px 4px 12px rgba(0,0,0,0.1)",
                            color: theme === "light" ? "#333" : "#fff",
                            gap: "2rem",
                            width: "80%",
                            maxWidth: "450px",
                            textAlign: "left"
                        }}>
                            <h3 style={{ marginBottom: "10px" }}>Contact Info</h3>
                            <div style={{ display: "flex", alignItems: "center", gap: "15px", marginBottom: "1rem" }}>
                                <FaPhoneAlt size={30} color={theme === "light" ? "#007bff" : "#f39c12"} />
                                <span style={{ fontSize: "1.4rem", fontWeight: "bold" }}>+91 98765 43210</span>
                            </div>
                            <div style={{ display: "flex", alignItems: "center", gap: "15px", marginBottom: "1rem" }}>
                                <FaEnvelope size={30} color={theme === "light" ? "#28a745" : "#e67e22"} />
                                <span style={{ fontSize: "1.4rem", fontWeight: "bold" }}>contact@nexusbank.com</span>
                            </div>
                            <div style={{ display: "flex", alignItems: "center", gap: "15px" }}>
                                <FaMapMarkerAlt size={30} color={theme === "light" ? "#dc3545" : "#9b59b6"} />
                                <span style={{ fontSize: "1.4rem", fontWeight: "bold" }}>123, Nexus Tower, Mumbai, India</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    );
}