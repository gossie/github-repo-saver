import { FormEvent, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"

export default function LoginPage() {

    const [username, setUsername] = useState(localStorage.getItem('username') ?? '')

    const nav = useNavigate()

    useEffect(() => localStorage.setItem('username', username), [username])

    const login = (ev: FormEvent) => {
        ev.preventDefault();
        nav(`/user/${username}`)
    }

    return (
        <div>
            <form onSubmit={login}>
                <div>
                    <input type="text" placeholder="Your GitHub username" value={username} onChange={ev => setUsername(ev.target.value)} />
                </div>
                <div>
                    <input type="submit" value="Login" />
                </div>
            </form>
        </div>
        
    )
}