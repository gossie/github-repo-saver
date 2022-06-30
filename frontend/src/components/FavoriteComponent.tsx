import { removeFavorite } from "../api-services";
import { Favorite, User } from "../model"

interface FavoriteComponentProps {
    user: User;
    favorite: Favorite;
    onFavoriteDeletion: (user: User) => void;
}

export default function FavoriteComponent(props: FavoriteComponentProps) {

    const remove = () => {
        removeFavorite(props.favorite.repositoryName)
            .then(changedUser => props.onFavoriteDeletion(changedUser))
    }

    return (
        <div>
            {props.favorite.repositoryName}
            <button onClick={remove}>Remove</button>
        </div>
    )
}