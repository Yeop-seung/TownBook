import React, { useRef, useState } from "react";
import styles from "./DonateUse.module.css"
import { useNavigate } from "react-router-dom";
// import { Route } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
import {BsFillArrowRightCircleFill} from "react-icons/bs"

function DonateUse(props) {
    // function submitHandler(event) {
    //     event.preventDefault()
    // }
    const navigate = useNavigate()
    // 페이지 이동
    const [inputs, setInputs] = useState({
        barcode: ''
    })
    // 바코드 받아오기
    const barcodeInput = useRef()

    const { barcode } = inputs

    const onChangeHandler = e =>{
        const { value, barcode } = e.target
        console.log(e.target)
        console.log(barcode)
        console.log('fff', e)
        console.log(barcodeInput)
        setInputs({
            ...inputs,
            [barcode]: value
        })
    }

    const onReset = () => {
        setInputs({
        barcode: '',
        });
        barcodeInput.current.focus();
    };

    const onClickHandlerHome = () => {
        navigate('/')
    }
    

    //수령일때랑 기부일때랑 바꿔서 처리 if로 해서
    return (
        // <div>
            <div>
                <div className={styles.myImg}>
                    <button className={styles.circle} onClick={onClickHandlerHome}>
                        <AiOutlineArrowLeft className={styles.iconStyle}/>
                    </button>
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
                        <input type="text" onChange={onChangeHandler} value={barcode} ref={barcodeInput} />
                    <button className={styles.homeCircle} onChange={onClickHandlerHome} >
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