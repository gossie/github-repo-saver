import { useState } from "react";
import { getRepositoriesByUser } from "./api-services";
import { GitHubRepository } from "./model";

export default function SearchPage() {

    const [githubUsername, setGithubUsername] = useState('')
    const [gitHubRepositories, setGitHubRepositories] = useState<Array<GitHubRepository>>([])

    const searchRepositories = () => {
        getRepositoriesByUser(githubUsername)
            .then(repositories => setGitHubRepositories(repositories))
    }

    const repos = gitHubRepositories.map(repo => <div key={repo.name}>{repo.name}</div>)

    return (
        <div>
            <div>
                <input type="text" value={githubUsername} onChange={ev => setGithubUsername(ev.target.value)} placeholder="GitHub Username" />
            </div>
            <div>
                <button onClick={searchRepositories}>Search</button>
            </div>
            <div>
                {repos}
            </div>
        </div>
    )
}
