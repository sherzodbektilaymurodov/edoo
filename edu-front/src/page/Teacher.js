import React, {Component} from 'react';

import {Button, Modal, ModalBody, ModalFooter, ModalHeader, Table,} from "reactstrap";
import {
    deleteTeacher,
    deleteUser, getTeacher, getUser, saveTeacher, saveUser
} from "../redux/actions/AppAction";
import {connect} from "react-redux";
import './css/style.css';

class Teacher extends Component {
    componentDidMount() {
        this.props.dispatch(getTeacher())
    }

    render() {
        const {teachers, showModal, deleteModal, currentItem, dispatch} = this.props;
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
            let roles = [1,2]
            let email = document.getElementById("email").value;
            let id = currentItem.id ? currentItem.id : null;
            if (currentItem.id) {
                obj = {id, firstName, phoneNumber, lastName, birthDate, roles, email}
            } else {
                obj = {firstName, phoneNumber, lastName, birthDate, roles, email}
            }
            console.log(obj)
            this.props.dispatch(saveTeacher(obj))
        }
        const deleteUsers = () => {
            this.props.dispatch(deleteTeacher(currentItem))
        }


        return (
            <div className="container">
                <h2 className="text-center">O'qituvchilar</h2>

                <button className="custom-btn btn-2" onClick={() => openModal('')}>Qo'shish+</button>
                <Table>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Ism</th>
                        <th>Familiya</th>
                        <th>Telefon raqam</th>
                        <th>Tug'ilgan yili</th>
                        <th>Huquqi</th>
                        <th>Email</th>
                        <th colSpan='2'>Action</th>
                    </tr>
                    </thead>
                    {teachers.length !== 0 ?
                        teachers.map((item, i) =>
                            <tbody>
                            <tr>
                                <td>{i + 1}</td>
                                <td>{item.firstName}</td>
                                <td>{item.lastName}</td>
                                <td>{item.phoneNumber}</td>
                                <td>{new Intl.DateTimeFormat('en-US', {
                                    year: 'numeric',
                                    month: '2-digit',
                                    day: '2-digit'
                                }).format(item.date)}</td>
                                <td>{item.roles}</td>
                                <td>{item.email}</td>
                                <td><Button color="warning" outline onClick={() => openModal(item)}>Tahrirlash</Button>
                                </td>
                                <td><Button color="danger" outline
                                            onClick={() => openDeleteModal(item.firstName)}>O'chirish</Button></td>
                            </tr>
                            </tbody>
                        )
                        : "Malumot mavjud emas"
                    }
                </Table>
                <Modal isOpen={showModal}>
                    <ModalHeader>{currentItem.id ? "O'qituvchini tahrirlash" : "O'qituvchi qo'shish"}</ModalHeader>
                    <ModalBody>
                        <div className="group">
                            <input type="text" required id="firstName" name="firstName"
                                   defaultValue={currentItem.firstName}/>
                            <span className="highlight"/>
                            <span className="bar"/>
                            <label>Ism (To'ldirish shart)</label>
                        </div>
                        <div className="group">
                            <input type="text" required id="lastName" name="lastName"
                                   defaultValue={currentItem.lastName}/>
                            <span className="highlight"/>
                            <span className="bar"/>
                            <label>Familiya</label>
                        </div>
                        <div className="group">
                            <input type="number" required id="phoneNumber" name="phoneNumber"
                                   defaultValue={currentItem.phoneNumber}/>
                            <span className="highlight"/>
                            <span className="bar"/>
                            <label>Telefon raqam (To'ldirish shart)</label>
                        </div>
                        <div className="group">
                            <input type="date" required id="birthDate" name="birthDate"
                                   defaultValue={currentItem.birthDate}/>
                            <span className="highlight"/>
                            <span className="bar"/>
                            <label>Tug'ilgaan yili</label>
                        </div>
                        <div className="group">
                            <select className="form-select" aria-label="Default select example" name="roles"
                                    id="roles">
                                <option value="2">O'qituvchi</option>
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
                        <button className="custom-btn btn-12" onClick={() => openModal('')}>Orqaga</button>
                        <button className="custom-btn btn-11" onClick={saveUsers}>Saqlandi
                            <div className="dot"/>
                        </button>
                    </ModalFooter>
                </Modal>
                <Modal isOpen={deleteModal}>
                    <ModalHeader>O'qituvchini o'chirish</ModalHeader>
                    <ModalBody>{currentItem + " shu o'qituvchini o'chirasizmi"}</ModalBody>
                    <ModalFooter>
                        <button className="custom-btn btn-11" onClick={() => openDeleteModal('')}>Orqaga</button>
                        <button className="custom-btn btn-12" onClick={() => deleteUsers()}>O'chirish</button>
                    </ModalFooter>
                </Modal>
            </div>

        );
    }
}

Teacher.propTypes = {};

export default connect(
    ({app: {teachers, showModal, deleteModal, currentItem}}) =>
        ({teachers, showModal, deleteModal, currentItem}))
(Teacher);