import React from "react";
import styles from "./DonateThanks.module.css"
import { useNavigate } from "react-router-dom";
import axios from "axios";

// import { Route } from "react-router-dom";
// import {BsFillArrowRightCircleFill} from "react-icons/bs"

function DonateThanks(props) {
    const navigate = useNavigate()
    const UrlMainClose = "http://192.168.140.1/mainServo/90 " //메인 보관함 닫기

    const onClickHandlerFinish = () => {
        // axios.get(UrlMainClose, {
        // })
        // .then((response) => {
            // const onClickHandlerBarcodeRead =() => {
            //     navigate('/BarcodeRead')
            // }
            // onClickHandlerBarcodeRead()
        //     navigate('/DonateThanks')
        // })
        
        // .catch(function (error) {
        //     console.log(error)
        // })
        navigate('/Finish')
    }
    const onClickHandlerBarcodeRead =() => {
        navigate('/BarcodeRead')
    }

    return (
        <div>
            <div className={styles.myImg}>
                    <div className={styles.buttonOne}>
                        <p className={styles.textAlign}>
                        기부해 주셔서 감사합니다
                        <br />
                        {}포인트가 적립 되었습니다
                        <br />
                        <br />
                        책을 추가 기부하시겠습니까?</p>
                        <button className={styles.buttonTwo} onClick={onClickHandlerFinish} >
                            처음화면
                        </button>
                        <button className={styles.buttonThree} onClick={onClickHandlerBarcodeRead}>
                            추가기부
                        </button>
                    </div>
            </div>
        </div>
        )
}

export default DonateThanks