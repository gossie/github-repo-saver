import { useEffect, useState } from "react"
import { getAuthData } from "../api-services"

export default function LoginPage() {

    const [gitHubClientId, setGitHubClientId] = useState('');

    useEffect(() => {
        getAuthData()
            .then(authData => setGitHubClientId(authData.gitHubClientId))
    })

    return (
        <div>
            { gitHubClientId && <a href={`https://github.com/login/oauth/authorize?client_id=${gitHubClientId}`}>Login with GitHub</a> }
        </div>
    )
}