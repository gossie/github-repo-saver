import { useEffect, useState } from "react"
import { Link, useNavigate, useParams } from "react-router-dom"
import { getRepositoriesByUser, getUser } from "../api-services"
import FavoriteComponent from "../components/FavoriteComponent"
import GitHubRepositoryComponent from "../components/GitHubRepositoryComponent"
import { GitHubRepository, User } from "../model"

export default function UserPage() {

    const [user, setUser] = useState<User>()
    const [errorMessage, setErrorMessage] = useState('');
    const [ownRepositories, setOwnRepositories] = useState<Array<GitHubRepository>>([]);

    const { username } = useParams()
    const nav = useNavigate()
 
    useEffect(() => {
        getUser(username!)
            .then((user => setUser(user)))
            .catch(() => setErrorMessage(`The user ${username} cannot be found. You will be redirected to the login page.`))

        getRepositoriesByUser(username!)
            .then(repositories => setOwnRepositories(repositories))
    }, [username])

    useEffect(() => {
        if (errorMessage) {
            setTimeout(() => nav('/'), 5000)
        }
    }, [errorMessage, nav])

    return (
        <div>
            { errorMessage
                ? 
                    <div>{errorMessage}</div>
                :
                    <div>
                        {user &&
                            <div>
                                <div>
                                    User: {username}
                                </div>
                                <div>
                                    <Link to={`/search/${username}`}>Search for repositories</Link>
                                </div>
                                <div>
                                    <h3>Favorites</h3>
                                    { user.favoriteRepositories?.map(f => <FavoriteComponent key={f.repositoryName} user={user} favorite={f} onFavoriteDeletion={setUser} />) }
                                </div>
                                <div>
                                    <h3>Own repositories</h3>
                                    { ownRepositories.map(repo => <GitHubRepositoryComponent key={repo.name} user={user!} gitHubRepository={repo} onFavoriteAdd={setUser} />) }
                                </div>
                            </div>
                        }
                    </div>
            }
            
        </div>
    )
}