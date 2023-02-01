import React from "react";
import "./DonateComplete.css"
import "./Base.css"
// import { Route } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
// import {BsFillArrowRightCircleFill} from "react-icons/bs"

function DonateComplete(props) {
    return (
        <div>
            <div className="my-img">
            
                <div className="circle">
                    <AiOutlineArrowLeft size={80} pull="right" color="#90C6FE"/>
                </div>
                <div className="button">
                    <div className="button-one">
                        <p className="text-align">보관함에 책을 넣고</p>
                        <br />
                        <p className="text-align-one">완료</p>
                        <p className="text-align-two"> 버튼을 눌러주세요</p>
                        <div>
                            <div className="button-three">
                                <p className="text-align-three">완료</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="home-circle">
                    <BiHomeAlt size={76} color="#90C6FE"/>
                </div>
            </div>
        </div>
        )
}

export default DonateComplete