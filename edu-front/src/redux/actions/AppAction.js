import {
    addSubject, addUser,
    delSubject, delUser,
    getSubjects, getUsers,
    updateSubject, updateUser,
} from "../../api/AppApi";
import * as types from "../actionTypes/AppActionTypes";
import {toast} from "react-toastify";


export const getSubject = () => (dispatch) => {
    dispatch({
        api: getSubjects,
        types: [
            types.REQUEST_START,
            types.GET_SUBJECT_LIST,
            types.REQUEST_ERROR
        ]
    })

}
//register
export const saveSubject = (payload) => (dispatch) => {
    console.log("Action")
    dispatch({
        api: payload.id ? updateSubject : addSubject,
        types: [
            types.REQUEST_START,
            types.REQUEST_SUCCESS,
            types.REQUEST_ERROR
        ],
        data: payload
    }).then(res => {
        dispatch({
            type: 'updateState'
        })
        dispatch(getSubject())
        toast.success("Successfully saved subject")
    }).catch(err => {
        alert(err)
        toast.error("Error saving subject!");
    })
}
export const deleteSubject = (payload) => (dispatch) => {
    dispatch({
        api: delSubject,
        types: [
            types.REQUEST_START,
            types.REQUEST_SUCCESS,
            types.REQUEST_ERROR
        ],
        data: payload
    }).then(res => {
        dispatch({
            type: 'updateState'
        })
        dispatch(getSubject())
        dispatch({
            type: types.REQUEST_SUCCESS
        })
        toast.success("Successfully deleted subject")
    }).catch(err => {
        toast.error("Error deleting subject!");
    })
}
//register
export const getUser = () => (dispatch) => {
    dispatch({
        api: getUsers,
        types: [
            types.REQUEST_START,
            types.GET_USER_LIST,
            types.REQUEST_ERROR
        ]
    })

}
export const saveUser = (payload) => (dispatch) => {
    dispatch({
        api: payload.id ? updateUser : addUser,
        types: [
            types.REQUEST_START,
            types.REQUEST_SUCCESS,
            types.REQUEST_ERROR
        ],
        data: payload
    }).then(res => {
        dispatch({
            type: 'updateState'
        })
        dispatch(getUser())
        toast.success("Successfully saved subject")
    }).catch(err => {
        toast.error("Error saving subject!");
    })
}
export const deleteUser = (payload) => (dispatch) => {
    dispatch({
        api: delUser,
        types: [
            types.REQUEST_START,
            types.REQUEST_SUCCESS,
            types.REQUEST_ERROR
        ],
        data: payload
    }).then(res => {
        dispatch({
            type: 'updateState'
        })
        dispatch(getUser())
        dispatch({
            type: types.REQUEST_SUCCESS
        })
        toast.success("Successfully deleted subject")
    }).catch(err => {
        toast.error("Error deleting subject!");
    })
}
