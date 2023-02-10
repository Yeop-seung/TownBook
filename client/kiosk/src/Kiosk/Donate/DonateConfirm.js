import React from "react";
import styles from "./DonateConfirm.module.css"
import { useLocation, useNavigate } from "react-router-dom";
import book from "../img/book.jpg"
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
import axios from "axios";

function DonateConfirm(props) {
    // 빈보관함이 무엇인지 확인하고 if로 하기  
    // const UrlMainOpen = "http://192.168.140.1/mainServo/0"   //메인 보관함 열기 1번 보관함 열기 2번 보관함 열기
    
    // const Url = 'http://localhost:3000/'
    const navigate = useNavigate()
    const location = useLocation()

    const isnavigate = location.state.isnavigate // 기부인지 수령인지 확인
    const Locker = location.state.Locker // 락커
    const User = location.state.User  // 유저정보
    const Book = location.state.Book  // 책정보

    const detailLocker = location.state.Locker.detailLocker // 안에 서랍장
    
    console.log(isnavigate)
    console.log(Locker)
    console.log(User)
    console.log(Book)
    console.log(detailLocker)

    const title = Book.data.bookTitle  // 책 제목

    // const [detailLockerNo, setDetailLockerNo] = useState()
    // console.log(detailLockerNo)

    let bookURL = location.state.bookIntroductionURL
    if (bookURL === "null.png") {
        bookURL = book
        }
    // 이미지가 없을 때 빈이미지 대신 넣는 이미지
    const onClickHandlerHome = () => {
        navigate('/')
    }
    //홈
    
    const onClickHandlerComplete =() => {
        
        let numbers = 1
        console.log('detailLocker.length',detailLocker.length)
        for (let i = 1; i <= detailLocker.length; i++){
            console.log('fffffff', i)
            console.log(detailLocker[i - 1])
            if (detailLocker[i - 1].bookInDetailLocker === null){
                numbers = i
                console.log('eeesss',detailLocker[i - 1])
                // console.log(numbers)
                break
            }}
            
        console.log('numbers', numbers)
        
        const UrlServo = `http://192.168.140.1/servo${numbers}/0`
        console.log('UrlServo', UrlServo)
        
        const checkTwo = () => {
            console.log('axiosnumbers',numbers)
            axios.get(UrlServo, {
            })
            .then((response) => {
                console.log('gggggg',response)
            })
            .catch((error) => {
                console.log(error)
            })
        }
        checkTwo()
        
        const data = {isnavigate: isnavigate, Locker :Locker, User: User, Book: Book, numbers: numbers}
        console.log('data', data)
        navigate('/DonateComplete', {state: data})
        }
            

    const onClickHandlerBarcodeReadError =() => {
        const data = {isnavigate: isnavigate, Locker :Locker, User: User}
        navigate('/BarcodeReadError', {state: data})
    }
    // 다시 바코드를 찍게
    const goBack = () => {
        navigate(-1)
    }
    // 뒤로가기

    // 회원정보를 가지고 있어야 된다.
    return (
            <div>
                <div className={styles.myImg}>
                    <button className={styles.circle} onClick={goBack}>
                        <AiOutlineArrowLeft className={styles.iconStyle}/>
                    </button>
                    <div >
                        <div className={styles.buttonOne}>
                            <img src={bookURL} className={styles.book}/>
                            <div className={styles.title}>
                                {title}
                            </div>
                            <p className={styles.textAlignTwo}>기부하시는 책이 맞습니까?</p>
                            <div>
                                <button className={styles.buttonTwo} onClick={onClickHandlerComplete}>
                                    <p className={styles.textAlignOne}>예</p>
                                </button>
                                <button className={styles.buttonThree} onClick={onClickHandlerBarcodeReadError}>
                                    <p className={styles.textAlignOne}>아니요</p>
                                </button>
                            </div>
                        </div>
                    </div>
                    <button className={styles.homeCircle} onClick={onClickHandlerHome}>
                        <BiHomeAlt className={styles.iconStyle}/>
                    </button>
                </div>
            </div>
        )
}

export default DonateConfirm