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
        navigate('/Kiosk')
    }
    const goBack = () => {
        navigate(-1)
    }
    const onClickHandlerReceiptComplete = () => {
        navigate('/Kiosk/ReceiptComplete')
    }
    return (
        // 책 리스트 불러오기 
            <div>
                <div className={styles.myImg}>
                    <button className={styles.circle} onClick={goBack}>
                        <AiOutlineArrowLeft className={styles.iconStyle}/>
                    </button>
                    <div>
                        <div className={styles.buttonOne} onClick={onClickHandlerReceiptComplete}>
                            <p className={styles.h3Text}>수령하실 책을 선택하주세요</p>
                            {/* 책 리스트 */}
                            <List />
                        </div>
                    </div>
                    <button className={styles.homeCircle} onClick={onClickHandlerHome}>
                        <BiHomeAlt className={styles.iconStyle}/>
                    </button>
                </div>
            </div>
        )
}

export default ReceiptConfirm