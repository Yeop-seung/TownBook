import React from "react"
import styles from "./Main.module.css"
// import axios from "axios"
import { useNavigate } from 'react-router-dom';
// import { useQuery } from "react-query"
// import { apis } from "./Api.js"
// import { Route } from "react-router-dom";
// import MemberSelect from './MemberSelect'

function Main(props) {
    const navigate = useNavigate()

    const onClickHandler = (name) => {
        navigate('/MemberSelect')
        } 
    const onClickHandlerUse = () => {
        navigate('/DonateUse')
    }
    return (
            <div className={styles.myImg}>
                    <button className={styles.buttonLeft} onClick={onClickHandler}>
                        <p >도서 기부</p>
                    </button>
                    <button className={styles.buttonRight} onClick={onClickHandlerUse}>
                        <p >도서 수령</p>
                    </button>
            </div>
        )
}

export default Main