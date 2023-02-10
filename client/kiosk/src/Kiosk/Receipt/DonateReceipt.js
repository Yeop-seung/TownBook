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
    console.log(location)

    const isnavigate = location.state.isnavigate   // 기부인지
    const Locker = location.state.Locker           // 락커
    const User = location.state.User               // 유저
    const Book = location.state.Book          // 책정보
    
    const detailLocker = Book.bookLog.detailLockerNo


    const UrlOneClose = `http://192.168.140.1/servo${detailLocker}/90` //n번 보관함 닫기

    console.log(Locker)
    console.log(Locker.lockerNo)
    console.log(User)
    console.log('Book',Book.bookLog)
    
    const realData = {
        lockerNo: Locker.lockerNo   , // 동네북 위치
        detailLockerNo: detailLocker, // 서랍장
        accountNo: User, // 유저 넘버
        bookIsbn: Book.bookLog.bookIsbn,  // 책
        bookLogNo: Book.bookLog.bookLogNo
    }

    console.log('realData', realData)
    
    const goBack = () => {
        navigate(-1)
    }
    const onClickHandlerHome = () => {
        navigate('/')
    }
    let accountPoint = 0    

    async function onClickHandlerReceiptThanks() {
        try {
        const response = await axios.get(UrlOneClose)
        // const response = await axios.get(Url)
        
        const postResponse = await axios.post('http://i8b201.p.ssafy.io:8081/backend/bookLog/receiveBook', realData)
        
        const postData = postResponse.data.data
        console.log('postData',postData)

        accountPoint = postData.accountPoint // 포인트
        } catch (error) {
        console.error(error);
        }
        const data = {isnavigate: isnavigate, Locker :Locker, User: User, accountPoint: accountPoint }
        navigate('/ReceiptTanks', {state: data}) // 회원
    }

    return (
        <div>
            <div className={styles.myImg}>
                <button className={styles.circle} onClick={goBack}>
                    <AiOutlineArrowLeft className={styles.iconStyle}/>
                </button>
                {/* 보관함 책을 넣고 빼고  */}
                    <div className={styles.buttonOne}>
                        <p className={styles.textAlign}>{detailLocker} 보관함에 책을 빼고</p>
                        <br />
                        <p className={styles.textAlignOne}>완료</p>
                        <p className={styles.textAlignTwo}> 버튼을 눌러주세요</p>
                        <div>
                            <button className={styles.buttonTwo} onClick={onClickHandlerReceiptThanks}>
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

export default DonateReceipt