import { Link } from 'react-router-dom';
import { useState } from 'react';
import { FaUser, FaSun, FaMoon } from 'react-icons/fa';
import { useTheme } from '../context/ThemeContext';

const Navbar = () => {
  const { theme, toggleTheme } = useTheme();
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const handleLogout = (e) => {
    e.preventDefault();
    window.location.href = 'http://localhost:8080/logout';
  };

  const navLinks = [
    { path: '/home', name: 'Home' },
    { path: '/notices', name: 'Notices' },
    { path: '/contact', name: 'Contact' },
    { path: '/login', name: 'Login' },
    { path: '/profile', name: <FaUser /> },
    { path: '/logout', name: 'Logout', onClick: handleLogout },
  ];

  return (
    <nav 
      className="navbar navbar-expand-lg fixed-top winky-rough"
      style={{ 
        background: theme === 'dark' 
          ? "linear-gradient(to right, #7e2d94, #c0392b)" 
          : "linear-gradient(to right, #fff3eb, #ffe5d9)",
        transition: 'all 0.3s ease',
        position: 'relative',
        overflow: 'hidden'
      }}
    >
      <style>
        {`
          @keyframes twinkle {
            0% { opacity: 0.2; }
            50% { opacity: 1; }
            100% { opacity: 0.2; }
          }

          @keyframes slideIn {
            from {
              opacity: 0;
              transform: translateY(-10px);
            }
            to {
              opacity: 1;
              transform: translateY(0);
            }
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

          /* Rest of your existing styles remain the same */
          .nav-link {
            transition: all 0.2s ease !important;
            position: relative;
            display: flex;
            align-items: center;
            gap: 12px;
            margin: 0 20px;
          }

          .nav-link::after {
            content: '';
            position: absolute;
            width: 0;
            height: 2px;
            bottom: 0;
            left: 0;
            background-color: ${theme === 'dark' ? '#fff' : '#4a4a4a'};
            transition: width 0.3s ease;
          }

          .nav-link:hover::after {
            width: 100%;
          }

          .nav-item {
            animation: slideIn 0.3s ease forwards;
          }

          .navbar-brand {
            display: flex;
            align-items: center;
            letter-spacing: 2.5px !important;
            transition: transform 0.3s ease;
            margin-left: 2rem;
            font-size: 2.5rem;
            font-weight: 800;
            color: ${theme === 'dark' ? '#fff' : '#4a4a4a'};
          }

          .brand-icon {
            height: 60px;
            margin-right: 20px;
            border-radius: 15%;
            filter: ${theme === 'dark' ? 'brightness(1.2)' : 'none'};
          }

          .theme-toggle {
            display: flex;
            align-items: center;
            background: transparent !important;
            border: none !important;
            padding: 0.5rem 1rem;
            margin-right: 2rem;
          }

          .theme-icon {
            font-size: 1.7rem;
            transition: all 0.3s ease;
          }

          .theme-toggle:hover .theme-icon {
            transform: scale(1.2);
            filter: brightness(1.2);
          }

          .navbar-nav {
            margin-right: 4rem;
          }
        `}
      </style>

      {theme === 'dark' && (
        <div className="stars">
          {[...Array(15)].map((_, index) => (
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

      <div className="container-fluid">
        <Link className="navbar-brand" to="/home">
          <img 
            src="nexus-brand-icon.png" 
            alt="Nexus Bank" 
            className="brand-icon"
          />
          Nexus Bank
        </Link>

        <button
          className="navbar-toggler ms-auto me-5"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
          onClick={() => setIsMenuOpen(!isMenuOpen)}
        >
          <span className={`navbar-toggler-icon ${isMenuOpen ? 'cross' : ''}`} />
        </button>

        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ms-auto align-items-lg-center me-5">
            {navLinks.map((link, index) => (
              <li 
                className="nav-item" 
                key={link.path}
                style={{ animationDelay: `${index * 0.1}s` }}
              >
                {link.path === '/logout' ? (
                  <a
                    href="http://localhost:8080/logout"
                    className="nav-link"
                    style={{
                      color: theme === 'dark' ? '#fff' : '#4a4a4a',
                      fontWeight: 600
                    }}
                    onClick={link.onClick}
                  >
                    {link.name}
                  </a>
                ) : (
                  <Link
                    className="nav-link"
                    to={link.path}
                    style={{
                      color: theme === 'dark' ? '#fff' : '#4a4a4a',
                      fontWeight: 600
                    }}
                  >
                    {link.name}
                  </Link>
                )}
              </li>
            ))}
            
            <li className="nav-item ms-lg-4">
              <button
                onClick={toggleTheme}
                className="theme-toggle"
                style={{
                  color: theme === 'dark' ? '#fff' : '#4a4a4a',
                }}
              >
                {theme === 'dark' ? (
                  <FaSun className="theme-icon" />
                ) : (
                  <FaMoon className="theme-icon" />
                )}
              </button>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;