import React, { useEffect } from "react";
import styles from "./Finish.module.css"
import { useNavigate } from "react-router-dom";
import axios from "axios";

// import { Route } from "react-router-dom";
// import {BsFillArrowRightCircleFill} from "react-icons/bs"

function Finish(props){
    
    const navigate = useNavigate()
    
    const timeout = () => {
        setTimeout(() => {
        navigate('/');
        }, 8000);
    };
      // 컴포넌트가 화면에 다 나타나면 timeout 함수 실행
    useEffect(() => {
        timeout();
        return () => {
          // clear 해줌
        clearTimeout(timeout);
        };
    });

    return (
            <div className={styles.myImg}>
                <div className={styles.buttonOne}>
                    <p className={styles.textAlign}>
                    이용해 주셔서
                    <br />
                    감사합니다</p>
                </div>
            </div>
        )
}

export default Finish