import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"

export default function LoginPage() {

    const [username, setUsername] = useState(localStorage.getItem('username') ?? '')

    const nav = useNavigate()

    useEffect(() => localStorage.setItem('username', username), [username])

    const login = () => nav(`/user/${username}`)

    return (
        <div>
            <div>
                <input type="text" placeholder="Your GitHub username" value={username} onChange={ev => setUsername(ev.target.value)} />
            </div>
            <div>
                <button onClick={login}>Login</button>
            </div>
        </div>
        
    )
}