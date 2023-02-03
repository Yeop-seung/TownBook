import React from "react";
import styles from "./ReceiptComplete.module.css"
import List from "../../ui/List"
import { useNavigate } from "react-router-dom";
// import { Route } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';

function ReceiptComplete(props) {
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
                    <div className="button">
                        <div className="button-one">
                            <List />
                            {/* 책 리스트 */}
                            <p className="text-align">수령하실 책이 맞습니까?<br></br>
                            예를 누르시면 포인트가 <br></br>차감되고 보관함이 열립니다</p>
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

export default ReceiptComplete