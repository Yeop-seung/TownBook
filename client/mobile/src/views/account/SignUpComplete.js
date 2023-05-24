import axios from "axios";

import { React, useState, useEffect, useRef } from "react";
// react plugin for creating notifications over the dashboard
import NotificationAlert from "react-notification-alert";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link } from "react-router-dom";
import NoticeList from "views/notice/NoticeList";
import { faArrowLeft } from "@fortawesome/free-solid-svg-icons";
import {
  faHeadset,
  faQrcode,
  faSearch,
  faComments,
  faBullhorn,
  faKey,
  faPlus,
} from "@fortawesome/free-solid-svg-icons";
// reactstrap components
import {
  Alert,
  UncontrolledAlert,
  Button,
  Card,
  CardHeader,
  CardBody,
  CardTitle,
  Row,
  Col,
} from "reactstrap";

function SignUpComplete(props) {


  useEffect(() => {
    axios
      .post(`https://도메인/backend/file`)
      // .get("https:///townbook/myPage/receive/${receiverNo}")
      .then((res) => {
       
     
        // console.log(notices)
      })
      .catch((error) => {
        alert("글로딩에 실패하였습니다.");
      });
  }, []);

  // if (isLoading) {
  //   <section>
  //     <p>Loading...</p>
  //   </section>;
  // }

  return (
    <>
      <Link to={"/map"}>
        <FontAwesomeIcon
          icon={faArrowLeft}
          size="xl"
          color="black"
          position="absolute"
          zIndex="2000"
          style={{ margin: 15, marginBottom: 5 }}
        />
      </Link>
      <div className="content">
        <Row>
          <Col md="6">
            <Card>
              <CardBody>
                회원 가입이 완료되었습니다. 저소득 인증을 하시면 매달 5권의 책을
                수령하실 수 있습니다.
                <Link to={"/map"}>동네북 보러가기</Link>
                <Link to={"/account/LowCertification"}>저소득 인증하러 가기</Link>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default SignUpComplete;
