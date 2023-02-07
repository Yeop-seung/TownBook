import React, { useState } from "react";
import styles from "./DonateUse.module.css"
import { useLocation, useNavigate } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
import {BsFillArrowRightCircleFill} from "react-icons/bs"
import axios from 'axios'

function DonateUse(props) {
    
    const navigate = useNavigate()
    // 페이지 이동
    // const Url = `http://i8b201.p.ssafy.io:8081/backend/account/user/`
    const Url = 'http://localhost:3000/'
    const [inputs, setInputs] = useState({
        barcode : ""
    })

    const location = useLocation()

    const isnavigate = location.state
    console.log(isnavigate)
    
    function barcodeInput(e) {
        let event=window.event || e;
        if(isnavigate === true) {
            console.log(Url + `${e.target.value}`)
            axios.get(Url + `${e.target.value}`, {
            })
            .then((response) => {
                const onClickHandlerBarcodeRead =() => {
                    navigate('/BarcodeRead', {state: response.data})
                }
                onClickHandlerBarcodeRead()
            })
            
            .catch(function (error) {
                console.log(error)
            })
        } else {
            // axios.get(`/server/account/user/${e.target.value}`, {
            // })
            axios.get(Url, {
            })
            .then((response) => {
                // 수령 페이지로 이동
                const onClickHandlerReceiptConfirm =() => {
                    navigate('/ReceiptConfirm', {state: isnavigate})
                }
                onClickHandlerReceiptConfirm()
            })
            .catch(function (error) {
                console.log(error)
            })
            }
        }
    
    const onClickHandlerHome = () => {
        navigate('/')
    }
    const goBack = () => {
        navigate(-1)
    }
    //수령일때랑 기부일때랑 바꿔서 처리 if로 해서
    return (
        // <div>
            <div>
                <div className={styles.myImg}>
                    <button className={styles.circle} onClick={goBack}>
                        <AiOutlineArrowLeft className={styles.iconStyle}/>
                    </button>
                        {/* <div className={styles.buttonOne} onClick={onClickHandlerBarcodeRead}> */}
                        <div className={styles.buttonOne}>
                            <h3 className={styles.h3Align}>STEP1</h3>
                            <p className={styles.textAlignOne}>
                            마이페이지에서
                            <br />
                            QR코드를
                            <br />
                            발급해주세요</p>
                            <BsFillArrowRightCircleFill className={styles.arrowRight} size={80}/>
                            <h3 className={styles.h3AlignOne}>STEP2</h3>
                            <p className={styles.textAlignTwo}>
                            바코드리더기에
                            <br />
                            QR코드를
                            <br />
                            찍어주세요</p>
                        </div>
                        <input className={styles.barcode} type="text" onChange={barcodeInput} autoFocus/>
                        {/* onChange={onChangeHandler} value={barcode}  */}
                    <button className={styles.homeCircle} onClick={onClickHandlerHome} >
                        <BiHomeAlt className={styles.iconStyle}/>
                    </button>
                </div>
            </div>
            /* <label htmlFor="title" onSubmit={}></label>
            <input type="text" required id="title" />
        </div> */
        )
}

export default DonateUse