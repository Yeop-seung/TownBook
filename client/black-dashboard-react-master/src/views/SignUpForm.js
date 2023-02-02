import axios from "axios";
import React from "react";
import { useRef } from "react";
import { useHistory } from "react-router-dom";

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
// import { isPropertySignature } from "typescript";

function SignUpForm(props) {
  const emailInputRef = useRef();
  const pwInputRef = useRef();
  const nameInputRef = useRef();
  const addressInputRef = useRef();
  const genderInputRef = useRef();
  const phonenumberInputRef = useRef();
  const nicknameInputRef = useRef();
  const birthdayInputRef = useRef();
  const history = useHistory();

  function submitHandler(event) {
    event.preventDefault();

    const enteredEmail = emailInputRef.current.value;
    const enteredPw = pwInputRef.current.value;
    const enteredName = nameInputRef.current.value;
    const enteredAddress = addressInputRef.current.value;
    const enteredGender = genderInputRef.current.value;
    const enteredPhoneNumber = phonenumberInputRef.current.value;
    const enteredNickname = nicknameInputRef.current.value;
    const enteredBirthDay = birthdayInputRef.current.value;

    const userInfo = {
      accountEmail: enteredEmail,
      accountPw: enteredPw,
      accountName: enteredName,
      accountAddress: enteredAddress,
      accountGender: enteredGender,
      accountPhoneNumber: enteredPhoneNumber,
      accountNickname: enteredNickname,
      accountBirthDay: enteredBirthDay,
    };
    console.log(userInfo);
    // props.onAddInfo(userInfo);
    
    // fetch(
    //   "https://react-getting-started-9d228-default-rtdb.firebaseio.com/meetups.json",
    //   {
    //     method: "POST",
    //     body: JSON.stringify(userInfo),
    //     headers: {
    //       "Content-Type": "application/json",
    //     },
    //   }
    //   //replace는 뒤로가기 버튼 비활성 이미 양식 제출했으므로
    // ).then(() => {
    //   //then 대신에 asynce나 await가능
    //   history.replace("/");
    // });
  
   
    // console.log(context);
    axios
      .post("https://react-getting-started-9d228-default-rtdb.firebaseio.com/meetups.json", userInfo)
      // .get("https:///townbook/myPage/receive/${receiverNo}")
      .then((response) => {
        // if(response=="true"){
        alert("회원가입에 성공하였습니다.");
        history.replace("/");
      // }
      // else{
      //   alert("회원가입에 실패하였습니다.");
      // }
      })
      .catch((error) => {
        alert("회원가입에 실패하였습니다.");
      });
   }
  return (
    <>
      <div className="content">
        <Row>
          <Col md="8">
            <Card>
              <CardHeader>
                <h5 className="title">회원가입</h5>
              </CardHeader>
              <CardBody>
                <Form>
                  {/* <Col className="pr-md-1" md="5">
                      <FormGroup>
                        <label>Company (disabled)</label>
                        <Input
                          defaultValue="Creative Code Inc."
                          disabled
                          placeholder="Company"
                          type="text"
                        />
                      </FormGroup>
                    </Col> */}
                  <Col className="pr-md-1" md="5">
                    <FormGroup>
                      <label>이름</label>
                      <input
                        //   defaultValue="이름을 입력해주세요"
                        placeholder="이름을 입력해주세요"
                        type="text"
                        ref={nameInputRef}
                      />
                    </FormGroup>
                  </Col>
                  <Col className="pr-md-1" md="5">
                    <FormGroup>
                      <label htmlFor="exampleInputEmail1">이메일</label>
                      <input
                        placeholder="mike@email.com"
                        type="email"
                        ref={emailInputRef}
                      />
                    </FormGroup>
                  </Col>

                  <Col className="pr-md-1" md="5">
                    <FormGroup>
                      <label>비밀번호</label>
                      <input
                        //   defaultValue="Mike"
                        placeholder="비밀번호를 입력해주세요"
                        type="text"
                        ref={pwInputRef}
                      />
                    </FormGroup>
                  </Col>

                  <Col className="pr-md-1" md="5">
                    <FormGroup>
                      <label>비밀번호 확인</label>
                      <Input
                        //   defaultValue="Andrew"
                        placeholder="비밀번호를 다시 입력해주세요"
                        type="text"
                      />
                    </FormGroup>
                  </Col>

                  <Row>
                    <Col md="12">
                      <FormGroup>
                        <label>주소</label>
                        <input
                          //   defaultValue="Bld Mihail Kogalniceanu, nr. 8 Bl 1, Sc 1, Ap 09"
                          placeholder="Home Address"
                          type="text"
                          ref={addressInputRef}
                        />
                      </FormGroup>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="12">
                      <FormGroup>
                        <label>성별</label>
                        <input
                          //   defaultValue="Bld Mihail Kogalniceanu, nr. 8 Bl 1, Sc 1, Ap 09"
                          placeholder="성별"
                          type="text"
                          ref={genderInputRef}
                        />
                      </FormGroup>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="12">
                      <FormGroup>
                        <label>핸드폰번호</label>
                        <input
                          //   defaultValue="Bld Mihail Kogalniceanu, nr. 8 Bl 1, Sc 1, Ap 09"
                          placeholder="Home Address"
                          type="text"
                          ref={phonenumberInputRef}
                        />
                      </FormGroup>
                    </Col>
                  </Row>

                  <Row>
                    <Col className="pr-md-1" md="4">
                      <FormGroup>
                        <label>닉네임</label>
                        <input
                          //   defaultValue="Mike"
                          placeholder="City"
                          type="text"
                          ref={nicknameInputRef}
                        />
                      </FormGroup>
                    </Col>

                    <Col className="px-md-1" md="4">
                      <FormGroup>
                        <label>생년월일</label>
                        <input
                          defaultValue="Andrew"
                          placeholder="Country"
                          type="text"
                          ref={birthdayInputRef}
                        />
                      </FormGroup>
                    </Col>
                  </Row>

                  <Row>
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
                  </Row>
                </Form>
              </CardBody>
              <CardFooter>
                <button
                  className="btn-login"
                  type="submit"
                  onClick={submitHandler}
                >
                  가입완료
                </button>
              </CardFooter>
            </Card>
          </Col>

          {/* <Col md="4">
            <Card className="card-user">
              <CardBody>
                <CardText />
                <div className="author">
                  <div className="block block-one" />
                  <div className="block block-two" />
                  <div className="block block-three" />
                  <div className="block block-four" />
                  <a href="#pablo" onClick={(e) => e.preventDefault()}>
                    <img
                      alt="..."
                      className="avatar"
                      src={require("assets/img/anime3.png")}
                    />

                    <h5 className="title">이영재</h5>
                  </a>
                  <p className="description">Ceo/Co-Founder</p>
                </div>
                <div className="card-description">
                  Do not be scared of the truth because we need to restart the
                  human foundation in truth And I love you like Kanye loves
                  Kanye I love Rick Owens’ bed design but the back is...
                </div>
              </CardBody>
              <CardFooter>
                <div className="button-container">
                  <Button className="btn-icon btn-round" color="facebook">
                    <i className="fab fa-facebook" />
                  </Button>
                  <Button className="btn-icon btn-round" color="twitter">
                    <i className="fab fa-twitter" />
                  </Button>
                  <Button className="btn-icon btn-round" color="google">
                    <i className="fab fa-google-plus" />
                  </Button>
                </div>
              </CardFooter>
            </Card>
          </Col> */}
        </Row>
      </div>
    </>
  );
}

export default SignUpForm;
