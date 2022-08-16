import HttpClient from "../utils/HttpClient";
import {api} from "./api";
//subject
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
//pupils
export const getPupils = () => {
    return HttpClient.doGet(api.auth+"/pupils");
}
export const addPupils = (data) => {
    return HttpClient.doPost(api.register, data);
}
export const updatePupils = (data) => {
    return HttpClient.doPut(api.auth + "/" + data.id, data);
}
export const delPupils = (data) => {
    console.log(data)
    return HttpClient.doDelete(api.auth + "/" + data.id);
}
//register
export const getUsers = () => {
    return HttpClient.doGet(api.register+"/list");

}
export const addUser = (data) => {
    return HttpClient.doPost(api.register, data);
}
export const delUser = (data) => {
    console.log(data)
    return HttpClient.doDelete(api.auth + "/" + data.id);
}
export const updateUser = (data) => {
    console.log(data.id);
    return HttpClient.doPut(api.auth + "/" + data.id, data);
}
//teacher
export const getTeachers = () => {
    return HttpClient.doGet(api.auth+"/teachers");
}
export const addTeacher = (data) => {
    return HttpClient.doPost(api.register, data);
}
export const delTeacher = (data) => {
    console.log(data.id)
    return HttpClient.doDelete(api.auth + "/" + data.id);
}
export const updateTeacher = (data) => {
    console.log(data.id);
    return HttpClient.doPut(api.auth + "/" + data.id, data);
}
//admin
export const getAdmins = () => {
    return HttpClient.doGet(api.auth+"/admins");
}
export const addAdmin = (data) => {
    return HttpClient.doPost(api.auth, data);
}
export const delAdmin = (data) => {
    console.log(data.id)
    return HttpClient.doDelete(api.auth + "/" + data.id);
}
export const updateAdmin = (data) => {
    console.log(data.id);
    return HttpClient.doPut(api.auth + "/" + data.id, data);
}
