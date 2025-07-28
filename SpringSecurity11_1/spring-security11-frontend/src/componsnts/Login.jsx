import axios from 'axios'
import React, { useEffect } from 'react'
import { useCsrfToken } from '../hooks/useCsrfToken';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';

export default function Login() {

  const navgateTo= useNavigate();

  const csrfToken = useCsrfToken();

  console.log("CSRF Token in Login component:", csrfToken);
  


  const { register, handleSubmit, formState: { errors } } = useForm();

  const onFormSubmit = async (data) => {
    try {
      const response = await axios.post('http://localhost:8080/api/auth/login', data,
        { withCredentials: true }
      ).then((response) => {
        console.log("Login successful", response.data);
        alert("Login successful");
        const { username, roles } = response.data;
        console.log(response.headers);
        const jwtToken = response.headers["authorization"]; // lowercase!
        console.log("JWT Token:", jwtToken);
        sessionStorage.setItem("user", username);

        sessionStorage.setItem("roles", JSON.stringify(roles));
        sessionStorage.setItem("authorization", "Bearer "+jwtToken);
        sessionStorage.setItem("XSRF-TOKEN", csrfToken);
        console.log(sessionStorage.getItem("authorization"));

        navgateTo('/home');
        
      })
    } catch (error) {
      console.error("Login failed", error);
      alert("Login failed. Please check your credentials.");
    }
  }


  return (
    <div>
      <form onSubmit={handleSubmit(onFormSubmit)} className="container mt-5">
        <div className="mb-3">
          <label htmlFor="exampleInputEmail1" className="form-label">Email address</label>
          <input type="email" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
          {...register("email",{required: "Email is required"})}
          />
          <div id="emailHelp" className="form-text">We'll never share your email with anyone else.</div>
        </div>
        <div className="mb-3">
          <label htmlFor="exampleInputPassword1" className="form-label">Password</label>
          <input type="password" className="form-control" id="exampleInputPassword1"
          {...register("password",{required: "password is required"})}
          />
        </div>
        
        <button type="submit" className="btn btn-primary">Submit</button>
      </form>
    </div>
  )
}
