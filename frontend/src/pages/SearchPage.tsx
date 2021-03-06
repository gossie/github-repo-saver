import { FormEvent, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getRepositoriesByUser, getUser } from "../api-services";
import GitHubRepositoryComponent from "../components/GitHubRepositoryComponent";
import { GitHubRepository, User } from "../model";

export default function SearchPage() {

    const [user, setUser] = useState<User>()
    const [errorMessage, setErrorMessage] = useState('');
    const [githubUsername, setGithubUsername] = useState('')
    const [gitHubRepositories, setGitHubRepositories] = useState<Array<GitHubRepository>>([])

    const nav = useNavigate()
 
    useEffect(() => {
        getUser()
            .then((user => setUser(user)))
            .catch(() => setErrorMessage(`The user cannot be found. You will be redirected to the login page.`))
    }, [])

    useEffect(() => {
        if (errorMessage) {
            setTimeout(() => nav('/'), 5000)
        }
    }, [errorMessage, nav])

    const searchRepositories = (ev: FormEvent) => {
        ev.preventDefault()

        getRepositoriesByUser(githubUsername)
            .then(repositories => setGitHubRepositories(repositories))
    }

    return (
        <div>
            { errorMessage
                ? 
                    <div>{errorMessage}</div>
                :
                    <div>
                        {user &&
                            <div>
                                <form onSubmit={searchRepositories}>
                                    <div>
                                        <input type="text" value={githubUsername} onChange={ev => setGithubUsername(ev.target.value)} placeholder="GitHub Username" />
                                    </div>
                                    <div>
                                        <input type="submit" value="Search" />
                                    </div>
                                </form>
                                <div>
                                    { gitHubRepositories.map(repo => <GitHubRepositoryComponent key={repo.name} user={user!} gitHubRepository={repo} onFavoriteAdd={(user) => nav(`/user/${user.gitHubUsername}`)} />) }
                                </div>
                            </div>
                        }
                    </div>
            }
        </div>
    )
}
