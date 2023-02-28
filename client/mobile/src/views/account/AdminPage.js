// import classes from "./MeetupList.module.css";
import React from "react";
import MyPageDonateItem from "views/account/MyPageDonateItem";
import MyPageReceiveItem from "views/account/MyPageReceiveItem";
// import Table from "reactstrap";
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
  Table,
} from "reactstrap";

function AdminPage(props) {
  console.log('이건관리자프롭',props);
  return (
    <div>
      {/* <Alert>
        <Row>
          <Col>회원번호</Col>
          <Col>이메일</Col>
          <Col>이름(닉네임)</Col>
          <Col>포인트</Col>
          <Col>휴대폰</Col>
          <Col>회원유형</Col>
        </Row>
      </Alert> */}
      <Table  className="tablesorter">
      <thead className="text-primary">
        <tr>
          <th>회원번호</th>
          <th>이메일</th>
          <th>이름(닉네임)</th>
          <th>포인트</th>
          <th>휴대폰</th>
          <th>회원유형</th>
          {/* <th className="text-center">Salary</th> */}
        </tr>
      </thead>
              <tbody>
              {props.users.map((user) => (
                <tr>
                  <td style={{ color: "white" }}>{user.accountNo}</td>
                  <td style={{ color: "white" }}>{user.accountEmail}</td>
                  <td style={{ color: "white" }}>{user.accountName}({user.accountNickname})</td>
                  <td style={{ color: "white" }}>{user.accountPoint}</td>
                  <td style={{ color: "white" }}>{user.accountPhoneNumber}</td>
                  <td>회원유형자리</td>
                </tr>
                ))}
              </tbody>
            </Table>
      
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
