import React from "react";
import styles from "./DonateThanks.module.css"
import { useNavigate } from "react-router-dom";

// import { Route } from "react-router-dom";
// import {BsFillArrowRightCircleFill} from "react-icons/bs"

function ReceiptThanks(props) {
    
    const navigate = useNavigate()

    const onClickHandlerFinish = () => {
        navigate('/Finish')
    }
    const onClickHandlerReceiptConfirm = () => {
        navigate('/ReceiptConfirm')
    }

    return (
        <div>
            <div className={styles.myImg}>
                <div className={styles.buttonOne}>
                    <p className={styles.textAlign}>이용해 주셔서 감사합니다
                    <br />포인트가 {props.children} 사용 되었습니다
                    <br />
                    <br />
                    책을 추가 수령하시겠습니까?</p>
                    <div>
                        <button className={styles.buttonTwo} onClick={onClickHandlerFinish}>
                            <p className={styles.textAlignOne}>처음화면</p>
                        </button>
                        <button className={styles.buttonThree} onClick={onClickHandlerReceiptConfirm}>
                            <p className={styles.textAlignOne}>추가수령</p>
                        </button>
                    </div>
                </div>
            </div>  
        </div>
        )
}

export default ReceiptThanks