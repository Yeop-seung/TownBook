import React from "react";
import styles from "./DonateThanks.module.css"
import { useLocation, useNavigate } from "react-router-dom";


function ReceiptThanks(props) {
    
    const navigate = useNavigate()
    const location = useLocation()

    const isnavigate = location.state.isnavigate
    const Locker = location.state.Locker
    const User = location.state.User
    const accountPoint = location.state.accountPoint
    
    console.log(isnavigate)
    console.log(Locker)
    console.log(User)
    const data = {isnavigate: isnavigate, Locker :Locker, User: User}


    const onClickHandlerDoorClose = () => {
        navigate('/DoorClose')
    }
    const onClickHandlerReceiptConfirm = () => {
        navigate('/ReceiptConfirm', {state: data})
    }

    return (
            <div className={styles.myImg}>
                <div className={styles.buttonOne}>
                    <p className={styles.textAlign}>이용해 주셔서 감사합니다
                    <br /> {accountPoint} 포인트가 남아 있습니다
                    <br />
                    <br />
                    책을 추가 수령하시겠습니까?</p>
                    <div>
                        <button className={styles.buttonTwo} onClick={onClickHandlerReceiptConfirm}>
                            <p className={styles.textAlignOne}>예</p>
                        </button>
                        <button className={styles.buttonThree} onClick={onClickHandlerDoorClose}>
                            <p className={styles.textAlignOne}>아니요</p>
                        </button>
                    </div>
                </div>
            </div>  
        )
}

export default ReceiptThanks