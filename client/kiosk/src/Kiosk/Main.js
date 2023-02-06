import React, { useEffect, useState } from "react"
import styles from "./Main.module.css"
import Modal from "../ui/Modal.js"
// import axios from "axios"
import { useNavigate } from 'react-router-dom';

function Main(props) {
    // useEffect(()=>{
    //     axios.get().then().catch();
    // });
    // 페이지 방문하자마자 Ajax요청
    const navigate = useNavigate()
    const [modalOpen, setModalOpen] = useState(false);

    // 모달창 노출
    const showModal = () => {
        setModalOpen(true);
    };

    const onClickHandler = () => {
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
                    <div>
                        <button  onClick={showModal}>도서 기부</button>
                        {/* className={styles.buttonLeft} */}
                        {modalOpen && <Modal setModalOpen={setModalOpen} />}
                    </div>
                    <button className={styles.buttonRight} onClick={onClickHandlerUse}>
                        <p >도서 수령</p>
                    </button>
            </div>
        )
}

export default Main