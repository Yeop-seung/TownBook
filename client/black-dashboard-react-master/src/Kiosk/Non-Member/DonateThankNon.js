import React from "react";
import "./DonateThanksNon.css"
import "../Base.css"
// import { Route } from "react-router-dom";
// import {BsFillArrowRightCircleFill} from "react-icons/bs"

function DonateThanksNon(props) {
    return (
        <div>
            <div className="my-img">
                <div className="button">
                    <div className="button-one">
                        <p className="text-align">기부해 주셔서 감사합니다</p>
                        <p className="text-align-two">책을 추가 기부하시겠습니까?</p>
                        <div>
                            <div className="button-three">
                                <p className="text-align-three">추가기부</p>
                            </div>
                            <div className="button-two">
                                <p className="text-align-three">처음화면</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        )
}

export default DonateThanksNon