import React from "react";
import styles from "./DonateThanks.module.css"
import { useNavigate } from "react-router-dom";

// import { Route } from "react-router-dom";
// import {BsFillArrowRightCircleFill} from "react-icons/bs"

function ReceiptThanks(props) {
    const navigate = useNavigate()
    return (
        <div>
            <div className="my-img">
                <div className="button">
                    <div className="button-one">
                        <p className="text-align">이용해 주셔서 감사합니다<br />포인트가 {props.children} 사용 되었습니다<br /><br />책을 추가 수령하시겠습니까?</p>
                        <div>
                            <div className="button-two">
                                <p className="text-align-one">처음화면</p>
                            </div>
                            <div className="button-three">
                                <p className="text-align-one">추가수령</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>  
        </div>
        )
}

export default ReceiptThanks