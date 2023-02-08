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

    console.log(location)

    const isnavigate = location.state.isnavigate
    const Locker = location.state.Locker
    const lockerNo = location.state.Locker.lockerNo
    const User = location.state.User

    var bookList = []
    async function check() {
        try {
        const response = await axios.get(`http://i8b201.p.ssafy.io:8081/backend/bookLog/locker/${lockerNo}`)
        const data = response.data.data
        console.log('fffffff',response.data.data)
        for (let i = 1; response.data.data.length; i++ ){
            const dataInfo = response.data.data[i].bookIsbn
            const postResponse = await axios.post(`http://i8b201.p.ssafy.io:8081/backend/book/find`, dataInfo)
            const postData = postResponse.data.data
            const bookTitle = postData.bookTitle
            this.bookList.push(bookTitle)
            console.log(postData)
            console.log(bookTitle)
        }
        } catch (error) {
        console.error(error);
        }
    }
    check()
    console.log('eeee',bookList)
    
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
                            <p className={styles.h3Text}>수령하실 책을 선택해주세요</p>
                        </div>
                        <div className={styles.textAlignTwo}>{bookList[0]}</div>
                    </div>
                    <button className={styles.homeCircle} onClick={onClickHandlerHome}>
                        <BiHomeAlt className={styles.iconStyle}/>
                    </button>
                </div>
            </div>
        )
}

export default ReceiptConfirm