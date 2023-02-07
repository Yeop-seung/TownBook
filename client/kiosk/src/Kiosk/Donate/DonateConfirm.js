import React from "react";
import styles from "./DonateConfirm.module.css"
import { useLocation, useNavigate } from "react-router-dom";
// import List from "../../ui/List"
import book from "../img/book.jpg"
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
import axios from "axios";

function DonateConfirm(props) {
    // 빈보관함이 무엇인지 확인하고 if로 하기  
    const UrlOneOpen = ["http://192.168.140.1/mainServo/0", "http://192.168.140.1/servo1/0"]   //1번 보관함 열기 //메인 보관함 열기

    const UrlTwoOpen = "http://192.168.140.1/servo2/0"   //1번 보관함 열기
    
    const navigate = useNavigate()

    const location = useLocation()
    
    
    const title = location.state.bookTitle
    let bookURL = location.state.bookIntroductionURL
    if (bookURL === "null.png") {
        bookURL = book
        }
    const onClickHandlerHome = () => {
        navigate('/kiosk')
    }
    const onClickHandlerComplete =() => {
        // axios.get(UrlOneOpen, {
        // })
        // .then((response) => {
        //     navigate('/DonateComplete')
        // })
        
        // .catch(function (error) {
        //     console.log(error)
        // })
        navigate('/kiosk/DonateComplete')
    }
    const onClickHandlerBarcodeRead =() => {
        navigate('/kiosk/BarcodeRead')
    }
    const onClickHandlerBarcodeReadError =() => {
        navigate('/kiosk/BarcodeReadError')
    }
    const goBack = () => {
        navigate(-1)
    }

    // 회원정보를 가지고 있어야 된다.
    return (
        // <div>
            <div>
                <div className={styles.myImg}>
                    <button className={styles.circle} onClick={goBack}>
                        <AiOutlineArrowLeft className={styles.iconStyle}/>
                    </button>
                    <div >
                        <div className={styles.buttonOne}>
                            <img src={bookURL} className={styles.book}/>
                            <div className={styles.title}>
                                {title}
                            </div>
                            {/* <List /> */}
                            <p className={styles.textAlignTwo}>기부하시는 책이 맞습니까?</p>
                            <div>
                                <button className={styles.buttonTwo} onClick={onClickHandlerComplete}>
                                    <p className={styles.textAlignOne}>예</p>
                                </button>
                                <button className={styles.buttonThree} onClick={onClickHandlerBarcodeReadError}>
                                    <p className={styles.textAlignOne}>아니오</p>
                                </button>
                            </div>
                        </div>
                    </div>
                    <button className={styles.homeCircle} onClick={onClickHandlerHome}>
                        <BiHomeAlt className={styles.iconStyle}/>
                    </button>
                </div>
            </div>
            /* <label htmlFor="title" onSubmit={}></label>
            <input type="text" required id="title" />
        </div> */
        )
}

export default DonateConfirm