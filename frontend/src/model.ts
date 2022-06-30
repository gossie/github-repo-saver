export interface GitHubRepository {
    name: string
    full_name: string;
}

export interface User {
    gitHubUsername: string;
    favoriteRepositories: Array<Favorite>;
}

export interface Favorite {
    repositoryName: string;
}

export interface LoginResponse {
    token: string;
}

export interface AuthData {
    gitHubClientId: string;
}