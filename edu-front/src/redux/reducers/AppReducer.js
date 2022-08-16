import * as types from "../actionTypes/AppActionTypes";
import {createReducer} from "../../utils/StoreUtils";

const initState = {
    showModal: false,
    deleteModal: false,
    currentItem: "",
    subjects: [],
    users: [],
    pupils: [],
    teachers: [],
}
const reducers = {

    [types.GET_SUBJECT_LIST](state, payload) {
        console.log(payload)
        state.subjects = payload.payload;
    },
    [types.GET_PUPIL_LIST](state, payload) {
        console.log(payload.payload)
        state.pupils = payload.payload;
    },
    [types.GET_USER_LIST](state, payload) {
        state.users = payload.payload;
    },
    [types.GET_TEACHER_LIST](state, payload) {
        state.teachers = payload.payload;
    },
    [types.REQUEST_SUCCESS](state, payload) {
        state.showModal = false
        state.deleteModal = false
        state.currentItem = ""
    },


    updateState(state, {payload}) {
        return {
            ...state,
            ...payload,
        };
    },
};
export default createReducer(initState, reducers);




