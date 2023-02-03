import React from "react";
import styles from "./DonateThanksNon.module.css"
import { useNavigate } from "react-router-dom";

// import { Route } from "react-router-dom";
// import {BsFillArrowRightCircleFill} from "react-icons/bs"

function DonateThanksNon(props) {
    const navigate = useNavigate()
    return (
        <div>
            <div className={styles.myImg}>
                    <div className={styles.buttonOne}>
                        <p className={styles.textAlign}>기부해 주셔서 감사합니다<br />책을 추가 기부하시겠습니까?</p>
                        <div>
                            <div className={styles.buttonThree}>
                                <p className={styles.textAlignOne}>추가기부</p>
                            </div>
                            <div className={styles.buttonTwo}>
                                <p className={styles.textAlignOne}>처음화면</p>
                            </div>
                        </div>
                    </div>
            </div>
        </div>
        )
}

export default DonateThanksNon