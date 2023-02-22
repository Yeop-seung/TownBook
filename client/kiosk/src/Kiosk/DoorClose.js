import React from "react";
import styles from "./DoorClose.module.css"
import {  useNavigate } from "react-router-dom";
import axios from "axios";

function DoorClose(props){
    
    const navigate = useNavigate()
    const UrlMainClose = "http://192.168.171.1/mainServo/90" //메인 보관함 닫기

    const axiosFinish = () => {
        axios.get(UrlMainClose, {
        })
        .then((response) => {
            navigate('/Finish')
        })
        .catch(function (error) {
            console.log(error)
        })
    }

    return (
            <div className={styles.myImg}>
                <div className={styles.buttonOne}>
                    <p className={styles.textAlign}>
                    문을 닫고 완료 
                    <br /> 버튼을 눌러주세요 
                    </p>
                    <p className={styles.textAlignTwo}>시간이 지나면 문이 잠깁니다 </p>
                </div>
                <button className={styles.buttonTwo} onClick={axiosFinish}>
                    완료
                </button>
            </div>
        )
}

export default DoorClose