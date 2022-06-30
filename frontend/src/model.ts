export interface GitHubRepository {
    name: string
    full_name: string;
}

export interface User {
    username: string;
    favoriteRepositories: Array<Favorite>;
}

export interface Favorite {
    repositoryName: string;
}

export interface LoginResponse {
    token: string;
}
