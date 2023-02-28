import React from "react";
import styles from "./ReceiptConfirm.module.css"
import { useNavigate } from "react-router-dom";
import List from "../../ui/List"
// import { Route } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';

function ReceiptConfirm(props) {
    // function submitHandler(event) {
    //     event.preventDefault()
    // }
    const navigate = useNavigate()

    const onClickHandlerHome = () => {
        navigate('/')
    }

    return (
        // <div>
            <div>
                <div className="my-img">
                    <button className="circle">
                        <AiOutlineArrowLeft className="icon-style"/>
                    </button>
                    <div>
                        <div className="button-one">
                            <p className="text-align">수령하실 책을 선택하주세요</p>
                            <List />
                            {/* 책 리스트 */}
                            <div>
                                <div className="button-three">
                                    <p className="text-align-one">아니오</p>
                                </div>
                                <div className="button-two">
                                    <p className="text-align-one">예</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button className="home-circle" onClick={onClickHandlerHome}>
                        <BiHomeAlt className="icon-style"/>
                    </button>
                </div>
            </div>
            /* <label htmlFor="title" onSubmit={}></label>
            <input type="text" required id="title" />
        </div> */
        )
}

export default ReceiptConfirm