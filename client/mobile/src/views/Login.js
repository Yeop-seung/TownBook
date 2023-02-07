import { faAlignCenter } from "@fortawesome/free-solid-svg-icons";
import React from "react";
import { useRef } from "react";
import { useHistory } from "react-router-dom";
import classes from "./Login.module.css";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowLeft } from "@fortawesome/free-solid-svg-icons";
// reactstrap components
import {
  Button,
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  CardText,
  FormGroup,
  Form,
  Input,
  Row,
  Col,
} from "reactstrap";
import axios from "axios";
// import { isPropertySignature } from "typescript";

function Login(props) {
  const emailInputRef = useRef();
  const pwInputRef = useRef();

  const history = useHistory();

  function submitHandler(event) {
    event.preventDefault();

    const accountEmail = emailInputRef.current.value;
    const accountPw = pwInputRef.current.value;

    // console.log(userInfo);
    // props.onAddInfo(userInfo);

    axios
      .post(
        "https://i8b201.p.ssafy.io/backend/auth/login",
        {  accountEmail, accountPw }
        // {
        //   method: "POST",
        //   body: JSON.stringify(userInfo),
        //   headers: {
        //     "Content-Type": "application/json",
        //   },
        // }
        //replace는 뒤로가기 버튼 비활성 이미 양식 제출했으므로
      )
      .then((res) => {
        console.log(res)
        // history.replace("/map");
        if (res.data.token) {
          localStorage.clear()
          localStorage.setItem("TOKEN", res.data.token)
          localStorage.setItem("accountNo", res.data.accountDto.accountNo)
          localStorage.setItem("accountEmail", res.data.accountDto.accountEmail)
          window.location.replace("/map");
          // history.replace("/map");

        }
        // console.log(res);
        // console.log(res.data);
        //then 대신에 asynce나 await가능
      }).catch((error) => {
        alert("이메일 또는 비밀번호를 확인해주세요.");
      });
  }
  return (
    <>
      <div className="content">
        <Row style={{ justifyContent: "center" }}>
          <Col lg="7">
            <Card>
              <CardHeader>
                <Link to={"/map"}>
                  <FontAwesomeIcon icon={faArrowLeft} size="xl" color="#C1B5A9"/>
                </Link>
              </CardHeader>
              <CardBody>
                <Form>
                  <Col>
                    <FormGroup style={{ marginTop: 20 }}>
                      {/* <div>
                        <label htmlFor="exampleInputEmail1">이메일</label>
                      </div> */}
                      <div>
                        <input
                          placeholder="이메일을 입력해주세요."
                          type="email"
                          ref={emailInputRef}
                          className={classes.style}
                        />
                      </div>
                    </FormGroup>
                  </Col>

                  <Col>
                    <FormGroup>
                      {/* <label>비밀번호</label> */}
                      <div>
                        <input
                          //   defaultValue="Mike"
                          placeholder="비밀번호를 입력해주세요"
                          type="text"
                          ref={pwInputRef}
                          className={classes.style}
                        />
                      </div>
                    </FormGroup>
                  </Col>

                  {/* <Row>
                    <Col md="8">
                      <FormGroup>
                        <label>About Me</label>
                        <Input
                          cols="80"
                          defaultValue="Lamborghini Mercy, Your chick she so thirsty, I'm in
                            that two seat Lambo."
                          placeholder="Here can be your description"
                          rows="4"
                          type="textarea"
                        />
                      </FormGroup>
                    </Col>
                  </Row> */}
                </Form>
              </CardBody>
              <CardFooter>
                <Row style={{ justifyContent: "center", paddingInline: 30 }}>
                  <button
                    className="btn-login"
                    // color="black"
                    type="submit"
                    onClick={submitHandler}
                    className={classes.style}
                  >
                    로그인
                  </button>
                </Row>
                <Row
                  style={{
                    justifyContent: "space-evenly",
                    paddingInline: 30,
                    paddingTop: 10,
                  }}
                >
                  <Link
                    className="btn-sm"
                    // color="black"
                    type="submit"
                    // onClick={submitHandler}
                    // className={classes.style}
                    to={"/account/signup"}
                  >
                    회원가입
                  </Link>

                  <Link
                    className="btn-sm"
                    // color="black"
                    type="submit"
                    to={"account/idfind/"}
                  >
                    아이디/비밀번호 찾기
                  </Link>
                </Row>
              </CardFooter>
            </Card>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default Login;
