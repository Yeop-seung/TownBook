import React from "react";
import styles from "./DonateReceipt.module.css"
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
// import {BsFillArrowRightCircleFill} from "react-icons/bs"

function DonateReceipt(props){
    const UrlTOneClose = "http://192.168.140.1/servo1/90" //1번 보관함 닫기
    const UrlTwoClose = "http://192.168.140.1/servo2/90 " //2번 보관함 닫기

    const navigate = useNavigate()

    const location = useLocation()

    const isnavigate = location.state
    
    function onClickHandlerReceiptThanks(e) {
    //     let event=window.event || e;
        
    //     axios.get(`/server/book/${e.target.value}`, {
    //     })
    //     .then((response) => {
    //         const onClickHandlerThanks = () => {
    //             navigate('/ReceiptThanks', {state : response.data})
    //         }})
    //         onClickHandlerThanks()
    //     .catch(function (error) {
    //         console.log(error)
    //     })
    }
    const onClickHandlerHome = () => {
        navigate('/')
    }
    const goBack = () => {
        navigate(-1)
    }

    return (
        <div>
            <div className={styles.myImg}>
                <button className={styles.circle} onClick={goBack}>
                    <AiOutlineArrowLeft className={styles.iconStyle}/>
                </button>
                {/* 보관함 책을 넣고 빼고  */}
                    <div className={styles.buttonOne}>
                        <p className={styles.textAlign}>보관함에 책을 빼고</p>
                        <br />
                        <p className={styles.textAlignOne}>완료</p>
                        <p className={styles.textAlignTwo}> 버튼을 눌러주세요</p>
                        <div>
                            <button className={styles.buttonTwo} onClick={onClickHandlerReceiptThanks}>
                                완료
                            </button>
                        </div>
                    </div>
                <button className={styles.homeCircle} onClick={onClickHandlerHome}>
                    <BiHomeAlt className={styles.iconStyle}/>
                </button>
            </div>
        </div>
        )
}

export default DonateReceipt