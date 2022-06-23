import axios, { AxiosResponse } from "axios";
import { GitHubRepository } from "./model";

export function getRepositoriesByUser(githubUsername: string) {
    return axios.get(`/api/githubrepositories?gitHubUsername=${githubUsername}`)
        .then((response: AxiosResponse<Array<GitHubRepository>>) => response.data)

}