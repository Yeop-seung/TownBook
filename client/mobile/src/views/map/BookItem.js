import classes from "./MeetupItem.module.css";
import { Card, CardBody, Modal, Alert, Col, Row } from "reactstrap";
import BookDetail from "views/map/BookDetail";
import { Route, Switch } from "react-router-dom";
import { Link } from "react-router-dom";
import axios from "axios";
// import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowLeft } from "@fortawesome/free-solid-svg-icons";
import {
  faBold,
  faBookBookmark,
  faBookmark,
} from "@fortawesome/free-regular-svg-icons";
import { faBookmark as fabookmark } from "@fortawesome/free-solid-svg-icons";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import "./Map.css";
import React, { useEffect } from "react";

const { kakao } = window;

function BookItem(props) {
  let bookIntroductionURL;
  if (props.bookIntroductionURL.length > 11) {
    bookIntroductionURL = props.bookIntroductionURL.substr(0, 100) + "...";
  } else {
    bookIntroductionURL = props.bookIntroductionURL;
  }

  useEffect(() => {
    
    const accountNo = localStorage.getItem("accountNo");
    const bookLogNo = props.bookLogNo;
    
    axios
      .get(`https://i8b201.p.ssafy.io/backend/myPage/wishList/${accountNo}`)
      .then((res) => {
        console.log('찜목록 불러옴', res )
        let found = false;
        for (let i = 0; i < res.data.count; i++) {
          if (res.data.data[i].bookLogNo === bookLogNo) {
            found = true;
            
          }
        }

        if (found) {
          setbookmark(true)
        } else {
          setbookmark(false)
        }
      });
  
}, []);

  // console.log(props.id)
  // console.log(props.noticeTitle)
  let bookTitle;
  if (props.bookTitle.length > 11) {
    bookTitle = props.bookTitle.substr(0, 17) + "...";
  } else {
    bookTitle = props.bookTitle;
  }
  const [text, setText] = React.useState('');
  useEffect(() => {
    // const container = document.getElementById("map"); //찾으려는 id
    // const options = {
    //   center: new kakao.maps.LatLng(37.49676871972202, 127.02474726969814),
    //   level: 3,
    // };
    // const map = new kakao.maps.Map(container, options);

    fetch('https://www.nl.go.kr/seoji/fu/ecip/dbfiles/CIP_FILES_TBL/4721454_5.txt')
      .then((response) => {response.text(); console.log('이건txt',response)})
      .then(data => setText(data));
  }, []);
  // FileReader
  // console.log(props.noticeContent)
  console.log("item전달받은값", props);
  const [bookmark, setbookmark] = React.useState(false);
  const [modalSearch, setmodalSearch] = React.useState(false);
  const [LockerNo, setLockerNo] = React.useState([]);
  const toggleModalSearch = () => {
    setmodalSearch(!modalSearch);
  };
  const toggleBookmark = () => {
    setbookmark(!bookmark);
  };
  console.log("라겈넘", props.lockerNo);

  
  const lockerNo = props.lockerNo;
  axios
    .get(`https://i8b201.p.ssafy.io/backend/locker/${lockerNo}`)
    .then((res) => {
      console.log("락커액시오스", res);
      setLockerNo(res.data.data.lockerRegion);
    })
    .catch((error) => {
      // alert("error")
    });

  function addWishList(params) {
    const accountNo = localStorage.getItem("accountNo");
    const bookLogNo = props.bookLogNo;
    axios
      .post("https://i8b201.p.ssafy.io/backend/bookLog/wishList", {
        accountNo,
        bookLogNo,
      })
      .then((res) => {
        console.log("찜목록추가성공", res);
        // console.log(res);
        toggleBookmark();
      })
      .catch((error) => {
        alert("error.");
      });
  }

  return (
    <div className={classes.item}>
      {/* // 컴포넌트로 감싸서 jsx 콘텐츠를 감싼다(children 개념) */}
      {/* <Card> */}
      {/* <div className={classes.image}>
          <img src={props.image} alt={props.contentTitle} />
        </div> */}
      {/* <Link
          to={{
            pathname: `/book/${props.id}`,
            state: {
              id: props.id,
              bookTitle: props.bookTitle,
              bookTitleURL: props.bookTitleURL,
              MapToken: true,
            },
          }}
        > */}
      <Link onClick={toggleModalSearch}>
        {/* <p style={{ color: "white" }}>{props.id}</p> */}
        {/* <Card style={{ margin: 0 }}> */}
        <Alert color="#ffffff">
          <Row>
            <img
              alt="..."
              // className="avatar"
              src={props.bookTitleURL}
              className={"image"}
              style={{
                height: "100%",
                width: "30%",
                boxShadow: "rgba(0, 0, 0, 0.5) 3px 3px 10px",
              }}
            />
            <Col>
              <p style={{ color: "#333333", fontSize: 16, fontWeight: "bold", marginBottom:3 }}>
                {bookTitle}
              </p>
              <p style={{ marginBottom:7, fontWeight: "bold"}}>저자: {props.bookAuthor}</p>
              <p style={{ color: "#ec217b", fontWeight: "bolder", marginBottom:3 }}>
                동네북 위치: {LockerNo}
              </p>
            </Col>
          </Row>
        </Alert>
        <hr />
        {/* </Card> */}
      </Link>
      {/* <address>{props.noticeContent}</address> */}

      {/* </Link> */}
      <Modal
        modalClassName="modal-search"
        isOpen={modalSearch}
        toggle={toggleModalSearch}
        style={{ height: "100%" }}
      >
        <Card style={{ height: "100vh" }}>
          <div style={{ display: "flex" }}>
            <button
              aria-label="Close"
              className="close"
              onClick={() => {
                toggleModalSearch();
                // handle.clickButton();
              }}
            >
              <FontAwesomeIcon
                icon={faArrowLeft}
                size="md"
                color="#424a51"
                position="absolute"
                zIndex="2000"
                style={{ margin: 15, marginBottom: 5 }}
              />
            </button>
          </div>

          <hr />

          {/* <Row
          span={24}
            style={{
              display: "flex",
              justifyContent: "space-between",
              marginInline: 15,
               flexWrap: "wrap"
            }}
          > */}
          <div style={{ display: "block", margin: "auto" }}>
            <img
              alt="..."
              // className="avatar"
              src={props.bookTitleURL}
              className={"image"}
              style={{
                height: "50vh",
                width: "35vh",
                boxShadow: "rgba(0, 0, 0, 0.5) 3px 3px 10px",
              }}
            />
          </div>
          <div
            style={{
              fontWeight: "bold",
              color: "#333333",
              marginLeft: 15,
              marginRight: 10,
              marginBottom: 10,
              fontSize: 17,
              marginTop: 15,
            }}
          >
            {props.bookTitle}
          </div>
          <CardBody>
            <Row style={{ marginLeft: "90%", marginBottom: 10 }}>
              <FontAwesomeIcon
                icon={fabookmark}
                size="xl"
                color="#333333"
                onClick={addWishList}
                style={{ marginTop: 5 }}
                hidden={!bookmark}
                // color=""
              />
              <FontAwesomeIcon
                icon={faBookmark}
                size="xl"
                color="#333333"
                onClick={addWishList}
                style={{ marginTop: 5 }}
                hidden={bookmark}
                // color=""
              />
            </Row>

            <Col style={{ fontSize: "12px" }}>
              <Col>
              <Row style={{ display: "flex", flexWrap: "wrap", fontSize:15,color: "#ec217b",
                  fontWeight: "bolder" }}>
                  동네북 위치 : {LockerNo}
                </Row>
                <hr style={{ margin: 2 }} />
                <Row style={{ display: "flex", flexWrap: "wrap" }}>
                  저자 : {props.bookAuthor}
                </Row>
                <hr style={{ margin: 2 }} />
                <Row style={{ display: "flex", flexWrap: "wrap" }}>
                  출판사 : {props.bookPublisher}
                </Row>
                <hr style={{ margin: 2 }} />
                <Row style={{ display: "flex", flexWrap: "wrap" }}>
                  출판일 : {props.bookPublishPredate}
                </Row>
                <hr style={{ margin: 2 }} />
                <Row style={{ display: "flex", flexWrap: "wrap" }}>
                  소개 : {bookIntroductionURL}
                  {/* <img
                    alt="..."
                    // className="avatar"
                    src={props.bookIntroductionURL}
                  /> */}
                </Row>
                <hr style={{ margin: 2 }} />
                
              </Col>
            </Col>

            {/* <Card>
              <div id="map" style={{ width: "10vh", height: "10vh" }}></div>
            </Card> */}
          </CardBody>
          {/* </Row> */}
        </Card>
      </Modal>
      {/* <div className={classes.actions}>
          <button>To Favorites</button>
        </div> */}
      {/* <div>
            {props.noticeTitle}
            {props.noticeContent}
        </div> */}
      {/* </Card> */}
    </div>
  );
}

export default BookItem;
