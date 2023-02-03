import React from "react";
// , { useRef, useState }
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
    // const inputs = barcodeValue
    // const [inputs, setInputs] = useState('')
    // const barcodeInput = useRef()
    
    // const onChangeHandler = e =>{
    //     const { value, barcode } = e.target
    //     setInputs({
    //         ...inputs,
    //         [barcode]: value
    //     })
    // }

    // const onReset = () => {
    //     setInputs({
    //     barcode: '',
    //     });
    //     barcodeInput.current.focus();
    // };

    function barcode(e){
        let event=window.event || e;
        // console.log(event)
        console.log('fffff', event.target.value.length)
        // console.log(inputs)
        if(event.target.value.length === "12"){
            // axios.get()
            console.log('123123',event.value)
        } 
        else if (event.target.value.length > "12") {
            // alert('다시 한번 바코드를 찍어주세요')
        }
    }
    
    
    // // 바코드 받아오기
    // const barcodeInput = useRef()

    const onClickHandlerHome = () => {
        navigate('/')
    }
    const onClickHandlerConfirm =() => {
        navigate('/DonateConfirm')
    }
    

    //수령일때랑 기부일때랑 바꿔서 처리 if로 해서
    return (
        // <div>
            <div>
                <div className={styles.myImg}>
                    <button className={styles.circle} onClick={onClickHandlerHome}>
                        <AiOutlineArrowLeft className={styles.iconStyle}/>
                    </button>
                        <div className={styles.buttonOne} onClick={onClickHandlerConfirm}>
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
                        <input className={styles.barcode} type="text" onKeyDown={barcode} autoFocus />
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