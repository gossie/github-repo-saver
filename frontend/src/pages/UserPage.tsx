import { useEffect, useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import { getRepositoriesByUser, getUser } from "../api-services"
import FavoriteComponent from "../components/FavoriteComponent"
import GitHubRepositoryComponent from "../components/GitHubRepositoryComponent"
import { GitHubRepository, User } from "../model"

export default function UserPage() {

    const [user, setUser] = useState<User>()
    const [errorMessage, setErrorMessage] = useState('');
    const [ownRepositories, setOwnRepositories] = useState<Array<GitHubRepository>>([]);

    const nav = useNavigate()
 
    useEffect(() => {
        getUser()
            .then((user => setUser(user)))
            .catch(() => setErrorMessage(`The user cannot be found. You will be redirected to the login page.`))
    }, [])

    useEffect(() => {
        if (user) {
            getRepositoriesByUser(user.username)
            .then(repositories => setOwnRepositories(repositories))
        }
    }, [user])

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
                        {user ?
                            <div>
                                <div>
                                    User: {user.username}
                                </div>
                                <div>
                                    <Link to={'/search'}>Search for repositories</Link>
                                </div>
                                <div>
                                    <h3>Favorites</h3>
                                    { user.favoriteRepositories?.map(f => <FavoriteComponent key={f.repositoryName} user={user} favorite={f} onFavoriteDeletion={setUser} />) }
                                </div>
                            </div>
                            : <div>...Loading...</div>
                        }
                        <div>
                            <h3>Own repositories</h3>
                            { ownRepositories.length > 0 ? ownRepositories.map(repo => <GitHubRepositoryComponent key={repo.name} user={user!} gitHubRepository={repo} onFavoriteAdd={setUser} />) : <div>...Loading...</div> }
                        </div>
                    </div>
            }
            
        </div>
    )
}