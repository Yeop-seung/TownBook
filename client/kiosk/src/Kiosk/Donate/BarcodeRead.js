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
            axios.get(`/server/book/find/${e.target.value}`, {
            })
            .then((response) => {
                const onClickHandlerConfirm = () => {
                    navigate('/DonateConfirm', {state : response.data} )
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
        navigate('/')
    }
    const onClickHandlerBarcodeReadError =() => {
        navigate('/BarcodeReadError')
    }
    const onClickHandlerUse = () => {
        navigate('/DonateUse')
    }
    
    // 회원정보를 가지고 있어야 된다.
    return (
        // <div>
            <div>
                <div className={styles.myImg}>
                    <button className={styles.circle} onClick={onClickHandlerUse}>
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