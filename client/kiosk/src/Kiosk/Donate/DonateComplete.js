import React from "react";
import styles from "./DonateComplete.module.css"
import { useNavigate } from "react-router-dom";
// import { Route } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
// import {BsFillArrowRightCircleFill} from "react-icons/bs"

function DonateComplete(props) {
    const navigate = useNavigate()

    const UrlTOneClose = "http://192.168.140.1/servo1/90" //1번 보관함 닫기
    const UrlTwoClose = "http://192.168.140.1/servo2/90 " //2번 보관함 닫기

    const onClickHandlerHome = () => {
        navigate('/Kiosk')
    }
    const onClickHandlerConfirm =() => {
        navigate('/Kiosk/DonateConfirm')
    }
    const onClickHandlerThanks = () => {
        // axios.get(UrlOneClose, {
        // })
        // .then((response) => {
        //     // console.log('eeeee', e.target.value) // 값을 보내준다
        //     navigate('/DonateThanks')
        // })
        
        // .catch(function (error) {
        //     console.log(error)
        // })
        navigate('/Kiosk/DonateThanks')
    }
    const goBack = () => {
        navigate(-1)
    }
// 회원정보를 지금 여기서도 가지고 있어야 된다
// 회원인지 비회원인지 확인 후 감사 인사 다르게 if
    return (
        <div>
            <div className={styles.myImg}>
                <button className={styles.circle}>
                    <AiOutlineArrowLeft className={styles.iconStyle} onClick={goBack}/>
                </button>
                    <div className={styles.buttonOne}>
                        {/* {보관함 번호} */}
                        <p className={styles.textAlign}> 보관함에 책을 넣고</p>
                        <br />
                        <p className={styles.textAlignOne}>완료</p>
                        <p className={styles.textAlignTwo}> 버튼을 눌러주세요</p>
                        <div>
                            <button className={styles.buttonTwo} onClick={onClickHandlerThanks}>
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

export default DonateComplete