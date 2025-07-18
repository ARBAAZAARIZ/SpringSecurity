import { useEffect, useState } from "react";
import axios from "axios";

export default function Profile() {
    const [profile, setProfile] = useState(null);
    console.log(profile);
    

    useEffect(() => {
        const fetchProfile = async () => {
    try {
        const token = sessionStorage.getItem("jwt"); // Get stored JWT

        if (!token) {
            console.error("JWT token missing, login first!");
            return;
        }

        const response = await axios.get("http://localhost:8080/auth/user", {
            headers: {
                "Authorization": `Bearer ${token}`
            },
            withCredentials: true
        });

        setProfile(response.data);
    } catch (error) {
        console.error("Failed to fetch profile:", error);
    }
};


        fetchProfile();
    }, []);

    return (
        <div>
            <h2>My Profile</h2>
            {profile ? (
                <div>
                    <p><strong>Email:</strong> {profile.email}</p>
                    <p><strong>Accounts:</strong> {profile.accounts ? JSON.stringify(profile.accounts) : "None"}</p>
                    <p><strong>Cards:</strong> {profile.card.length > 0 ? profile.card.join(", ") : "None"}</p>
                    <p><strong>Loans:</strong> {profile.loans.length > 0 ? JSON.stringify(profile.loans) : "None"}</p>
                    <p><strong>Message:</strong> {profile.message}</p>
                </div>
            ) : <p>Loading profile...</p>}
        </div>
    );
}
