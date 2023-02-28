import React from "react";
import styles from "./DonateComplete.module.css"
import { useNavigate } from "react-router-dom";
// import { Route } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
// import {BsFillArrowRightCircleFill} from "react-icons/bs"

function DonateReceipt(props){
    const navigate = useNavigate()

    const onClickHandlerHome = () => {
        navigate('/')
    }

    return (
        <div>
            <div className="my-img">
                <button className="circle">
                    <AiOutlineArrowLeft className="icon-style"/>
                </button>
                <div className="button">
                    <div className="button-one">
                        <p className="text-align">보관함에 책을 빼고</p>
                        <br />
                        <p className="text-align-one">완료</p>
                        <p className="text-align-two"> 버튼을 눌러주세요</p>
                        <div>
                            <div className="button-two">
                                <p className="text-align-three">완료</p>
                            </div>
                        </div>
                    </div>
                </div>
                <button className="home-circle" onClick={onClickHandlerHome}>
                    <BiHomeAlt className="icon-style"/>
                </button>
            </div>
        </div>
        )
}

export default DonateReceipt