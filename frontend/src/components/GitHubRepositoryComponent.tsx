import { useNavigate } from "react-router-dom";
import { addRepoAsFavorite } from "../api-services";
import { GitHubRepository } from "../model";

interface GitHubRepositoryComponentProps {
    username: string;
    gitHubRepository: GitHubRepository;
}

export default function GitHubRepositoryComponent(props: GitHubRepositoryComponentProps) {

    const nav = useNavigate()

    const add = () => {
        addRepoAsFavorite(props.username, props.gitHubRepository.full_name)
            .then(() => nav(`/user/${props.username}`))
    }

    return (
        <div>{props.gitHubRepository.name} <button onClick={add}>Add as favorite</button></div>
    )
}