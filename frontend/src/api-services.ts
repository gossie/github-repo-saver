import axios, { AxiosResponse } from "axios";
import { GitHubRepository, LoginResponse, User } from "./model";

export function getAuthData() {
    return axios.get("/api/auth")
        .then((response: AxiosResponse<AuthData>) => response.data)
}

export function getRepositoriesByUser(githubUsername: string) {
    return axios.get(`/api/githubrepositories?gitHubUsername=${githubUsername}`, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem('jwt')}`
        }
    })
    .then((response: AxiosResponse<Array<GitHubRepository>>) => response.data)

}

export function getUser() {
    return axios.get(`/api/users/me`, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem('jwt')}`
        }
    })
    .then((response: AxiosResponse<User>) => response.data)
}

export function addRepoAsFavorite(repoName: string) {
    return axios.post(`/api/users/me/favorites`, { repositoryName: repoName }, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem('jwt')}`
        }
    })
    .then((response: AxiosResponse<User>) => response.data)
}

export function removeFavorite(repoName: string) {
    return axios.delete(`/api/users/me/favorites?repoName=${repoName}`, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem('jwt')}`
        }
    })
    .then((response: AxiosResponse<User>) => response.data)
}

export function gitHubCallback(code: string) {
    return axios.post(`/api/oauth?code=${code}`)
        .then((response: AxiosResponse<LoginResponse>) => response.data)
}