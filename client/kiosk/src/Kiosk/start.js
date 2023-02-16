import React from "react";
import { useNavigate } from "react-router-dom";
import bookLog from "../Kiosk/img/TownBookLog.png";
import styles from "./start.module.css"

function Start(params) {
    const navigate = useNavigate()

    const onClickStart = () => {
        navigate('/')
    }

    return (
        <div >
            <img src={bookLog} alt={bookLog} className={styles.log} onClickStart={onClickStart}/>
        </div>
    )
}

export default Start