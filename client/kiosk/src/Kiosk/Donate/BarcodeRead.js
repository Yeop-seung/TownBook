import React, { useState } from "react";
import styles from "./BarcodeRead.module.css"
import { useNavigate } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
import axios from "axios";

function BarcodeRead(props) {
    
    const [inputs, setInputs] = useState({
        barcode : ""
    })
    
    function barcodeInput(e) {
        let event=window.event || e;

        if(event.target.value.length === 13){
            axios.get(`http://i8b201.p.ssafy.io:8081/backend/book/find/${e.target.value}`, {
            })
            .then((response) => {
                const onClickHandlerConfirm = () => {
                    navigate('/Kiosk/DonateConfirm', {state : response.data} )
                }
                onClickHandlerConfirm()
            })
            
            .catch(function (error) {
                console.log(error)
            })
        } 
        else if (event.target.value.length > 13) {
            onClickHandlerBarcodeReadError()
        }
        setInputs({
            ...inputs,
            [e.target.name]: e.target.value
        })
    }

    const navigate = useNavigate()

    const onClickHandlerHome = () => {
        navigate('/Kiosk')
    }
    const onClickHandlerBarcodeReadError =() => {
        navigate('/Kiosk/BarcodeReadError')
    }
    const onClickHandlerUse = () => {
        navigate('/Kiosk/DonateUse')
    }
    const goBack = () => {
        navigate(-1)
    }
    
    // 회원정보를 가지고 있어야 된다.
    return (
        // <div>
            <div>
                <div className={styles.myImg}>
                    <button className={styles.circle} onClick={goBack}>
                        <AiOutlineArrowLeft className={styles.iconStyle}/>
                    </button>
                    <div >
                        <div className={styles.buttonOne}>
                            <p className={styles.textAlign}>
                            위에 있는 바코드 리더기에
                            <br />
                            도서 바코드를 찍어주세요</p>
                        </div>
                    </div>
                    <input className={styles.barcode} type="text" name="inputs" onChange={barcodeInput} autoFocus />
                    <button className={styles.homeCircle} onClick={onClickHandlerHome}>
                        <BiHomeAlt className={styles.iconStyle}/>
                    </button>
                </div>
            </div>
        )
}

export default BarcodeRead