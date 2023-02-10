import React from "react";
import styles from "./MemberSelect.module.css"
// import { Route } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
import { useLocation, useNavigate } from "react-router-dom";

function MemberSelect(props) {
    const navigate = useNavigate()
    const location = useLocation()

    const isnavigate = location.state.isnavigate // 기부인지
    const locker = location.state.Locker //락커 정보 
    // console.log(locker)

    const data = {isnavigate: isnavigate, Locker: locker }

    const goBack = () => {
        navigate(-1)
    }
    const onClickHandlerHome = () => {
        navigate('/')
    }
    const onClickHandlerUse = () => {
        navigate('/DonateUse', {state: data})
    }
    const onClickHandlerBarcodeRead =() => {
        const data = {isnavigate: isnavigate, Locker: locker , User: 1}
        navigate('/BarcodeRead', {state: data})
    } 
    // 비회원일시 User 번호 1
    return (
        <div>
            <div className={styles.myImg}>
                <button className={styles.circle} onClick={goBack}>
                {/* 수령인지 기부인지 확인하고 뒤로가기 바꾸기 */}
                    <AiOutlineArrowLeft className={styles.iconStyle}/>
                </button>
                <button className={styles.buttonLeft} onClick={onClickHandlerUse}>
                    <p>회원</p>
                </button>
                <button className={styles.buttonRight} onClick={onClickHandlerBarcodeRead}>
                    <p>비회원</p>
                </button>
                <button className={styles.homeCircle} onClick={onClickHandlerHome}>
                    <BiHomeAlt className={styles.iconStyle}/>
                </button>
            </div>
        </div>
        )
}

export default MemberSelect