// import { faAlignCenter } from "@fortawesome/free-solid-svg-icons";
import React from "react";
import { useRef, useState } from "react";
import { useHistory } from "react-router-dom";
import classes from "./Login.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowLeft } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import axios from "axios";
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
  CardHeader,
} from "reactstrap";
// import { isPropertySignature } from "typescript";

function IdFind(props) {
  const emailInputRef = useRef();
  const pwInputRef = useRef();
  const phonenumberInputRef = useRef();



  const history = useHistory();
  const [hiddenpassword, sethiddenpassword] = React.useState(true);
  const [hiddenid, sethiddenid] = React.useState(false);
  // const [clicked, setClicked] = useState(false);

  // const handleClick = () => {
    
  // };
  const showid = () => {
      sethiddenpassword(true);
      sethiddenid(false);
      // setClicked(true);;
  };

  const showpassword = () => {
      sethiddenid(true);
      sethiddenpassword(false)
      // setClicked(true);
  };
  function findEmail() {
    const enteredPhoneNumber = phonenumberInputRef.current.value;

    axios
        .get(`https://i8b201.p.ssafy.io/backend/account/findEmail/${enteredPhoneNumber}`)
        // .get("https:///townbook/myPage/receive/${receiverNo}")
        .then((response) => {
          console.log(response)
          // if(response=="true"){
          // alert("회원가입에 성공하였습니다.");
          // history.replace("/login");
          // }
          // else{  
          //   alert("회원가입에 실패하였습니다.");
          // }
          alert(`회원님의 이메일은 ${response.data.data}입니다.`)
        })
        .catch((error) => {
          alert("전화번호가 틀렸습니다.");
        });
  }

  function submitHandler(event) {
    event.preventDefault();

    const enteredEmail = emailInputRef.current.value;

    const userInfo = {
      accountEmail: enteredEmail,
    };
    console.log(userInfo);
    // props.onAddInfo(userInfo);

    axios
    .post(
      `https://i8b201.p.ssafy.io/backend/account/tempPassword`,userInfo
      
      //replace는 뒤로가기 버튼 비활성 이미 양식 제출했으므로
    ).then((res) => {
      console.log(res)
      //then 대신에 asynce나 await가능
      alert('입력하신 이메일로 임시 비밀번호가 보내졌습니다. 로그인 후 비밀번호를 다시 바꿔주세요')
      history.replace("/login");

    });
  }
  return (
    <>
      <div className="content">
        <Row style={{ justifyContent: "center" }}>
          <Col lg="7">
            <Card>
            <CardHeader>
            <Link to={"/login"}>
                  <FontAwesomeIcon icon={faArrowLeft} size="xl" color="#C1B5A9"/>
                </Link>
            </CardHeader>
            <CardBody>
              <Row style={{ justifyContent: "center" }}>
                <div>
                  <button 
                    // color="black"
                    type="submit"
                    onClick={showid}
                    // className={classes.style}
                    style={{ backgroundColor: hiddenid ? "black" : "white" }}
                  >
                    아이디 찾기
                  </button>
                </div>
                
                <div>
                  <button
                    // color="black"
                    type="submit"
                    onClick={showpassword}
                    // className={classes.style}
                    style={{ backgroundColor: hiddenpassword ? "black" : "white" }}
                  >
                    비밀번호 찾기
                  </button>
                </div>
              </Row>
            </CardBody>

            <Card hidden={hiddenid}>
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
                          ref={phonenumberInputRef}
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
                    onClick={findEmail}
                    className={classes.style}
                  >
                    찾기
                  </button>
                </Row>
              </CardFooter>
            </Card>

            <Card hidden={hiddenpassword}>
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
            </Card>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default IdFind;
