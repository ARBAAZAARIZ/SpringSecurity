import axios from "axios";
import { useEffect, useState } from "react"


const useCsrfToken = () => {
    const [csrfToken, setCsrfToken] = useState(null);
    console.log(csrfToken);
    

    useEffect(() => {
        const fetchCsrfToken = async () => {

            try {

                await axios.get("http://localhost:8080/auth/dummy", { withCredentials: true });

                // Extract CSRF token from cookies
                const token = document.cookie
                    .split('; ')
                    .find(row => row.startsWith("XSRF-TOKEN="))
                    ?.split('=')[1];
                if (token) {
                    sessionStorage.setItem("csrfToken", token);
                    setCsrfToken(token);
                    console.log(token);
                    
                }

            } catch (error) {
                console.log("Failed to fetch CSRF token:", error);
            }



        };
        fetchCsrfToken();
    },[]);
    return csrfToken;
}
export default useCsrfToken;