import React from "react";
import styles from "./DonateThanks.module.css"
import { useLocation, useNavigate } from "react-router-dom";
// import axios from "axios";

function DonateThanks(props) {
    const navigate = useNavigate()
    const location = useLocation()
    console.log(location)
    const isnavigate = location.state.isnavigate
    const Locker = location.state.Locker
    const User = location.state.User
    const accountPoint = location.state.accountPoint
    
    console.log(isnavigate)
    console.log(Locker)
    console.log(User)
    const data = {isnavigate: isnavigate, Locker :Locker, User: User}

    // const UrlMainClose = "http://192.168.140.1/mainServo/90" //메인 보관함 닫기

    const onClickHandlerDoorClose = () => {
        navigate('/DoorClose')
        // axios.get(UrlMainClose, {
        // })
        // .then((response) => {
            
        // })
        // .catch(function (error) {
        //     console.log(error)
        // })
    }
    const onClickHandlerBarcodeRead =() => {
        navigate('/BarcodeRead', {state: data})
    }

    return (
        <div>
            <div className={styles.myImg}>
                    <div className={styles.buttonOne}>
                        <p className={styles.textAlign}>
                        기부해 주셔서 감사합니다
                        <br />
                        남아 있는 포인트는 {accountPoint} 입니다
                        <br />
                        <br />
                        책을 추가 기부하시겠습니까?</p>
                        <button className={styles.buttonTwo} onClick={onClickHandlerBarcodeRead} >
                            예
                        </button>
                        <button className={styles.buttonThree} onClick={onClickHandlerDoorClose}>
                            아니요
                        </button>
                    </div>
            </div>
        </div>
        )
}

export default DonateThanks