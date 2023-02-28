import React from "react";
import styles from "./DonateThanksNon.module.css"
import { useLocation, useNavigate } from "react-router-dom";
// import axios from "axios";

function DonateThanksNon(props) {
    const navigate = useNavigate()
    const location = useLocation()

    // const UrlMainClose = "http://192.168.140.1/mainServo/90 " //메인 보관함 닫기
    
    const isnavigate = location.state.isnavigate
    const Locker = location.state.Locker
    const User = location.state.User
    
    // console.log(isnavigate)
    // console.log(Locker)
    // console.log(User)
    const data = {isnavigate: isnavigate, Locker :Locker, User: User}

    const onClickHandlerDoorClose = () => {
        navigate('/DoorClose')
        // axios.get(UrlMainClose, {
        // })
        // .then((response) => {
        //     console.log(response)
        // })
        // .catch(function (error) {
        //     console.log(error)
        // })
    }
    const onClickHandlerBarcodeRead =() => {
        navigate('/BarcodeRead', {state:data})
    }
    return (
        <div>
            <div className={styles.myImg}>
                    <div className={styles.buttonOne}>
                        <p className={styles.textAlign}>기부해 주셔서 감사합니다
                        <br />
                        책을 추가 기부하시겠습니까?</p>
                        <div>
                            <button className={styles.buttonTwo} onClick={onClickHandlerBarcodeRead}>
                                <p className={styles.textAlignOne}>예</p>
                            </button>
                            <button className={styles.buttonThree} onClick={onClickHandlerDoorClose}>
                                <p className={styles.textAlignOne}>아니요</p>
                            </button>
                        </div>
                    </div>
            </div>
        </div>
        )
}

export default DonateThanksNon