// import classes from "./MeetupList.module.css";
import React from "react";
import MyPageDonateItem from "views/account/MyPageDonateItem";
import MyPageReceiveItem from "views/account/MyPageReceiveItem";

import {
  Alert,
  UncontrolledAlert,
  CardBody,
  Row,
  CardHeader,
  CardTitle,
  Card,
  Button,
  Col,
} from "reactstrap";

function AdminPage(props) {


  console.log(props);
  return (
    <div>
    
      <Alert>
        <Row>
          <Col>회원번호</Col>
          <Col>이메일</Col>
          <Col>이름(닉네임)</Col>
          <Col>포인트</Col>
          <Col>휴대폰</Col>
          <Col>회원유형</Col>

        </Row>
      </Alert>
      {props.users.map((user) => (
        <div>
          <Alert>
     
            <Row>
              <Col>
                <p style={{ color: "white" }}>{user.accountNo}</p>
              </Col>
              <Col>
                <p style={{ color: "white" }}>{user.accountEmail}</p>
              </Col>
              <Col>
              <p style={{ color: "white" }}>{user.accountName}({user.accountNickname})</p>
              </Col>
              <Col>
              <p style={{ color: "white" }}>{user.accountPoint}</p>
              </Col>

              <Col>
              <p style={{ color: "white" }}>{user.accountPhoneNumber}</p>

              </Col>
              <Col>
              회원유형자리
              </Col>
            </Row>
          </Alert>
        </div>
      ))}
      {/* {props.Receives.map((receive) => (
        <div>
          <Alert color="info" hidden={hiddenpassword}>
            <MyPageReceiveItem
              //   key={receive.id}
              id={receive.id}
              bookTitle={receive.bookTitle}
              bookLogDonateDateTime={receive.bookLogDonateDateTime}
            />
          </Alert>
        </div>
      ))} */}
    </div>
  );
}
export default AdminPage;
