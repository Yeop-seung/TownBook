import axios from "axios";
import React from "react";
import { useRef } from "react";
import { useHistory } from "react-router-dom";
import classes from "./Login.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowLeft } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
// import { Helmet } from "react-helmet";
// reactstrap components
import {
  // Button,
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  // CardText,
  FormGroup,
  Label,
  Input,
  // Form,
  // Input,
  Row,
  Col,
  Modal,
  ModalHeader,
  Button,
} from "reactstrap";
import DaumPostcode from "react-daum-postcode";
import { data } from "jquery";

// import { isPropertySignature } from "typescript";

function SignUp(props) {
  const [certifyNumber, setcertifyNumber] = React.useState("");
  const [modalSearch, setmodalSearch] = React.useState(false);
  const [modalSearch2, setmodalSearch2] = React.useState(false);

  const [emailHidden, setemailHidden] = React.useState(false);
  const [emailToken, setemailToken] = React.useState(false);
  const toggleModalSearch = () => {
    setmodalSearch(!modalSearch);
  };

  const toggleModalSearch2 = () => {
    setmodalSearch2(!modalSearch2);
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
      addressInputRef.current.value = data.address;
      toggleModalSearch();
      console.log(`
                주소: ${data.address},
                우편번호: ${data.zonecode}
            `);
    },
    //  console.log(data)
  };
  // const [password, setPassword] = React.useState('');

  const emailInputRef = useRef();
  const pwInputRef = useRef();
  const nameInputRef = useRef();
  const addressInputRef = useRef();
  const genderInputRef = useRef();
  const phonenumberInputRef = useRef();
  const nicknameInputRef = useRef();
  const birthdayInputRef = useRef();
  const history = useHistory();
  const pwSubmitRef = useRef();
  const certifynumInputRef = useRef();

  function emailConfirm(event) {
    event.preventDefault();

    const enteredEmail = emailInputRef.current.value;
    const email = {
      email: enteredEmail,
    };
    console.log(email);
    // axios.defaults.headers.post['Content-Type'] = 'application/json';
    axios
      .post("https://i8b201.p.ssafy.io/backend/account/emailConfirm", email)
      .then((response) => {
        console.log(response.data);
        setcertifyNumber(response.data);
        setemailHidden(true);
        alert("작성하신 이메일로 인증번호를 보냈습니다.");
      })
      .catch((error) => {
        alert("이메일을 입력해주세요!");
      });
  }

  function numberConfirm(event) {
    event.preventDefault();

    const enteredCertify = certifynumInputRef.current.value;
    if (enteredCertify === certifyNumber) {
      setemailHidden(false);
      alert("인증완료");
      setemailToken(true);
    } else {
      alert("인증번호가 일치하지 않습니다. 다시 입력해주세요.");
    }
    // const email = {
    //   email: enteredEmail,
    // };
    // console.log(email);
    // // axios.defaults.headers.post['Content-Type'] = 'application/json';
    // axios
    //   .post("https://i8b201.p.ssafy.io/backend/account/emailConfirm", email)
    //   .then((response) => {
    //     console.log(response);
    //     // hidden
    //   })
    //   .catch((error) => {
    //     alert("이메일 인증에 실패하였습니다.");
    //   });
  }
  function submitHandler(event) {
    if (!emailToken) {
      alert("이메일 인증을 완료해주세요");
      //위치를 이메일로?
    } else {
      event.preventDefault();
      const enteredEmail = emailInputRef.current.value;
      const enteredPw = pwInputRef.current.value;
      const enteredName = nameInputRef.current.value;
      const enteredAddress = addressInputRef.current.value;
      const enteredGender = genderInputRef.current.value;
      const enteredPhoneNumber = phonenumberInputRef.current.value;
      const enteredNickname = nicknameInputRef.current.value;
      const enteredBirthDay = birthdayInputRef.current.value;
      const enteredPwSubmit = pwSubmitRef.current.value;

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
      // console.log(userInfo);
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
      if (
        userInfo.accountEmail == "" ||
        userInfo.accountPw == "" ||
        userInfo.accountName == "" ||
        userInfo.accountAddress == "" ||
        userInfo.accountGender == "" ||
        userInfo.accountPhoneNumber == "" ||
        userInfo.accountNickname == "" ||
        userInfo.accountBirthDay == ""
      ) {
        alert("빈칸을 모두 채워주세요");
      } else if (userInfo.accountPw != enteredPwSubmit) {
        alert("비밀번호를 확인해주세요");
      } else {
        axios
          .post("https://i8b201.p.ssafy.io/backend/account/signup", userInfo)
          // .get("https:///townbook/myPage/receive/${receiverNo}")
          .then((res) => {
            console.log(res);
            localStorage.clear();
            localStorage.setItem("TOKEN", res.data.token);
            localStorage.setItem("accountNo", res.data.accountDto.accountNo);
            localStorage.setItem(
              "accountEmail",
              res.data.accountDto.accountEmail
            );
            localStorage.setItem(
              "accountBirthDay",
              res.data.accountDto.accountBirthDay
            );
            localStorage.setItem(
              "accountAddress",
              res.data.accountDto.accountAddress
            );
            localStorage.setItem(
              "accountName",
              res.data.accountDto.accountName
            );
            localStorage.setItem(
              "accountNickname",
              res.data.accountDto.accountNickname
            );
            localStorage.setItem(
              "accountPhoneNumber",
              res.data.accountDto.accountPhoneNumber
            );
            // if(response=="true"){
            // toggleModalSearch2();
            // alert("회원가입에 성공하였습니다.");
            setemailToken(false);
            history.replace("/account/signupcomplete");
            // }
            // else{
            //   alert("회원가입에 실패하였습니다.");
            // }
            
          })
          .catch((error) => {
            alert("회원가입에 실패하였습니다.");
          });
      }
    }
  }
  return (
    <>
      <div className="content">
        <Row style={{ justifyContent: "center" }}>
          <Col md="8">
            <Card>
              <CardHeader>
                <Row
                  style={{ justifyContent: "space-between", paddingInline: 15 }}
                >
                  <Link to={"/login"}>
                    <FontAwesomeIcon
                      icon={faArrowLeft}
                      size="xl"
                      color="#C1B5A9"
                    />
                  </Link>
                  <h5 className="title">회원가입</h5>
                </Row>
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
                      <Label for="name">이름</Label>
                      <Input
                        //   defaultValue="이름을 입력해주세요"
                        placeholder="이름을 입력해주세요"
                        type="text"
                        innerRef={nameInputRef}
                        className={classes.style}
                        id="name"
                      />
                    </FormGroup>
                  </Col>
                  <Col className="pr-md-1" md="5">
                    <FormGroup>
                      <Row>
                        <Label htmlFor="exampleInputEmail1">이메일</Label>
                      </Row>

                      <Input
                        placeholder="ssafy@email.com"
                        type="email"
                        innerRef={emailInputRef}
                        className={classes.style}
                        hidden={emailHidden}
                      />
                      <Button
                        className="btn-simple"
                        color="primary"
                        size="sm"
                        onClick={emailConfirm}
                        hidden={emailHidden}
                      >
                        이메일 인증
                      </Button>
                      <Input
                        placeholder="인증번호를 입력해주세요"
                        type="email"
                        innerRef={certifynumInputRef}
                        className={classes.style}
                        hidden={!emailHidden}
                      />
                      <Button
                        className="btn-simple"
                        color="primary"
                        hidden={!emailHidden}
                        onClick={numberConfirm}
                      >
                        확인
                      </Button>
                    </FormGroup>
                  </Col>

                  <Col className="pr-md-1" md="5">
                    <FormGroup>
                      <Label>비밀번호</Label>
                      <Input
                        placeholder="비밀번호를 입력해주세요"
                        type="password"
                        innerRef={pwInputRef}
                        className={classes.style}
                      />
                    </FormGroup>
                  </Col>

                  <Col className="pr-md-1" md="5">
                    <FormGroup>
                      <label>비밀번호 확인</label>
                      <Input
                        placeholder="비밀번호를 다시 입력해주세요"
                        type="password"
                        className={classes.style}
                        innerRef={pwSubmitRef}
                      />
                    </FormGroup>
                  </Col>

                  <Col className="pr-md-1" md="8">
                    <FormGroup>
                      <Label>주소</Label>

                      {handle.data}
                      <Input
                        //   defaultValue="Bld Mihail Kogalniceanu, nr. 8 Bl 1, Sc 1, Ap 09"
                        placeholder={data.address}
                        type="text"
                        innerRef={addressInputRef}
                        className={classes.style}
                      />

                      <Button
                        className="btn-simple"
                        color="primary"
                        size="sm"
                        onClick={() => {
                          toggleModalSearch();
                          handle.clickButton();
                        }}
                      >
                        주소검색
                      </Button>
                    </FormGroup>
                  </Col>

                  <Col className="pr-md-1" md="8">
                    <FormGroup>
                      <div>성별</div>
                      <div>
                        <Label
                          for="exampleRadio"
                          style={{
                            marginRight: "3rem",
                          }}
                        >
                          <Input
                            type="radio"
                            name="gender"
                            id="male"
                            innerRef={genderInputRef}
                            value="0"
                            style={{
                              position: "unset",
                              marginTop: "0.3rem",
                              marginLeft: "0rem",
                              marginRight: "0.5rem",
                            }}
                          />
                          남
                        </Label>
                        <Label
                          for="exampleRadio"
                          style={{
                            marginRight: "3rem",
                          }}
                        >
                          <Input
                            type="radio"
                            name="gender"
                            id="female"
                            innerRef={genderInputRef}
                            value="1"
                            style={{
                              position: "unset",
                              marginTop: "0.3rem",
                              marginLeft: "0rem",
                              marginRight: "0.5rem",
                            }}
                          />
                          여
                        </Label>
                      </div>

                      {/* <select ref={genderInputRef}>
                        <option key="man" value="0">
                        남
                        </option>
                        <option key="woman" value="1">
                          여
                        </option>
                      </select> */}
                    </FormGroup>
                  </Col>

                  <Col className="pr-md-1" md="8">
                    <FormGroup>
                      <Label>핸드폰번호</Label>
                      <Input
                        //   defaultValue="Bld Mihail Kogalniceanu, nr. 8 Bl 1, Sc 1, Ap 09"
                        placeholder="-를 제외하고 입력해주세요"
                        type="text"
                        innerRef={phonenumberInputRef}
                        className={classes.style}
                      />
                    </FormGroup>
                  </Col>

                  <Col className="pr-md-1" md="4">
                    <FormGroup>
                      <Label>닉네임</Label>
                      <Input
                        //   defaultValue="Mike"
                        placeholder="닉네임을 입력해주세요"
                        type="text"
                        innerRef={nicknameInputRef}
                        className={classes.style}
                      />
                    </FormGroup>
                  </Col>

                  <Col className="px-md-1" md="4">
                    <FormGroup>
                      <Label>생년월일</Label>
                      <Input
                        // defaultValue=""
                        placeholder="생년월일을 입력해주세요"
                        type="text"
                        innerRef={birthdayInputRef}
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
        </Row>
        <Modal
          modalClassName="modal-search"
          isOpen={modalSearch}
          toggle={toggleModalSearch}
          // style={{ width: "70%" }}
        >
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
        </Modal>
      </div>
    </>
  );
}

export default SignUp;
