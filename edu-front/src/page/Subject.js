import React, {Component} from 'react';

import {Modal, ModalBody, ModalFooter, ModalHeader,} from "reactstrap";
import {
    deleteSubject,

    getSubject,
    saveSubject
} from "../redux/actions/AppAction";
import {connect} from "react-redux";
import './css/style.css';


class Subject extends Component {
    componentDidMount() {
        this.props.dispatch(getSubject())
    }

    render() {
        const {subjects, showModal, deleteModal, currentItem, dispatch} = this.props;

        console.log(subjects.length)
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
        const saveSubjects = (e) => {

            let obj = {}
            let name = document.getElementById("name").value;
            let description = document.getElementById("description").value;

            let id = currentItem.id ? currentItem.id : null;
            if (currentItem.id) {
                obj = {id, name, description}
            } else {
                obj = {name, description}
            }
            this.props.dispatch(saveSubject(obj))
        }
        const deleteSubjects = () => {
            this.props.dispatch(deleteSubject(currentItem))
        }

        return (
            <div className="container">
                <h2 className="text-center">Subject List</h2>

                <button className="custom-btn btn-2" onClick={() => openModal('')}>Add</button>
                <div style={{display: 'flex'}}>
                    {subjects.length != null ?
                        subjects.map((item, i) =>
                            <div id="card">
                                <div className="card-body">
                                    <h1 className="card-title"><b>{item.name}</b></h1>
                                    <h4 className="card-title"><b>{item.description}</b></h4>
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
                    <ModalHeader>{currentItem.id ? "Edit product" : "Add product"}</ModalHeader>
                    <ModalBody>
                        <div className="group">
                            <input type="text" required id="name" name="name"
                                   defaultValue={currentItem.name}/>
                            <span className="highlight"></span>
                            <span className="bar"></span>
                            <label>Name</label>
                        </div>
                        <textarea name="description" id="description" cols="30" rows="10" required={true} placeholder="Pleace Enter Description"></textarea>



                    </ModalBody>
                    <ModalFooter>
                        <button className="custom-btn btn-12" onClick={() => openModal('')}>Cancel</button>
                        <button className="custom-btn btn-11" onClick={saveSubjects}>Save
                            <div className="dot"></div>
                        </button>
                    </ModalFooter>
                </Modal>
                <Modal isOpen={deleteModal}>
                    <ModalHeader>Delete Subject</ModalHeader>
                    <ModalBody>{"Are you sure " + currentItem.name}</ModalBody>
                    <ModalFooter>
                        <button className="custom-btn btn-12" onClick={() => openDeleteModal('')}>Cancel</button>
                        <button className="custom-btn btn-11" onClick={deleteSubjects}>Delete
                        </button>
                    </ModalFooter>
                </Modal>
            </div>

        );
    }
}

Subject.propTypes = {};

export default connect(
    ({app: {subjects, showModal, deleteModal, currentItem}}) =>
        ({subjects, showModal, deleteModal, currentItem}))
(Subject);