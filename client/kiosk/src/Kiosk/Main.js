import React, { useEffect, useState } from "react";
import styles from "./Main.module.css";
import Modal from "../ui/Modal.js";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import ReceiptModal from "../ui/ReceiptModal";

function Main(props) {
  const navigate = useNavigate();
  const [modalOpen, setModalOpen] = useState(false);
  const [ReceiptmodalOpen, setReceiptModalOpen] = useState(false);

  let isnavigate = true; //true 기부 false 수령

  // const timeout = () => {
  //     setTimeout(() => {
  //     navigate('/Start');
  //     }, 20000);
  // };
  //   // 컴포넌트가 화면에 다 나타나면 timeout 함수 실행
  // useEffect(() => {
  //     timeout();
  //     return () => {
  //       // clear 해줌
  //     clearTimeout(timeout);
  //     };
  // });

  function onClickHandler() {
    isnavigate = true;
    axios
      .get(`http://도메인:8081/backend/locker/`, {})
      .then((response) => {
        console.log(response.data.data);
        if (response.data.data[3].lockerStorage === 0) {
          const showModal = () => {
            setModalOpen(true);
          };
          showModal();
          // 락커 여유공간이 없다면 모달 창 띄우기
        } else {
          const data = { isnavigate: isnavigate, Locker: response.data.data[3] };
          // 기부와 락커 정보 가져오기
          const onClickHandlerMemberSelect = () => {
            navigate("/MemberSelect", { state: data });
          };
          onClickHandlerMemberSelect();
        }
        // 그게 아니면 다음 페이지로 넘어간다
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  const onClickHandlerUse = () => {
    isnavigate = false;
    axios
      .get(`http://도메인:8081/backend/locker/`, {})
      .then((response) => {
        console.log(response.data.data[3]);
        if (response.data.data[3].lockerBookCnt === 0) {
          // if (response.data.data[1].lockerBookCnt === 0) {
          const ReceiptShowModal = () => {
            setReceiptModalOpen(true);
          };
          ReceiptShowModal();
          // 락커의 책과 길이가 같으면 모달창을 띄워줌
        } else {
          console.log(response.data.data);
          const data = { isnavigate: isnavigate, Locker: response.data.data[3] };
          const onClickHandlerDonateUse = () => {
            navigate("/DonateUse", { state: data });
          };
          onClickHandlerDonateUse();
        }
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return (
    <div className={styles.myImg}>
      <button className={styles.buttonLeft} onClick={onClickHandler}>
        도서 기부
      </button>
      <button className={styles.buttonRight} onClick={onClickHandlerUse}>
        도서 수령
      </button>
      {modalOpen && <Modal setModalOpen={setModalOpen} />}
      {ReceiptmodalOpen && <ReceiptModal setReceiptModalOpen={setReceiptModalOpen} />}
    </div>
  );
}

export default Main;
