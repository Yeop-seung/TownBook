import React from "react";
import "./MemberSelect.css"
// import { Route } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';

const MemberSelect = (props) => {
    return (
        <div>
            <div className="my-img">
                <div className="circle">
                    <AiOutlineArrowLeft size={80} pull="right" color="#90C6FE"/>
                </div>
                <div className="button">
                    <div className="button-three">
                        <p className="text-align">회원</p>
                    </div>
                </div>
                <div className="button-one">
                    <div className="button-two">
                    <p className="text-align-two">비회원</p>
                    </div>
                </div>
                <div className="home-circle">
                    <BiHomeAlt size={76} color="#90C6FE"/>
                </div>
            </div>
        </div>
        )
}

export default MemberSelect