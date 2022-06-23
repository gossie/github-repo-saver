import { useEffect, useState } from "react"
import { Link, useParams } from "react-router-dom"
import { getUser } from "../api-services"
import { User } from "../model"

export default function UserPage() {

    const [user, setUser] = useState<User>()

    const { username } = useParams()

    useEffect(() => {
        getUser(username!)
            .then((user => setUser(user)))
    }, [username])

    return (
        <div>
            {user &&
                <div>
                    <div>
                        User: {username}
                    </div>
                    <div>
                        <Link to="/search">Search for repositories</Link>
                    </div>
                    <div>
                        <h3>Favorites</h3>
                    </div>
                </div>
            }
        </div>
    )
}