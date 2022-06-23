import { useNavigate } from "react-router-dom";
import { addRepoAsFavorite } from "../api-services";
import { GitHubRepository, User } from "../model";

interface GitHubRepositoryComponentProps {
    user: User;
    gitHubRepository: GitHubRepository;
}

export default function GitHubRepositoryComponent(props: GitHubRepositoryComponentProps) {

    const nav = useNavigate()

    const add = () => {
        addRepoAsFavorite(props.user.username, props.gitHubRepository.full_name)
            .then(() => nav(`/user/${props.user.username}`))
    }

    return (
        <div>
            {props.gitHubRepository.name}
            { !props.user.favoriteRepositories.map(f => f.repositoryName).includes(props.gitHubRepository.full_name) && <button onClick={add}>Add as favorite</button>}
        </div>
    )
}