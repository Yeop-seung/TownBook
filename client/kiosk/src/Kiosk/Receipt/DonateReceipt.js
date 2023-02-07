import React from "react";
import styles from "./DonateReceipt.module.css"
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
// import {BsFillArrowRightCircleFill} from "react-icons/bs"

function DonateReceipt(props){
    const navigate = useNavigate()

    const location = useLocation()

    const isnavigate = location.state
    

    function barcodeInput(e) {
        let event=window.event || e;
        
        if(event.target.value.length === 13){
            axios.get(`/server/book/${e.target.value}`, {
            })
            .then((response) => {
                const onClickHandlerConfirm = () => {
                    navigate('/Kiosk/DonateConfirm', {state : response.data} )
                }
                onClickHandlerConfirm()
                
            })
            
            .catch(function (error) {
                console.log(error)
            })
        }}
    const onClickHandlerHome = () => {
        navigate('/Kiosk')
    }
    const onClickHandlerReceiptComplete = () => {
        navigate('/Kiosk/ReceiptComplete')
    }
    const onClickHandlerReceiptThanks = () => {
        navigate('/Kiosk/ReceiptThanks')
    }

    return (
        <div>
            <div className={styles.myImg}>
                <button className={styles.circle} onClick={onClickHandlerReceiptComplete}>
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
                                <p className={styles.textAlignThree}>완료</p>
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