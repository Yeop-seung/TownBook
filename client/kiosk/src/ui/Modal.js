import React, { useEffect }  from 'react';
// , { useEffect, useRef }
import styles from "./Modal.module.css"
import { AiOutlineClose } from "react-icons/ai";

function Modal( { setModalOpen, id, title, content, writer }) {
    
    // 모달 끄기 (X버튼 onClick 이벤트 핸들러)
    const closeModal = () => {
        setModalOpen(false);
    };
    
    // 모달 외부 클릭시 끄기 처리
    // Modal 창을 useRef로 취득
    // const modalRef = useRef< HTMLDivElement >(null);
    
    // useEffect(() => {
    //     // 이벤트 핸들러 함수
    //     const handler = () => {
    //         // mousedown 이벤트가 발생한 영역이 모달창이 아닐 때, 모달창 제거 처리
    //         if (modalRef.current && !modalRef.current.contains(event.target)) {
    //             setModalOpen(false);
    //         }    
    //     };
        
    //     // 이벤트 핸들러 등록
    //     document.addEventListener('mousedown', handler);
    //     // document.addEventListener('touchstart', handler); // 모바일 대응
        
    //     return () => {
    //         // 이벤트 핸들러 해제
    //         document.removeEventListener('mousedown', handler);
    //         // document.removeEventListener('touchstart', handler); // 모바일 대응
    //     };
    // });
    const timeout = () => {
        setTimeout(() => {
        closeModal()
        }, 5000);
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
        <div  className={styles.container}>
                <AiOutlineClose className={styles.close} onClick={closeModal}/>
            <p className={styles.text}> 죄송합니다.
            <br />지금 동네북에 책이
            <br />가득 차 있습니다.
            <br />다른 동네북을 이용해주세요.
            </p>
        </div>
    );
}
export default Modal