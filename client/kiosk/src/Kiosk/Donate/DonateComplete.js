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
    const location = useLocation()
    const isnavigate = location.state.isnavigate
    const Locker = location.state.Locker
    const User = location.state.User
    const Book = location.state.Book.data
    const LockerNo = location.state.detailLockerNo
    const detailLockerNo = Locker.detailLocker[LockerNo].detailLockerNo

    const UrlOneClose = `http://192.168.140.1/servo${LockerNo}/90` //1번 보관함 닫기

    const Url = 'http://localhost:3000/'

    // console.log(location)
    // console.log(UrlOneClose)
    // console.log(isnavigate)
    // console.log(Locker)
    // console.log(LockerNo)
    // console.log(User)
    // console.log(Book)
    // console.log(Locker.detailLocker[LockerNo].detailLockerNo)

    const realdata = {lockerNo: Locker.lockerNo, detailLockerNo: detailLockerNo, accountNo: User, bookIsbn: Book.bookIsbn}
    // const data = {lockerNo: 3, detailLockerNo: 25, accountNo: 1, bookIsbn: 9791162241950}
    console.log(realdata)
    const goBack = () => {
        navigate(-1)
    }
    const onClickHandlerHome = () => {
        navigate('/')
    }
    async function onClickHandlerThanks() {
        try {
        // const response = await axios.get(UrlOneClose)
        const response = await axios.get(Url)
        const data = response.data

        const postResponse = await axios.post('http://i8b201.p.ssafy.io:8081/backend/bookLog/donateBook', realdata)
        const postData = postResponse.data
        console.log(postData)

        } catch (error) {
        console.error(error);
        }
        if (User === 1) {
            const data = {isnavigate: isnavigate, Locker :Locker, User: User}
            navigate('/DonateThanksNon', {state: data})
        }else {
            const data = {isnavigate: isnavigate, Locker :Locker, User: User}
            navigate('/DonateThanks', {state: data})
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