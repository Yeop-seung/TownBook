import React from "react";
import styles from "./ReceiptConfirm.module.css"
import { useLocation, useNavigate } from "react-router-dom";
import List from "../../ui/List"
// import { Route } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
import axios from "axios";

function ReceiptConfirm(props) {
    const navigate = useNavigate()
    const location = useLocation()

    const isnavigate = location.state.isnavigate
    const Locker = location.state.Locker
    const User = location.state.User
    // const Book = location.state.Book
    // const detailLocker = location.state.Locker.detailLocker
    
    console.log(isnavigate)
    console.log(Locker)
    console.log(User)
    // console.log(Book)
    // console.log(detailLocker)

    // const title = Book.data.bookTitle
    axios.get(`http://i8b201.p.ssafy.io:8081/backend/bookLog/locker/${Locker}`, {

    })
    .then((resposne) => {
        console.log(resposne)
    })
    .catch((error) => {
        console.log(error)
    })
    
    const onClickHandlerHome = () => {
        navigate('/')
    }
    const goBack = () => {
        navigate(-1)
    }
    const onClickHandlerReceiptComplete = () => {
        navigate('/ReceiptComplete')
    }
    return (
        // 책 리스트 불러오기 
            <div>
                <div className={styles.myImg}>
                    <button className={styles.circle} onClick={goBack}>
                        <AiOutlineArrowLeft className={styles.iconStyle}/>
                    </button>
                    <div>
                        <div className={styles.buttonOne} onClick={onClickHandlerReceiptComplete}>
                            <p className={styles.h3Text}>수령하실 책을 선택하주세요</p>
                            {/* 책 리스트 */}
                            <List />
                        </div>
                    </div>
                    <button className={styles.homeCircle} onClick={onClickHandlerHome}>
                        <BiHomeAlt className={styles.iconStyle}/>
                    </button>
                </div>
            </div>
        )
}

export default ReceiptConfirm