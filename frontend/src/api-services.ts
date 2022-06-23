import axios, { AxiosResponse } from "axios";
import { GitHubRepository, User } from "./model";

export function getRepositoriesByUser(githubUsername: string) {
    return axios.get(`/api/githubrepositories?gitHubUsername=${githubUsername}`)
        .then((response: AxiosResponse<Array<GitHubRepository>>) => response.data)

}

export function getUser(username: string) {
    return axios.get(`/api/users/${username}`)
        .then((response: AxiosResponse<User>) => response.data)
}

export function addRepoAsFavorite(username: string, repoName: string) {
    return axios.post(`/api/users/${username}/favorites`, { repositoryName: repoName })
}

export function removeFavorite(username: string, repoName: string) {
    return axios.delete(`/api/users/${username}/favorites?repoName=${repoName}`)
        .then((response: AxiosResponse<User>) => response.data)
}