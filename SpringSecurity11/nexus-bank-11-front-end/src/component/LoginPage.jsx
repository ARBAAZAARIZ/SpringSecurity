import { useForm } from "react-hook-form";
import axios from "axios";
import useCsrfToken from "../hooks/useCsrfToken";
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
    const { register, handleSubmit } = useForm();
    const navigate = useNavigate();

    // Fetch CSRF token from the server
    const csrfToken = useCsrfToken();

    const onSubmit = async (data) => {
        if (!csrfToken) {
            alert("CSRF token not found. Please try again.");
            return;
        }
        // Add CSRF token to the request headers
        try {
            const response = await axios.post("http://localhost:8080/auth/login", data,
                {
                    headers: {
                        "X-XSRF-TOKEN": csrfToken
                    }, withCredentials: true
                });
            if (response.status === 200) {

                navigate("/profile");


            }


        } catch (error) {
            alert("Login failed! Invalid credentials.");
        }
    };

    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleSubmit(onSubmit)}>
                <input type="email" placeholder="Email" {...register("email")} required />
                <input type="password" placeholder="Password" {...register("password")} required />
                <button type="submit">Login</button>
            </form>
        </div>
    );
};

export default LoginPage;
