import React from "react";
import styles from "./ReceiptComplete.module.css"
import List from "../../ui/List"
import { useNavigate } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';

function ReceiptComplete(props) {
    // function submitHandler(event) {
    //     event.preventDefault()
    // }
    const navigate = useNavigate()

    const onClickHandlerHome = () => {
        navigate('/')
    }
    const onClickHandlerReceiptConfirm = () => {
        navigate('/ReceiptConfirm')
    }
    const onClickHandlerDonateReceipt = () => {
        navigate('/DonateReceipt')
    }

    return (
        // <div>
            <div>
                <div className={styles.myImg}>
                    <button className={styles.circle} onClick={onClickHandlerReceiptConfirm}>
                        <AiOutlineArrowLeft className={styles.iconStyle }/>
                    </button>
                    <div className={styles.buttonOne}>
                        <List />
                        {/* 책 리스트 */}
                        <p className={styles.textAlign}>수령하실 책이 맞습니까?
                        <br/>
                        예를 누르시면 포인트가
                        <br/>차감되고 보관함이 열립니다</p>
                        <div>
                            <div className={styles.buttonTwo} onClick={onClickHandlerDonateReceipt}>
                                <p className={styles.textAlignOne}>예</p>
                            </div>
                            <div className={styles.buttonThree} onClick={onClickHandlerReceiptConfirm}>
                                <p className={styles.textAlignOne}>아니오</p>
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

export default ReceiptComplete