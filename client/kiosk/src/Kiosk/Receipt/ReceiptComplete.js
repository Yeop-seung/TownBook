/* eslint-disable jsx-a11y/alt-text */
import React from "react";
import styles from "./ReceiptComplete.module.css"
import { useLocation, useNavigate } from "react-router-dom";
import book from "../img/book.jpg"
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';

function ReceiptComplete(props) {
    const UrlOneOpen = ["http://192.168.140.1/mainServo/0", "http://192.168.140.1/servo1/0"]   //메인 보관함 열기 //1번 보관함 열기 

    const UrlTwoOpen = "http://192.168.140.1/servo2/0"   //2번 보관함 열기
    
    const navigate = useNavigate()

    const location = useLocation()
    
    const title = location.state.bookTitle
    let bookURL = location.state.bookIntroductionURL
    if (bookURL === "null.png") {
        bookURL = book
        }

    const onClickHandlerHome = () => {
        navigate('/')
    }
    const onClickHandlerReceiptConfirm = () => {
        navigate('/ReceiptConfirm')
    }
    const onClickHandlerDonateReceipt = () => {
        navigate('/DonateReceipt')
    }
    const goBack = () => {
        navigate(-1)
    }

    return (
            <div>
                <div className={styles.myImg}>
                    <button className={styles.circle} onClick={goBack}>
                        <AiOutlineArrowLeft className={styles.iconStyle }/>
                    </button>
                    <div className={styles.buttonOne}>
                        <img src={bookURL} className={styles.book}/>
                            <div className={styles.title}>
                                {title}
                            </div>
                        <p className={styles.textAlign}>수령하실 책이 맞습니까?
                        <br/>
                        예를 누르시면 포인트가
                        <br/>차감되고 보관함이 열립니다</p>
                        <div>
                            <button className={styles.buttonTwo} onClick={onClickHandlerDonateReceipt}>
                                예
                            </button>
                            <button className={styles.buttonThree} onClick={onClickHandlerReceiptConfirm}>
                                아니오
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

export default ReceiptComplete