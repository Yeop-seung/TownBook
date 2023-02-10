import React, { useEffect } from "react";
import styles from "./DonateComplete.module.css"
import { useLocation, useNavigate } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
import axios from "axios";

function DonateComplete(props) {
    const navigate = useNavigate()
    const location = useLocation()
    
    const isnavigate = location.state.isnavigate   // 기부인지
    const Locker = location.state.Locker           // 락커
    const User = location.state.User               // 유저
    const Book = location.state.Book.data          // 책정보
    const numbers = location.state.numbers         // 열린 서랍장 정보
    
    const detailLockerNo = Locker.detailLocker[numbers-1].detailLockerNo 
    const detailLockerNoInLocker = Locker.detailLocker[numbers-1].detailLockerNoInLocker
    // const detailLockerIsEmpty = Locker.detailLocker[LockerNo].detailLockerIsEmpty

    const UrlOneClose = `http://192.168.140.1/servo${numbers}/90` //n번 보관함 닫기

    // const Url = 'http://localhost:3000/'

    console.log('location', location)
    // console.log(Locker)
    console.log(Locker.lockerNo)
    // console.log(User)
    // console.log(Book)
    // console.log(Book.bookIsbn)
    console.log('detailLockerNo', detailLockerNo)
    console.log('detailLockerNoInLocker',detailLockerNoInLocker)
    // const UrlServo = `http://192.168.140.1/servo${numbers}/0`
    
    const realData = {
        lockerNo: Locker.lockerNo   , // 동네북 위치
        detailLockerNo: detailLockerNo, // 서랍장
        accountNo: User, //
        bookIsbn: Book.bookIsbn,
    }
    // const realData = {
    //     lockerNo: 2, // 동네북 위치
    //     detailLockerNo: 12, // 서랍장
    //     accountNo: User, //
    //     bookIsbn: Book.bookIsbn,
    // }
    console.log('realData', realData)
    const goBack = () => {
        navigate(-1)
    }
    const onClickHandlerHome = () => {
        navigate('/')
    }
    let accountPoint = 0

    async function onClickHandlerThanks() {
        try {
        const response = await axios.get(UrlOneClose)
        // const response = await axios.get(Url)
        
        const postResponse = await axios.post('http://i8b201.p.ssafy.io:8081/backend/bookLog/donateBook', realData)
        
        const postData = postResponse.data.data
        console.log('postData',postData)

        accountPoint = postData.accountPoint // 포인트
        } catch (error) {
        console.error(error);
        }
        if (User === 1) {
            const data = {isnavigate: isnavigate, Locker :Locker, User: User}
            navigate('/DonateThanksNon', {state: data}) // 비회원시
        }else {
            const data = {isnavigate: isnavigate, Locker :Locker, User: User, accountPoint: accountPoint}
            navigate('/DonateThanks', {state: data}) // 회원
        }
    }

    return (
        <div>
            <div className={styles.myImg}>
                <button className={styles.circle}>
                    <AiOutlineArrowLeft className={styles.iconStyle} onClick={goBack}/>
                </button>
                    <div className={styles.buttonOne}>
                        <p className={styles.textAlign}> {detailLockerNoInLocker}번 보관함에 책을 넣고</p>
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