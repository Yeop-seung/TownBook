/* eslint-disable jsx-a11y/alt-text */
import React from "react";
import styles from "./ReceiptComplete.module.css"
import { useLocation, useNavigate } from "react-router-dom";
import book from "../img/book.jpg"
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
import axios from "axios";

function ReceiptComplete(props) {
    
    const navigate = useNavigate()

    const location = useLocation()
    // console.log(location)
    const isnavigate = location.state.isnavigate    //기부 및 수령인지
    const Locker = location.state.Locker
    const lockerNo = location.state.Locker.lockerNo // 락커번호
    const User = location.state.User        //user number
    const Book = location.state.Book        //isbn

    const detailLocker = location.state.detailLocker
    const bookLog = location.state.bookLog

    const title = Book.bookTitle
    // console.log(Book)
    // console.log(Locker)
    // console.log(lockerNo)

    const data = {isnavigate: isnavigate, Locker :Locker, User: User, Book: Book, detailLocker: detailLocker, bookLog: bookLog}

    const onClickHandlerDonateReceipt =() => {
        
        const UrlServo = `http://192.168.171.1/servo${detailLocker}/0`
        
        const checkTwo = () => {
            axios.get(UrlServo, {
            })
            .then((response) => {
                console.log(response)
            })
            .catch((error) => {
                console.log(error)
            })
        }

        checkTwo()
        navigate('/DonateReceipt', {state: data})
    }

    const onClickHandlerHome = () => {
        navigate('/')
    }
    const onClickHandlerReceiptConfirm = () => {
        navigate('/ReceiptConfirm' ,{state: data})
    }
    const goBack = () => {
        navigate(-1)
    }

    return (
            <div>
                <div className={styles.myImg}>
                    <button className={styles.circle} onClick={goBack}>
                        <AiOutlineArrowLeft className={styles.iconStyle }/>
                    </button>
                    <div className={styles.buttonOne}>
                            <div className={styles.title}>
                                {title}
                            </div>
                        <p className={styles.textAlign}>수령하실 책이 맞습니까?
                        <br/>
                        예를 누르시면 200 포인트가
                        <br/>차감되고 보관함이 열립니다</p>
                        <div>
                            <button className={styles.buttonTwo} onClick={onClickHandlerDonateReceipt}>
                                예
                            </button>
                            <button className={styles.buttonThree} onClick={onClickHandlerReceiptConfirm}>
                                아니요
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

export default ReceiptComplete