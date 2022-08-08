import HttpClient from "../utils/HttpClient";
import {api} from "./api";

export const getSubjects = () => {
    return HttpClient.doGet(api.subject+"/list");
}

export const addSubject = (data) => {
    return HttpClient.doPost(api.subject, data);
}
export const updateSubject = (data) => {
    return HttpClient.doPut(api.subject + "/" + data.id, data);
}
export const delSubject = (data) => {
    console.log(data)
    return HttpClient.doDelete(api.subject + "/" + data.id);
}
//register
export const getUsers = () => {
    return HttpClient.doGet(api.register+"/list");
}
export const addUser = (data) => {
    return HttpClient.doPost(api.register, data);
}
export const updateUser = (data) => {
    return HttpClient.doPut(api.auth + "/" + data.id, data);
}
export const delUser = (data) => {
    console.log(data.id)
    return HttpClient.doDelete(api.auth + "/" + data.id);
}