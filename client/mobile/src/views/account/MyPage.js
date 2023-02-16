import MyPageDonateList from "views/account/MyPageDonateList";
import React, { useEffect, useRef } from "react";
import axios from "axios";
import "../../assets/css/nucleo-icons.css";
// reactstrap components
import AdminPage from "views/account/AdminPage";
import classes from "./Login.module.css";
import { useHistory } from "react-router-dom";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faArrowLeft,
  faAngleUp,
  faAngleDown,
  faXmark,
  faCoins
} from "@fortawesome/free-solid-svg-icons";
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
import WishList from "./WishList";

function MyPage(props) {
  const pwInputRef = useRef();
  const addressInputRef = useRef();
  const phonenumberInputRef = useRef();
  const nicknameInputRef = useRef();
  const birthdayInputRef = useRef();
  const modalpwInputRef = useRef();
  const modalemailInputRef = useRef();

  const [modalSearch, setmodalSearch] = React.useState(false);
  const [modalSearch2, setmodalSearch2] = React.useState(false);
  const [showInfo, setshowInfo] = React.useState(false);
  const [hiddenInfo, sethiddenInfo] = React.useState(true);

  const [showDonate, setshowDonate] = React.useState(false);
  const [showWish, setshowWish] = React.useState(false);

  const toggleModalSearch = () => {
    setmodalSearch(!modalSearch);
  };
  const toggleModalSearch2 = () => {
    setmodalSearch2(!modalSearch2);
  };
  const toggleInfo = () => {
    setshowInfo(!showInfo);
    sethiddenInfo(!hiddenInfo);
  };

  const toggleDonate = () => {
    setshowDonate(!showDonate);
  };

  const toggleWish = () => {
    setshowWish(!showWish);
  };
  const [isLoading, setIsLoading] = React.useState(true);
  const [Donates, setDonates] = React.useState([]);
  const [Receives, setReceives] = React.useState([]);
  const [Point, setPoint] = React.useState("");
  const [verifiedPassword, setverifiedPassword] = React.useState(false);
  const [users, setusers] = React.useState([]);
  const [hiddenadmin, sethiddenadmin] = React.useState(true);
  const [wishList, setwishList] = React.useState([]);
  const accountNo = localStorage.getItem("accountNo");
  const history = useHistory();
  const [lockerRegion, setlockerRegion] = React.useState([]);
  const [LockerRegion, setLockerRegion] = React.useState([]);

  function findRegion() {
    const lockerNo = lockerRegion;
    console.log("라커리전입니다", lockerNo);
    axios
      .put(`https://i8b201.p.ssafy.io/backend/locker/${lockerNo}`)
      .then((res) => {
        // console.log("변경", res);
        // if (res.)
        setLockerRegion(res.data.lockerRegion);
      })
      .catch((error) => {
        alert("지역을 못불러왔습니다.");
      });
  }

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
      accountNo: localStorage.getItem("accountNo"),
    };

    console.log(userInfo);

    axios
      .put("https://i8b201.p.ssafy.io/backend/account/modify", userInfo)
      .then((res) => {
        // console.log("변경", res);
        // if (res.)
        localStorage.setItem("accountBirthDay", res.data.data.accountBirthDay);
        localStorage.setItem("accountNickname", res.data.data.accountNickname);
        localStorage.setItem("accountAddress", res.data.data.accountAddress);
        localStorage.setItem(
          "accountPhoneNumber",
          res.data.data.accountPhoneNumber
        );

        setverifiedPassword(false);
        // history.replace('/mypage')
        alert("변경완료.");
      })
      .catch((error) => {
        alert("비밀번호를 확인해주세요.");
      });
  }
  // console.log(accountNo);

  //개인정보수정 확인 비밀번호
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
        // console.log(res);
      })
      .catch((error) => {
        alert("비밀번호를 확인해주세요.");
      });
  }
  function accountRemove(params) {
    const enteredPw = modalpwInputRef.current.value;
    const enteredEmail = modalemailInputRef.current.value;
    const accountEmail = enteredEmail;
    const accountPw = enteredPw;

    axios
      .put("https://i8b201.p.ssafy.io/backend/account/leave", {
        accountEmail,
        accountPw,
      })
      .then((res) => {
        if (res.data.success) {
          alert("회원탈퇴가 완료되었습니다. 이용해주셔서 감사합니다.");
          localStorage.clear();
          history.replace("/login");
        }
        // console.log("회원탈퇴", res);
      })
      .catch((error) => {
        alert("이메일 또는 비밀번호를 확인해주세요.");
      });
  }

  useEffect(() => {
    console.log("어드민", localStorage.getItem("accountNo"));

    if (localStorage.getItem("accountNo") === "3") {
      sethiddenadmin(false);
      console.log("어드민입니다.");
    } else {
      sethiddenadmin(true);
      console.log("어드민아닙니니다.");
    }

    Promise.all([
      axios.get(`https://i8b201.p.ssafy.io/backend/myPage/donate/${accountNo}`),
      axios.get(
        `https://i8b201.p.ssafy.io/backend/myPage/receive/${accountNo}`
      ),
      axios.get(
        `https://i8b201.p.ssafy.io/backend/myPage/myPoint/${accountNo}`
      ),
      axios.get(`https://i8b201.p.ssafy.io/backend/admin`),
      axios.get(
        `https://i8b201.p.ssafy.io/backend/myPage/wishList/${accountNo}`
      ),
    ])
      .then(([res1, res2, res3, res4, res5]) => {
        console.log("기부내역 받아오는 부분", res1);
        // console.log(res1);
        // console.log("포인트액시오스", res3);
        console.log(res4);
        console.log("찜목록입니다", res5);
        const donates = [];
        const receives = [];
        const usersinfo = [];
        const wishlist = [];
        for (let i = 0; i < res1.data.count; i++) {
          donates.push({ ...res1.data.data[i], id: i + 1 });
        }
        for (let i = 0; i < res2.data.count; i++) {
          receives.push({ ...res2.data.data[i], id: i + 1 });
        }

        for (let i = 0; i < res4.data.count; i++) {
          usersinfo.push({ ...res4.data.data[i], id: i + 1 });
        }

        for (let i = 0; i < res5.data.count; i++) {
          wishlist.push({ ...res5.data.data[i], id: i + 1 });
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
        // console.log("기부", donates);
        // console.log("수령", receives);
        // console.log(res3);
        // console.log("유저인포", usersinfo);
        setwishList(wishlist);
        setDonates(donates);
        setReceives(receives);
        setusers(usersinfo);
        setPoint(res3.data.data);
        setIsLoading(false);
        setlockerRegion(res1.data.bookLogLocker);
        // console.log("합친거", Donates)
      })
      .catch((error) => {
        alert("내역 로딩에 실패하였습니다.");
      });
  }, [hiddenadmin]);
  if (isLoading) {
    <section>
      <p>Loading...</p>
    </section>;
  }
  // console.log("hidden",hiddenadmin)
  // console.log("유저스", users);
  return (
    <>
      <Link to={"/map"}>
        <FontAwesomeIcon
          icon={faArrowLeft}
          size="xl"
          color="#424a51"
          position="absolute"
          zIndex="2000"
          style={{ margin: 15, marginBottom: 5 }}
        />
      </Link>
      <div className="content">
        <Row>
          <Col md="12">
            <Card hidden={hiddenadmin}>
              <CardHeader>관리자페이지</CardHeader>
              <CardBody>
                <AdminPage users={users} />
              </CardBody>
            </Card>
            <Card>
              <CardHeader style={{ paddingTop: 0 }}>
                <Row style={{ justifyContent: "space-between" }}>
                  <CardTitle
                    tag="h4"
                    style={{
                      paddingLeft: 15,
                      marginTop: 10,
                      color: "#424A51",
                      fontFamily: "Nanum Gothic",
                      fontWeight: "bold",
                    }}
                    className="SCD"
                  >
                    내 정보
                  </CardTitle>
                  <Card style={{width:"60%", height:"50", margin:0, border:0}}>
                    <Row style={{ display:"flex", justifyContent:"end"}}>
                  <FontAwesomeIcon
                    icon={faCoins}
                    size="xl"
                    color="#424a51"
                    position="absolute"
                    style={{ margin: 15, marginBottom: 5 }}
                    
                    
                  />
                    <CardBody style={{paddingLeft:0}}>{Point}</CardBody>
                    </Row>
                    
                  </Card>
                  {/* <FontAwesomeIcon
                    icon={faAngleUp}
                    size="xl"
                    color="#424a51"
                    position="absolute"
                    zIndex="2000"
                    style={{ margin: 15, marginBottom: 5 }}
                    onClick={toggleInfo}
                    hidden={!showInfo}
                  />
                  <FontAwesomeIcon
                    icon={faAngleDown}
                    size="xl"
                    color="#424a51"
                    position="absolute"
                    zIndex="2000"
                    style={{ margin: 15, marginBottom: 5 }}
                    onClick={toggleInfo}
                    hidden={showInfo}
                  /> */}
                </Row>
              </CardHeader>
              <CardBody
                style={{
                  paddingInline: 0,
                  fontFamily: "Nanum Gothic",
                  fontWeight: 400,
                }}
              >
               

                <Col>이름 : {localStorage.getItem("accountName")}</Col>

                <Col hidden={!verifiedPassword}>
                  <FormGroup>
                    <label>비밀번호</label>
                    <Input
                      //   defaultValue="비밀번호를 입력해주세요"
                      placeholder="비밀번호를 입력해주세요"
                      type="password"
                      innerRef={pwInputRef}
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
                    <Input
                      //   defaultValue="비밀번호를 입력해주세요"
                      placeholder="닉네임을 입력해주세요"
                      type="text"
                      innerRef={nicknameInputRef}
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
                    <Input
                      //   defaultValue="비밀번호를 입력해주세요"
                      placeholder="생일을 입력해주세요"
                      type="text"
                      innerRef={birthdayInputRef}
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
                    <Input
                      //   defaultValue="비밀번호를 입력해주세요"
                      placeholder="주소를 입력해주세요"
                      type="text"
                      innerRef={addressInputRef}
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
                    <Input
                      //   defaultValue="비밀번호를 입력해주세요"
                      placeholder="휴대폰 번호를 입력해주세요"
                      type="text"
                      innerRef={phonenumberInputRef}
                      // className={classes.style}
                    />
                  </FormGroup>
                </Col>
                <Row style={{ justifyContent: "center" }}>
                  <Button hidden={!verifiedPassword} onClick={modifyMyInfo}>
                    수정완료
                  </Button>
                  <Button
                    hidden={!verifiedPassword}
                    onClick={() => {
                      setverifiedPassword(false);
                    }}
                  >
                    취소
                  </Button>
                  <Button
                    onClick={toggleModalSearch}
                    hidden={verifiedPassword}
                    // style={{
                    //   marginRight: 20,
                    //   margin: 0,
                    //   paddingTop: 0,
                    //   paddingBottom: 0,
                    // }}
                  >
                    개인정보수정
                  </Button>
                  <Button
                    onClick={toggleModalSearch2}
                    hidden={verifiedPassword}
                  >
                    회원탈퇴
                  </Button>
                </Row>
              </CardBody>
            </Card>
          </Col>
          <Col md="12">
            <Card>
              <CardHeader style={{ paddingTop: 0 }}>
                <Row style={{ justifyContent: "space-between" }}>
                  <CardTitle
                    tag="h4"
                    style={{
                      paddingLeft: 15,
                      marginTop: 10,
                      color: "#424A51",
                      fontFamily: "Nanum Gothic",
                      fontWeight: "bold",
                    }}
                  >
                    기부/수령 내역
                  </CardTitle>
                  <FontAwesomeIcon
                    icon={faAngleUp}
                    size="xl"
                    color="#424a51"
                    position="absolute"
                    zIndex="2000"
                    style={{ margin: 15, marginBottom: 5 }}
                    onClick={toggleDonate}
                    hidden={!showDonate}
                  />
                  <FontAwesomeIcon
                    icon={faAngleDown}
                    size="xl"
                    color="#424a51"
                    position="absolute"
                    zIndex="2000"
                    style={{ margin: 15, marginBottom: 5 }}
                    onClick={toggleDonate}
                    hidden={showDonate}
                  />
                </Row>
              </CardHeader>

              <CardBody hidden={!showDonate}>
                <MyPageDonateList Donates={Donates} Receives={Receives} />
              </CardBody>
            </Card>
          </Col>
          <Col md="12">
            <Card>
              <CardHeader style={{ paddingTop: 0 }}>
                {/* <p className="category">
                  Handcrafted by our friends from{" "}
                  <a href="https://nucleoapp.com/?innerRef=1712">NucleoApp</a>
                </p> */}
                <Row style={{ justifyContent: "space-between" }}>
                  <CardTitle
                    tag="h4"
                    style={{
                      paddingLeft: 15,
                      marginTop: 10,
                      color: "#424A51",
                      fontFamily: "Nanum Gothic",
                      fontWeight: "bold",
                    }}
                  >
                    찜 목록
                  </CardTitle>
                  <FontAwesomeIcon
                    icon={faAngleUp}
                    size="xl"
                    color="#424a51"
                    position="absolute"
                    zIndex="2000"
                    style={{ margin: 15, marginBottom: 5 }}
                    onClick={toggleWish}
                    hidden={!showWish}
                  />
                  <FontAwesomeIcon
                    icon={faAngleDown}
                    size="xl"
                    color="#424a51"
                    position="absolute"
                    zIndex="2000"
                    style={{ margin: 15, marginBottom: 5 }}
                    onClick={toggleWish}
                    hidden={showWish}
                  />
                </Row>
              </CardHeader>

              <CardBody className="all-icons" hidden={!showWish}>
                <WishList wishList={wishList} />
                {/* <Row>
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
                </Row> */}
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
                    <Input
                      //   defaultValue="Mike"
                      placeholder="비밀번호를 입력해주세요"
                      type="text"
                      inner
                      innerRef={modalpwInputRef}
                      className={classes.style}
                    />
                  </div>
                </FormGroup>
              </Col>
              <Row style={{ justifyContent: "center", paddingInline: 30 }}>
                <Button
                  className="btn-login"
                  // color="black"
                  type="submit"
                  onClick={() => {
                    verifyPassword();
                    toggleModalSearch();
                  }}
                  className={classes.style}
                >
                  확인
                </Button>
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
              <FontAwesomeIcon
                icon={faXmark}
                size="xl"
                color="black"
                style={{ margin: 0 }}
              />
            </button>
          </ModalBody>
        </Modal>

        <Modal
          modalClassName="modal-search"
          isOpen={modalSearch2}
          toggle={toggleModalSearch2}
        >
          <ModalBody>
            <div>
              <Col>
                <FormGroup>
                  {/* <label>비밀번호</label> */}
                  <div>
                    <Input
                      //   defaultValue="Mike"
                      placeholder="이메일을 입력해주세요"
                      type="text"
                      innerRef={modalemailInputRef}
                      className={classes.style}
                    />
                    <Input
                      //   defaultValue="Mike"
                      placeholder="비밀번호를 입력해주세요"
                      type="text"
                      innerRef={modalpwInputRef}
                      className={classes.style}
                    />
                  </div>
                </FormGroup>
              </Col>
              <Row style={{ justifyContent: "center", paddingInline: 30 }}>
                <Button
                  className="btn-login"
                  // color="black"
                  type="submit"
                  onClick={accountRemove}
                  className={classes.style}
                >
                  확인
                </Button>
              </Row>
            </div>
            <button
              aria-label="Close"
              className="close"
              onClick={() => {
                toggleModalSearch2();
                // handle.clickButton();
              }}
            >
              <FontAwesomeIcon
          icon={faXmark}
          size="xl"
          color="black"
          style={{ margin: 0 }}
        />
            </button>
          </ModalBody>
        </Modal>
      </div>
    </>
  );
}

export default MyPage;
