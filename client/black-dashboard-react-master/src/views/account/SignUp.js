import axios from "axios";
import React from "react";
import { useRef } from "react";
import { useHistory } from "react-router-dom";
import classes from "./Login.module.css";
import { Helmet } from "react-helmet";
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
  Modal,
  ModalHeader,
} from "reactstrap";
import DaumPostcode from "react-daum-postcode";
import { data } from "jquery";
// import { isPropertySignature } from "typescript";

function SignUp(props) {
  const [modalSearch, setmodalSearch] = React.useState(false);
  const toggleModalSearch = () => {
    setmodalSearch(!modalSearch);
  };
  /**
   * useState
   */
  const [openPostcode, setOpenPostcode] = React.useState(false);

  /**
   * handler
   */
  const handle = {
    // 버튼 클릭 이벤트
    clickButton: () => {
      setOpenPostcode(true);
    },

    // 주소 선택 이벤트
    selectAddress: (data: any) => {
      console.log(`
                주소: ${data.address},
                우편번호: ${data.zonecode}
            `);
    },
  //  console.log(data)
  };
  
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
      .post(
        "https://react-getting-started-9d228-default-rtdb.firebaseio.com/meetups.json",
        userInfo
      )
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
        <Row style={{ justifyContent: "center" }}>
          <Col md="8">
            <Card>
              <CardHeader>
                <h5 className="title">회원가입</h5>
              </CardHeader>
              <CardBody>
                <Col>
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
                        className={classes.style}
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
                        className={classes.style}
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
                        className={classes.style}
                      />
                    </FormGroup>
                  </Col>

                  <Col className="pr-md-1" md="5">
                    <FormGroup>
                      <label>비밀번호 확인</label>
                      <input
                        //   defaultValue="Andrew"
                        placeholder="비밀번호를 다시 입력해주세요"
                        type="text"
                        className={classes.style}
                      />
                    </FormGroup>
                  </Col>

                  <Col className="pr-md-1" md="8">
                    <FormGroup>
                      <label>주소</label>
                      <button
                        onClick={() => {
                          toggleModalSearch();
                          handle.clickButton();
                        }}
                      >
                        주소검색
                      </button>
                      
                      {handle.data}
                      <input
                        //   defaultValue="Bld Mihail Kogalniceanu, nr. 8 Bl 1, Sc 1, Ap 09"
                        placeholder={data.address}
                        type="text"
                        ref={addressInputRef}
                        className={classes.style}
                      />
                    </FormGroup>
                  </Col>

                  <Col className="pr-md-1" md="8">
                    <FormGroup>
                      <label>성별</label>
                      <input
                        //   defaultValue="Bld Mihail Kogalniceanu, nr. 8 Bl 1, Sc 1, Ap 09"
                        placeholder="성별"
                        type="text"
                        ref={genderInputRef}
                        className={classes.style}
                      />
                    </FormGroup>
                  </Col>

                  <Col className="pr-md-1" md="8">
                    <FormGroup>
                      <label>핸드폰번호</label>
                      <input
                        //   defaultValue="Bld Mihail Kogalniceanu, nr. 8 Bl 1, Sc 1, Ap 09"
                        placeholder="Home Address"
                        type="text"
                        ref={phonenumberInputRef}
                        className={classes.style}
                      />
                    </FormGroup>
                  </Col>

                  <Col className="pr-md-1" md="4">
                    <FormGroup>
                      <label>닉네임</label>
                      <input
                        //   defaultValue="Mike"
                        placeholder="City"
                        type="text"
                        ref={nicknameInputRef}
                        className={classes.style}
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
                        className={classes.style}
                      />
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
                </Col>
              </CardBody>
              <CardFooter>
                <button
                  className="btn-login"
                  type="submit"
                  onClick={submitHandler}
                >
                  가입완료
                </button>
                <div></div>
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
        <Modal
          modalClassName="modal-search"
          isOpen={modalSearch}
          toggle={toggleModalSearch}
        >
          <ModalHeader>
            {/* <Input placeholder="QR이미지" type="text" /> */}
            <div>
              {openPostcode && (
                <DaumPostcode
                  onComplete={handle.selectAddress} // 값을 선택할 경우 실행되는 이벤트
                  autoClose={true} // 값을 선택할 경우 사용되는 DOM을 제거하여 자동 닫힘 설정
                  defaultQuery="판교역로 235" // 팝업을 열때 기본적으로 입력되는 검색어
                />
              )}
            </div>
            <button
              aria-label="Close"
              className="close"
              onClick={() => {
                toggleModalSearch();
                handle.clickButton();
              }}
            >
              <i className="tim-icons icon-simple-remove" />
            </button>
          </ModalHeader>
        </Modal>
      </div>
    </>
  );
}

export default SignUp;
