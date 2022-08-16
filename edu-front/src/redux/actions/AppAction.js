import {
    addPupils,
    addSubject, addTeacher, addUser, delPupils,
    delSubject, delTeacher, delUser, getPupils,
    getSubjects, getTeachers, getUsers, updatePupils,
    updateSubject, updateTeacher, updateUser,
} from "../../api/AppApi";
import * as types from "../actionTypes/AppActionTypes";
import {toast} from "react-toastify";

//subject
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
//pupil
export const getPupil = () => (dispatch) => {
    dispatch({
        api: getPupils,
        types: [
            types.REQUEST_START,
            types.GET_PUPIL_LIST,
            types.REQUEST_ERROR
        ]
    })
}
export const savePupil = (payload) => (dispatch) => {
    console.log("Action")
    dispatch({
        api: payload.id ? updatePupils : addPupils,
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
        dispatch(getPupils())
        toast.success("Successfully saved subject")
    }).catch(err => {
        alert(err)
        toast.error("Error saving subject!");
    })
}
export const deletePupil = (payload) => (dispatch) => {
    dispatch({
        api: delPupils,
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
        dispatch(getPupils())
        dispatch({
            type: types.REQUEST_SUCCESS
        })
        toast.success("Successfully deleted pupil")
    }).catch(err => {
        toast.error("Error deleting pupil!");
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
        toast.success("Foydalanuvchi saqlandi")
    }).catch(err => {
        toast.error("Saqlashda xatolik");
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
        toast.success("Successfully deleted user")
    }).catch(err => {
        toast.error("Error deleting user!");
    })
}
//teacher
export const getTeacher = () => (dispatch) => {
    dispatch({
        api: getTeachers,
        types: [
            types.REQUEST_START,
            types.GET_TEACHER_LIST,
            types.REQUEST_ERROR
        ]
    })

}
export const saveTeacher = (payload) => (dispatch) => {
    dispatch({
        api: payload.id ? updateTeacher : addTeacher,
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
        dispatch(getTeacher())
        toast.success("O'qituvchi saqlandi")
    }).catch(err => {
        toast.error("Saqlashda xatolik");
    })
}
export const deleteTeacher = (payload) => (dispatch) => {
    dispatch({
        api: delTeacher,
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
        dispatch(getTeacher())
        dispatch({
            type: types.REQUEST_SUCCESS
        })
        toast.success("O'qituvchi o'chirildi")
    }).catch(err => {
        toast.error("O'chirishda xatolik");
    })
}
