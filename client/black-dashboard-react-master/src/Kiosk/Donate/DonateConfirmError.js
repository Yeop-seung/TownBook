import React from "react";
import styles from "./DonateConfirmError.module.css"
import { useNavigate } from "react-router-dom";
import List from "../../ui/List"
// import { Route } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';

function DonateConfirmError(props) {
    // function submitHandler(event) {
    //     event.preventDefault()
    // }
    const navigate = useNavigate()

    const onClickHandlerHome = () => {
        navigate('/')
    }
    const onClickHandlerError =() => {
        navigate('/DonateConfirmError')
    }
    const onClickHandlerUse = () => {
        navigate('/DonateUse')
    }
    const onClickHandlerComplete =() => {
        navigate('/DonateComplete')
    }
    // 회원정보를 가지고 있어야 된다.
    return (
        // <div>
            <div>
                <div className={styles.myImg}>
                    <button className={styles.circle} onClick={onClickHandlerUse}>
                        <AiOutlineArrowLeft className={styles.iconStyle}/>
                    </button>
                    <div>
                        <input type="text" />
                        <div className={styles.buttonOne}>
                            {/* if(name === /) { */}
                            <p className={styles.textAlign}>바코드 리더기에<br />다시 도서 바코드를 찍어주세요 </p>
                            {/* } else{ */}
                            <List />
                            <p className={styles.textAlignTwo}>기부하시는 책이 맞습니까?</p>
                            <div>
                                <button className={styles.buttonTwo} onClick={onClickHandlerComplete} >
                                    <p className={styles.textAlignOne}>예</p>
                                </button>
                                <button className={styles.buttonThree} onClick={onClickHandlerError}>
                                    <p className={styles.textAlignOne}>아니오</p>
                                </button>
                            </div>
                            {/* } */}
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

export default DonateConfirmError