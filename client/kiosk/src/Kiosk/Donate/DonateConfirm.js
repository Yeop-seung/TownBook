import React from "react";
import styles from "./DonateConfirm.module.css"
import { useLocation, useNavigate } from "react-router-dom";
import List from "../../ui/List"
import book from "../img/book.jpg"
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';

function DonateConfirm(props) {
        

    const navigate = useNavigate()

    const location = useLocation();
    
    console.log(location.state)
    
    const title = location.state.bookTitle
    let bookURL = location.state.bookIntroductionURL
    if (bookURL === "null.png") {
        console.log(bookURL)
        bookURL = book
        console.log(bookURL)
    }
    const onClickHandlerHome = () => {
        navigate('/')
    }
    const onClickHandlerComplete =() => {
        navigate('/DonateComplete')
    }
    const onClickHandlerBarcodeRead =() => {
        navigate('/BarcodeRead')
    }
    const onClickHandlerBarcodeReadError =() => {
        navigate('/BarcodeReadError')
    }

    // 회원정보를 가지고 있어야 된다.
    return (
        // <div>
            <div>
                <div className={styles.myImg}>
                    <button className={styles.circle} onClick={onClickHandlerBarcodeRead}>
                        <AiOutlineArrowLeft className={styles.iconStyle}/>
                    </button>
                    <div >
                        <div className={styles.buttonOne}>
                            <img src={bookURL} className={styles.book}/>
                            <div className={styles.title}>
                                {title}
                            </div>
                            {/* <List /> */}
                            <p className={styles.textAlignTwo}>기부하시는 책이 맞습니까?</p>
                            <div>
                                <button className={styles.buttonTwo} onClick={onClickHandlerComplete}>
                                    <p className={styles.textAlignOne}>예</p>
                                </button>
                                <button className={styles.buttonThree} onClick={onClickHandlerBarcodeReadError}>
                                    <p className={styles.textAlignOne}>아니오</p>
                                </button>
                            </div>
                        </div>
                    </div>
                    <button className={styles.homeCircle} onClick={onClickHandlerHome}>
                        <BiHomeAlt className={styles.iconStyle}/>
                    </button>
                </div>
            </div>
            /* <label htmlFor="title" onSubmit={}></label>
            <input type="text" required id="title" />
        </div> */
        )
}

export default DonateConfirm