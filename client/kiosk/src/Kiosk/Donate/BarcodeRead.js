import React from "react";
import styles from "./BarcodeRead.module.css";
import { useLocation, useNavigate } from "react-router-dom";
import { AiOutlineArrowLeft } from "react-icons/ai";
import { BiHomeAlt } from "react-icons/bi";
import axios from "axios";

function BarcodeRead(props) {
  const navigate = useNavigate();
  const location = useLocation();
  // console.log(location)

  const isnavigate = location.state.isnavigate;
  const Locker = location.state.Locker;
  const User = location.state.User;

  const data = { isnavigate: isnavigate, Locker: Locker, User: User };

  // console.log(isnavigate)
  // console.log(Locker)
  // console.log(User)

  const onClickHandlerHome = () => {
    navigate("/");
  };
  //홈
  const onClickHandlerBarcodeReadError = () => {
    navigate("/BarcodeReadError", { state: data });
  };
  //다시 찍어달라하기
  const goBack = () => {
    navigate(-1);
  };
  //뒤로가기

  function barcodeInput(e) {
    let event = window.event || e;

    if (event.target.value.length === 13) {
      axios
        .post(`http://도메인:8081/backend/book/find`, `isbn=${event.target.value}`)
        .then((response) => {
          console.log(response.data);
          const data = { isnavigate: isnavigate, Locker: Locker, User: User, Book: response.data };
          const onClickHandlerConfirm = () => {
            navigate("/DonateConfirm", { state: data });
          };
          onClickHandlerConfirm();
        })
        .catch(function (error) {
          console.log(error);
        });
    } else if (event.target.value.length > 13) {
      onClickHandlerBarcodeReadError();
    }
  }
  return (
    // <div>
    <div>
      <div className={styles.myImg}>
        <button className={styles.circle} onClick={goBack}>
          <AiOutlineArrowLeft className={styles.iconStyle} />
        </button>
        <div>
          <div className={styles.buttonOne}>
            <p className={styles.textAlign}>
              우측에 있는 바코드 리더기에
              <br />
              도서 바코드를 찍어주세요
            </p>
          </div>
        </div>
        <input className={styles.barcode} type="text" onChange={barcodeInput} autoFocus />
        <button className={styles.homeCircle} onClick={onClickHandlerHome}>
          <BiHomeAlt className={styles.iconStyle} />
        </button>
      </div>
    </div>
  );
}

export default BarcodeRead;
