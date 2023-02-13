import React, { useMemo, useState, Component, useEffect } from "react";
import styles from "./ReceiptConfirm.module.css"
import { useLocation, useNavigate } from "react-router-dom";
// import { Route } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
import axios from "axios";


function ReceiptConfirm(props) {
    const navigate = useNavigate()
    const location = useLocation()
    
    const isnavigate = location.state.isnavigate
    const Locker = location.state.Locker
    const lockerNo = location.state.Locker.lockerNo
    const User = location.state.User
    const detailLocker = location.state.Locker.detailLocker
    
    console.log('detailLocker', detailLocker)
    console.log('detailLocker', detailLocker[0].detailLockerNoInLocker)



    const [bookData, setData] = useState([])
    useEffect(() => {
        axios.get(`http://i8b201.p.ssafy.io:8081/backend/bookLog/locker/${lockerNo}`, {
        })
        .then((response) => {
            const responseData = response.data.data
            setData(responseData)
            // console.log('gggggg1',responseData)
        })
        .catch((error) => {
            console.log(error)
        })
    }, [])

    console.log('bookData', bookData)
    
    const onClickHandlerHome = () => {
        navigate('/')
    }
    const goBack = () => {
        navigate(-1)
    }
    const [selectedItem, setSelectedItem] = useState(null);

    
    function onClickHandlerReceiptComplete (item) {
        setSelectedItem(item);
        console.log('eeeessadfitme',item)
        const data = {isnavigate: isnavigate, Locker :Locker, User: User, Book: item, detailLocker:item.detailLockerNoInLocker}
        navigate('/ReceiptComplete', {state:data})
    }
    return (
        // 책 리스트 불러오기 
                <div className={styles.myImg}>
                    <button className={styles.circle} onClick={goBack}>
                        <AiOutlineArrowLeft className={styles.iconStyle}/>
                    </button>
                    <div>
                        <div className={styles.buttonOne}>
                            <p className={styles.h3Text}>수령하실 책을 선택해주세요</p>
                            <ul className={styles.list}>
                                {bookData.map(item =>
                                <p className={styles.bookList} onClick={() => onClickHandlerReceiptComplete(item)} key={item.id}>
                                {item.detailLockerNoInLocker}.
                                {item.bookTitle}</p>
                                )}
                                {selectedItem && <p>Selected: {selectedItem}</p>}
                            </ul>
                        </div>
                    <button className={styles.homeCircle} onClick={onClickHandlerHome}>
                        <BiHomeAlt className={styles.iconStyle}/>
                    </button>
                </div>
            </div>
        )
}

export default ReceiptConfirm