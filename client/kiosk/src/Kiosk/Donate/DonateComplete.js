import React from "react";
import styles from "./DonateComplete.module.css"
import { useLocation, useNavigate } from "react-router-dom";
// import { Route } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
// import {BsFillArrowRightCircleFill} from "react-icons/bs"
import axios from "axios";

function DonateComplete(props) {
    const navigate = useNavigate()

    const UrlOneClose = "http://192.168.140.1/servo1/90" //1번 보관함 닫기
    const UrlTwoClose = "http://192.168.140.1/servo2/90 " //2번 보관함 닫기

    const location = useLocation()
    const isnavigate = location.state.isnavigate
    const Locker = location.state.Locker
    const User = location.state.User
    const Book = location.state.Book
    // console.log(isnavigate)
    // console.log(Locker)
    // console.log(User)
    // console.log(Book)

    // const data = {lockerNo: Locker.lockerNo, detailLockerNo: Locker.detailLocker[0].detailLockerNo, accountNo: User, bookIsbn: Book.bookIsbn}
    const data = {lockerNo: 2, detailLockerNo: 15, accountNo: 4, bookIsbn: 9791162241950}
    console.log(data)
    
    const goBack = () => {
        navigate(-1)
    }
    const onClickHandlerHome = () => {
        navigate('/')
    }
    async function onClickHandlerThanks() {
        try {
        // const response = await axios.get(UrlOneClose)
        // const data = response.data
    
        const postResponse = await axios.post('http://i8b201.p.ssafy.io:8081/backend/bookLog/donateBook', data)
        const postData = postResponse.data
        console.log(postData)
        if (User === undefined) {
            const data = {isnavigate: isnavigate, Locker :Locker, User: User}
            navigate('/DonateThanksNon', data)
        }else {
            const data = {isnavigate: isnavigate, Locker :Locker, User: User}
            navigate('/DonateThanks', data)
        }
        } catch (error) {
        console.error(error);
        }
    }

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