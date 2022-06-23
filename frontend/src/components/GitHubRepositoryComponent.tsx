import { addRepoAsFavorite } from "../api-services";
import { GitHubRepository, User } from "../model";

interface GitHubRepositoryComponentProps {
    user: User;
    gitHubRepository: GitHubRepository;
    onFavoriteAdd: (user: User) => void;
}

export default function GitHubRepositoryComponent(props: GitHubRepositoryComponentProps) {

    const add = () => {
        addRepoAsFavorite(props.user.username, props.gitHubRepository.full_name)
            .then((changedUser) => props.onFavoriteAdd(changedUser))
    }

    return (
        <div>
            {props.gitHubRepository.name}
            { !props.user.favoriteRepositories.map(f => f.repositoryName).includes(props.gitHubRepository.full_name) && <button onClick={add}>Add as favorite</button>}
        </div>
    )
}