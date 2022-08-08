import React, {Component} from 'react';

import {Modal, ModalBody, ModalFooter, ModalHeader,} from "reactstrap";
import {
    deleteUser, getUser, saveUser
} from "../redux/actions/AppAction";
import {connect} from "react-redux";
import './css/style.css';


class Register extends Component {
    componentDidMount() {
        this.props.dispatch(getUser())
    }

    render() {
        const { users, showModal, deleteModal, currentItem, dispatch} = this.props;

        console.log(users)
        alert("ishlasinda")
        const openModal = (item) => {
            dispatch({
                type: 'updateState',
                payload: {
                    showModal: !showModal,
                    currentItem: item
                }
            })
        };

        const openDeleteModal = (item) => {
            dispatch({
                type: 'updateState',
                payload: {
                    deleteModal: !deleteModal,
                    currentItem: item
                }
            })
        };
        const saveUsers = (e) => {

            let obj = {}
            let firstName = document.getElementById("firstName").value;
            let lastName = document.getElementById("lastName").value;
            let phoneNumber = document.getElementById("phoneNumber").value;
            let birthDate = document.getElementById("birthDate").value;
            let roles = document.getElementById("roles").value;
            let email = document.getElementById("email").value;
            // let isChecked = document.getElementById("isChecked").value;

            let id = currentItem.id ? currentItem.id : null;
            if (currentItem.id) {
                obj = {id, firstName, phoneNumber, lastName, birthDate, roles, email}
            } else {
                obj = {firstName, phoneNumber, lastName, birthDate, roles, email}
            }
            console.log(obj)
            this.props.dispatch(saveUser(obj))
        }
        const deleteUsers = () => {
            this.props.dispatch(deleteUser(currentItem))
        }

        return (
            <div className="container">
                <h2 className="text-center">User List</h2>

                <button className="custom-btn btn-2" onClick={() => openModal('')}>Add</button>
                <div style={{display: 'flex'}}>
                    {users.length != null ?
                        users.map((item, i) =>
                            <div id="card">
                                <div className="card-body">
                                    <h1 className="card-title"><b>{item.name}</b></h1>
                                    <div className="buttons">
                                        <button className="custom-btn btn-13" onClick={() => openModal(item)}>Edit
                                        </button>
                                        {' '}
                                        <button className="custom-btn btn-12"
                                                onClick={() => openDeleteModal(item)}>Delete
                                        </button>
                                    </div>
                                </div>
                            </div>
                        )
                        : "Malumot mavjud emas"
                    }
                </div>

                <Modal isOpen={showModal}>
                    <ModalHeader>{currentItem.id ? "Edit User" : "Add User"}</ModalHeader>
                    <ModalBody>
                        <div className="group">
                            <input type="text" required id="firstName" name="firstName"
                                   defaultValue={currentItem.firstName}/>
                            <span className="highlight"/>
                            <span className="bar"/>
                            <label>First Name</label>
                        </div>
                        <div className="group">
                            <input type="text" required id="lastName" name="lastName"
                                   defaultValue={currentItem.lastName}/>
                            <span className="highlight"/>
                            <span className="bar"/>
                            <label>Last Name</label>
                        </div>
                        <div className="group">
                            <input type="number" required id="phoneNumber" name="phoneNumber"
                                   defaultValue={currentItem.phoneNumber}/>
                            <span className="highlight"/>
                            <span className="bar"/>
                            <label>Phone Number</label>
                        </div>
                        <div className="group">
                            <input type="date" required id="birthDate" name="birthDate"
                                   defaultValue={currentItem.birthDate}/>
                            <span className="highlight"/>
                            <span className="bar"/>
                            <label>Birth date</label>
                        </div>
                        <div className="group">
                            <select className="form-select" aria-label="Default select example" name="roles"
                                id="roles">
                            <option value="0" selected={true}>Selected Category</option>
                            <option value="1">ROLE_USER</option>
                            <option value="2">ROLE_ADMIN</option>
                            <option value="3">ROLE_SUPERADMIN</option>
                        </select>
                        </div>
                        <div className="group">
                            <input type="email" required id="email" name="email"
                                   defaultValue={currentItem.email}/>
                            <span className="highlight"/>
                            <span className="bar"/>
                            <label>Email</label>
                        </div>
                        {/*<br/>*/}
                        {/*<div className="group">*/}
                        {/*    <input type="checkbox" required id="isChecked" name="isChecked"*/}
                        {/*       defaultValue={currentItem.name}/>*/}
                        {/*<label>Telefon qildingizmi?</label>*/}
                        {/*</div>*/}
                    </ModalBody>
                    <ModalFooter>
                        <button className="custom-btn btn-12" onClick={() => openModal('')}>Cancel</button>
                        <button className="custom-btn btn-11" onClick={saveUsers}>Save
                            <div className="dot"/>
                        </button>
                    </ModalFooter>
                </Modal>
                <Modal isOpen={deleteModal}>
                    <ModalHeader>Delete User</ModalHeader>
                    <ModalBody>{"Are you sure " + currentItem.name}</ModalBody>
                    <ModalFooter>
                        <button className="custom-btn btn-12" onClick={() => openDeleteModal('')}>Cancel</button>
                        <button className="custom-btn btn-11" onClick={deleteUsers}>Delete
                        </button>
                    </ModalFooter>
                </Modal>
            </div>

        );
    }
}

Register.propTypes = {};

export default connect(
    ({app: { users,showModal, deleteModal, currentItem}}) =>
        ({ users, showModal, deleteModal, currentItem}))
(Register);