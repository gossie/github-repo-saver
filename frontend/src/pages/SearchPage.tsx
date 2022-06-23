import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getRepositoriesByUser, getUser } from "../api-services";
import GitHubRepositoryComponent from "../components/GitHubRepositoryComponent";
import { GitHubRepository, User } from "../model";

export default function SearchPage() {

    const [user, setUser] = useState<User>()
    const [errorMessage, setErrorMessage] = useState('');
    const [githubUsername, setGithubUsername] = useState('')
    const [gitHubRepositories, setGitHubRepositories] = useState<Array<GitHubRepository>>([])

    const { username } = useParams()
    const nav = useNavigate()
 
    useEffect(() => {
        getUser(username!)
            .then((user => setUser(user)))
            .catch(() => setErrorMessage(`The user ${username} cannot be found. You will be redirected to the login page.`))
    }, [username])

    useEffect(() => {
        if (errorMessage) {
            setTimeout(() => nav('/'), 5000)
        }
    }, [errorMessage, nav])

    const searchRepositories = () => {
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
                                <div>
                                    <input type="text" value={githubUsername} onChange={ev => setGithubUsername(ev.target.value)} placeholder="GitHub Username" />
                                </div>
                                <div>
                                    <button onClick={searchRepositories}>Search</button>
                                </div>
                                <div>
                                    {gitHubRepositories.map(repo => <GitHubRepositoryComponent key={repo.name} user={user!} gitHubRepository={repo} />)}
                                </div>
                            </div>
                        }
                    </div>
            }
        </div>
    )
}
