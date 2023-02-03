// import { faAlignCenter } from "@fortawesome/free-solid-svg-icons";
import React from "react";
import { useRef } from "react";
import { useHistory } from "react-router-dom";
import classes from "./Login.module.css";
// import { Link } from "react-router-dom";

// reactstrap components
import {
  // Button,
  Card,
  // CardHeader,
  CardBody,
  CardFooter,
  // CardText,
  FormGroup,
  Form,
  // Input,
  Row,
  Col,
} from "reactstrap";
// import { isPropertySignature } from "typescript";

function IdFind(props) {
  const emailInputRef = useRef();
  const pwInputRef = useRef();

  const history = useHistory();

  function submitHandler(event) {
    event.preventDefault();

    const enteredEmail = emailInputRef.current.value;
    const enteredPw = pwInputRef.current.value;

    const userInfo = {
      accountEmail: enteredEmail,
      accountPw: enteredPw,
    };
    console.log(userInfo);
    // props.onAddInfo(userInfo);

    fetch(
      "https://react-getting-started-9d228-default-rtdb.firebaseio.com/meetups.json",
      {
        method: "POST",
        body: JSON.stringify(userInfo),
        headers: {
          "Content-Type": "application/json",
        },
      }
      //replace는 뒤로가기 버튼 비활성 이미 양식 제출했으므로
    ).then(() => {
      //then 대신에 asynce나 await가능
      history.replace("/");
    });
  }
  return (
    <>
      <div className="content">
        <Row style={{ justifyContent: "center" }}>
          <Col lg="7">
            <CardBody>
              <Row style={{ justifyContent: "center" }}>
                <div>
                  <button
                    // color="black"
                    type="submit"
                    onClick={submitHandler}
                    // className={classes.style}
                  >
                    아이디 찾기
                  </button>
                </div>
                
                <div>
                  <button
                    // color="black"
                    type="submit"
                    onClick={submitHandler}
                    // className={classes.style}
                  >
                    비밀번호 찾기
                  </button>
                </div>
              </Row>
            </CardBody>

            <Card>
              {/* <CardHeader>
                <h5 className="card-header">로그인</h5>
              </CardHeader> */}
              <CardBody>
                <Form>
                  <Col>
                    <FormGroup style={{ marginTop: 20 }}>
                      {/* <div>
                        <label htmlFor="exampleInputEmail1">이메일</label>
                      </div> */}
                      <div>
                        <input
                          placeholder="핸드폰 번호를 입력해주세요."
                          type="name"
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
                          placeholder="이름을 입력해주세요."
                          type="text"
                          ref={pwInputRef}
                          className={classes.style}
                        />
                      </div>
                    </FormGroup>
                  </Col>
                </Form>
              </CardBody>

              <CardFooter>
                <Row style={{ justifyContent: "center", paddingInline: 30 }}>
                  <button
                    // className="btn-login"
                    // color="black"
                    type="submit"
                    onClick={submitHandler}
                    className={classes.style}
                  >
                    찾기
                  </button>
                </Row>
              </CardFooter>
            </Card>

            <Card>
              {/* <CardHeader>
                <h5 className="card-header">로그인</h5>
              </CardHeader> */}
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
                          placeholder="이름을 입력해주세요"
                          type="text"
                          ref={pwInputRef}
                          className={classes.style}
                        />
                      </div>
                    </FormGroup>
                  </Col>
                </Form>
              </CardBody>
              <CardFooter>
                <Row style={{ justifyContent: "center", paddingInline: 30 }}>
                  <button
                    // className="btn-login"
                    // color="black"
                    type="submit"
                    onClick={submitHandler}
                    className={classes.style}
                  >
                    찾기
                  </button>
                </Row>
              </CardFooter>
            </Card>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default IdFind;
