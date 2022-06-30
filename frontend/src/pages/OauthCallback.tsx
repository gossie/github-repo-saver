import { useEffect } from "react"
import { useLocation, useNavigate } from "react-router-dom";
import { gitHubCallback } from "../api-services";

export default function OauthCallback() {

    const code = new URLSearchParams(useLocation().search).get('code');
    const nav = useNavigate();

    useEffect(() => {
        gitHubCallback(code!)
            .then(loginResponse => localStorage.setItem('jwt', loginResponse.token))
            .then(() => nav('/user'))
    }, [code]);

    return (
        <div>...Loading...</div>
    )
}