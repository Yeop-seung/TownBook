import MyPageDonateList from "views/account/MyPageDonateList";
import React, { useEffect, useRef } from "react";
import axios from "axios";
// reactstrap components
import classes from "./Login.module.css";
import { useHistory } from "react-router-dom"; 
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
  CardTitle,
  Table,
  Modal,
  ModalHeader,
  ModalBody,
} from "reactstrap";

function MyPage(props) {
  const pwInputRef = useRef();
  const addressInputRef = useRef();
  const phonenumberInputRef = useRef();
  const nicknameInputRef = useRef();
  const birthdayInputRef = useRef();
  const modalpwInputRef = useRef();



  const [modalSearch, setmodalSearch] = React.useState(false);
  const toggleModalSearch = () => {
    setmodalSearch(!modalSearch);
  };
  const [isLoading, setIsLoading] = React.useState(true);
  const [Donates, setDonates] = React.useState([]);
  const [Receives, setReceives] = React.useState([]);
  const [Point, setPoint] = React.useState("");
  const [verifiedPassword, setverifiedPassword] = React.useState(false);
  
  const accountNo = localStorage.getItem("accountNo");
  const history = useHistory();


  function modifyMyInfo(event) {
    event.preventDefault();

    const enteredPw = pwInputRef.current.value;
    const enteredAddress = addressInputRef.current.value;
    const enteredNickname = nicknameInputRef.current.value;
    const enteredBirthDay = birthdayInputRef.current.value;
    const enteredPhoneNumber = phonenumberInputRef.current.value;

    const userInfo = {
      accountPw: enteredPw,
      accountAddress: enteredAddress,
      accountNickname: enteredNickname,
      accountBirthDay: enteredBirthDay,
      accountPhoneNumber: enteredPhoneNumber,
      accountNo: localStorage.getItem('accountNo')
    }
  
    console.log(userInfo)
    axios
      .put("https://i8b201.p.ssafy.io/backend/account/modify", 
        userInfo
      )
      .then((res) => {
        
        console.log("변경",res);
        // if (res.)
        localStorage.setItem('accountBirthDay', res.data.data.accountBirthDay)
        localStorage.setItem('accountNickname', res.data.data.accountNickname)
        localStorage.setItem('accountAddress', res.data.data.accountAddress)
        localStorage.setItem('accountPhoneNumber', res.data.data.accountPhoneNumber)

        setverifiedPassword(false)
        // history.replace('/mypage')
        alert("변경완료.");
      })
      .catch((error) => {
        alert("비밀번호를 확인해주세요.");
      });
  }
  // console.log(accountNo);
  function verifyPassword() {
    const enteredPw = modalpwInputRef.current.value;

    const accountEmail = localStorage.getItem("accountEmail");
    const accountPw = enteredPw;

    axios
      .post("https://i8b201.p.ssafy.io/backend/auth/login", {
        accountEmail,
        accountPw,
      })
      .then((res) => {
        if (res) {
          setverifiedPassword(true);
        }
        console.log(res);
      })
      .catch((error) => {
        alert("비밀번호를 확인해주세요.");
      });
  }

  useEffect(() => {
    Promise.all([
      axios.get(`https://i8b201.p.ssafy.io/backend/myPage/donate/${accountNo}`),
      axios.get(
        `https://i8b201.p.ssafy.io/backend/myPage/receive/${accountNo}`
      ),
      axios.get(
        `https://i8b201.p.ssafy.io/backend/myPage/myPoint/${accountNo}`
      ),
    ])
      // .get("https:///townbook/myPage/receive/${receiverNo}")
      .then(([res1, res2, res3]) => {
        console.log(res1);
        // console.log(res1);
        // console.log(res2);
        // console.log(res4)
        const donates = [];
        const receives = [];
        for (let i = 0; i < res1.data.count; i++) {
          donates.push({ ...res1.data.data[i], id: i + 1 });
        }
        for (let i = 0; i < res2.data.count; i++) {
          receives.push({ ...res2.data.data[i], id: i + 1 });
        }
        // for (const key in res.data) {
        //   const notice = {
        //   id: key,
        //   ...res.data[key
        // };
        // notices.push(notice);
        // };
        // const base64 = btoa(
        //   new Uint8Array(res.data).reduce(
        //     (data, byte) => data + String.fromCharCode(byte),
        //     ""
        //   )
        // );
        // setImageUrl(`data:${res.headers["content-type"]};base64,${base64}`)
        console.log("기부", donates);
        console.log("수령", receives);
        console.log(res3);
        setIsLoading(false);
        setDonates(donates);
        setReceives(receives);
        setPoint(res3.data.data);
        // console.log("합친거", Donates)
      })
      .catch((error) => {
        alert("내역 로딩에 실패하였습니다.");
      });
  }, []);
  if (isLoading) {
    <section>
      <p>Loading...</p>
    </section>;
  }

  return (
    <>
      <div className="content">
        <Row>
          <Col md="12">
            <Card>
              <CardHeader>
                <Row style={{ justifyContent: "space-between" }}>
                  <CardTitle tag="h4">내 정보</CardTitle>
                  <Button
                    onClick={toggleModalSearch}
                    hidden={verifiedPassword}
                  >
                    개인정보수정
                  </Button>
                </Row>
              </CardHeader>
              <CardBody>
                <Col>내포인트 : {Point}</Col>

                <Col>이름 : {localStorage.getItem("accountName")}</Col>

                <Col hidden={!verifiedPassword}>
                  <FormGroup>
                    <label>비밀번호</label>
                    <input
                      //   defaultValue="비밀번호를 입력해주세요"
                      placeholder="비밀번호를 입력해주세요"
                      type="password"
                      ref={pwInputRef}
                      // className={classes.style}
                    />
                  </FormGroup>
                </Col>

                <Col hidden={verifiedPassword}>
                  닉네임 : {localStorage.getItem("accountNickname")}
                </Col>
                <Col hidden={!verifiedPassword}>
                  <FormGroup>
                    <label>닉네임</label>
                    <input
                      //   defaultValue="비밀번호를 입력해주세요"
                      placeholder="닉네임을 입력해주세요"
                      type="text"
                      ref={nicknameInputRef}
                      // className={classes.style}
                    />
                  </FormGroup>
                </Col>
                <Col hidden={verifiedPassword}>
                  생일 : {localStorage.getItem("accountBirthDay")}
                </Col>
                <Col hidden={!verifiedPassword}>
                  <FormGroup>
                    <label>생일</label>
                    <input
                      //   defaultValue="비밀번호를 입력해주세요"
                      placeholder="생일을 입력해주세요"
                      type="text"
                      ref={birthdayInputRef}
                      // className={classes.style}
                    />
                  </FormGroup>
                </Col>
                <Col hidden={verifiedPassword}>
                  주소 : {localStorage.getItem("accountAddress")}
                </Col>
                <Col hidden={!verifiedPassword}>
                  <FormGroup>
                    <label>주소</label>
                    <input
                      //   defaultValue="비밀번호를 입력해주세요"
                      placeholder="주소를 입력해주세요"
                      type="text"
                      ref={addressInputRef}
                      // className={classes.style}
                    />
                  </FormGroup>
                </Col>
                <Col hidden={verifiedPassword}>
                  연락처 : {localStorage.getItem("accountPhoneNumber")}
                </Col>
                <Col hidden={!verifiedPassword}>
                  <FormGroup>
                    <label>연락처</label>
                    <input
                      //   defaultValue="비밀번호를 입력해주세요"
                      placeholder="휴대폰 번호를 입력해주세요"
                      type="text"
                      ref={phonenumberInputRef}
                      // className={classes.style}
                    />
                  </FormGroup>
                </Col>
                <Row style={{justifyContent:"center"}}>
                  <button hidden={!verifiedPassword} onClick={modifyMyInfo}>수정완료</button>
                  <button hidden={!verifiedPassword} onClick={()=>{setverifiedPassword(false)}}>취소</button>
                </Row>
              </CardBody>
              <CardBody>
                {/* <MyPageDonateList/> */}
                {/* <Table className="tablesorter" responsive>
                  <thead className="text-primary">
                    <tr>
                      <th>번호</th>
                      <th>제목</th>
                      <th>작성일자</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>Dakota Rice</td>
                      <td>Niger</td>
                      <td>Oud-Turnhout</td>
                    </tr>
                    <tr>
                      <td>Minerva Hooper</td>
                      <td>Curaçao</td>
                      <td>Sinaai-Waas</td>
                    </tr>
                    <tr>
                      <td>Sage Rodriguez</td>
                      <td>Netherlands</td>
                      <td>Baileux</td>
                    </tr>
                    <tr>
                      <td>Philip Chaney</td>
                      <td>Korea, South</td>
                      <td>Overland Park</td>
                    </tr>
                    <tr>
                      <td>Doris Greene</td>
                      <td>Malawi</td>
                      <td>Feldkirchen in Kärnten</td>
                    </tr>
                    <tr>
                      <td>Mason Porter</td>
                      <td>Chile</td>
                      <td>Gloucester</td>
                    </tr>
                    <tr>
                      <td>Jon Porter</td>
                      <td>Portugal</td>
                      <td>Gloucester</td>
                    </tr>
                  </tbody>
                </Table> */}
                {/* {point} */}
              </CardBody>
            </Card>
          </Col>
          <Col md="12">
            <Card>
              <CardBody>
                <MyPageDonateList Donates={Donates} Receives={Receives} />

                {/* <Table className="tablesorter" responsive>
                  <thead className="text-primary">
                    <tr>
                      <th>이름</th>
                      <th>시</th>
                      <th>동</th>
                      <th className="text-center">포인트</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>Dakota Rice</td>
                      <td>Niger</td>
                      <td>Oud-Turnhout</td>
                      <td className="text-center">$36,738</td>
                    </tr>
                    <tr>
                      <td>Minerva Hooper</td>
                      <td>Curaçao</td>
                      <td>Sinaai-Waas</td>
                      <td className="text-center">$23,789</td>
                    </tr>
                    <tr>
                      <td>Sage Rodriguez</td>
                      <td>Netherlands</td>
                      <td>Baileux</td>
                      <td className="text-center">$56,142</td>
                    </tr>
                    <tr>
                      <td>Philip Chaney</td>
                      <td>Korea, South</td>
                      <td>Overland Park</td>
                      <td className="text-center">$38,735</td>
                    </tr>
                    <tr>
                      <td>Doris Greene</td>
                      <td>Malawi</td>
                      <td>Feldkirchen in Kärnten</td>
                      <td className="text-center">$63,542</td>
                    </tr>
                    <tr>
                      <td>Mason Porter</td>
                      <td>Chile</td>
                      <td>Gloucester</td>
                      <td className="text-center">$78,615</td>
                    </tr>
                    <tr>
                      <td>Jon Porter</td>
                      <td>Portugal</td>
                      <td>Gloucester</td>
                      <td className="text-center">$98,615</td>
                    </tr>
                  </tbody>
                </Table> */}
              </CardBody>
            </Card>
          </Col>
          <Col md="12">
            <Card>
              <CardHeader>
                <h5 className="title">찜목록</h5>
                {/* <p className="category">
                  Handcrafted by our friends from{" "}
                  <a href="https://nucleoapp.com/?ref=1712">NucleoApp</a>
                </p> */}
              </CardHeader>
              <CardBody className="all-icons">
                <Row>
                  <Col
                    className="font-icon-list col-xs-6 col-xs-6"
                    lg="2"
                    md="3"
                    sm="4"
                  >
                    <div className="font-icon-detail">
                      <i className="tim-icons icon-alert-circle-exc" />
                      <p>icon-alert-circle-exc</p>
                    </div>
                  </Col>
                  <Col
                    className="font-icon-list col-xs-6 col-xs-6"
                    lg="2"
                    md="3"
                    sm="4"
                  >
                    <div className="font-icon-detail">
                      <i className="tim-icons icon-align-center" />
                      <p>icon-align-center</p>
                    </div>
                  </Col>
                  <Col
                    className="font-icon-list col-xs-6 col-xs-6"
                    lg="2"
                    md="3"
                    sm="4"
                  >
                    <div className="font-icon-detail">
                      <i className="tim-icons icon-align-left-2" />
                      <p>icon-align-left-2</p>
                    </div>
                  </Col>
                  <Col
                    className="font-icon-list col-xs-6 col-xs-6"
                    lg="2"
                    md="3"
                    sm="4"
                  >
                    <div className="font-icon-detail">
                      <i className="tim-icons icon-app" />
                      <p>icon-app</p>
                    </div>
                  </Col>
                </Row>
              </CardBody>
            </Card>
          </Col>
        </Row>
        <Modal
          modalClassName="modal-search"
          isOpen={modalSearch}
          toggle={toggleModalSearch}
        >
          <ModalBody>
            <div>
              <Col>
                <FormGroup>
                  {/* <label>비밀번호</label> */}
                  <div>
                    <input
                      //   defaultValue="Mike"
                      placeholder="비밀번호를 입력해주세요"
                      type="text"
                      ref={modalpwInputRef}
                      className={classes.style}
                    />
                  </div>
                </FormGroup>
              </Col>
              <Row style={{ justifyContent: "center", paddingInline: 30 }}>
                <button
                  className="btn-login"
                  // color="black"
                  type="submit"
                  onClick={()=>{verifyPassword(); toggleModalSearch();}}
                  className={classes.style}
                >
                  확인
                </button>
              </Row>
            </div>
            <button
              aria-label="Close"
              className="close"
              onClick={() => {
                toggleModalSearch();
                // handle.clickButton();
              }}
            >
              <i className="tim-icons icon-simple-remove" />
            </button>
          </ModalBody>
        </Modal>
      </div>
    </>
  );
}

export default MyPage;
