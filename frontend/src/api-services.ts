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