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

import React from "react";
function BookItem(props) {
  // console.log(props.id)
  // console.log(props.noticeTitle)

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
  console.log('라겈넘',props.lockerNo)
  const lockerNo = props.lockerNo;
  axios
    .get(`https://i8b201.p.ssafy.io/backend/locker/${lockerNo}`)
    .then((res) => {
      console.log('락커액시오스',res)
      setLockerNo(res.data.data.lockerRegion);
    })
    .catch((error)=> {
      // alert("error")
    })

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
        <Card style={{ margin: 0 }}>
          <Alert color="info">
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

            <p style={{ color: "white" }}>{props.bookTitle}</p>
          </Alert>
        </Card>
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

          <div
            style={{
              fontWeight: "bold",
              marginLeft: 13,
              marginBottom: 10,
              fontSize: 17,
            }}
          >
            {props.bookTitle}
          </div>

          <Row
          span={24}
            style={{
              display: "flex",
              justifyContent: "space-between",
              marginInline: 15,
               flexWrap: "wrap"
            }}
          >
            <Col span={12} style={{ padding: 0, marginRight: 10 }}>
              <img
                alt="..."
                // className="avatar"
                src={props.bookTitleURL}
                className={"image"}
                style={{
                  height: "30vh",
                  width: "20vh",
                  boxShadow: "rgba(0, 0, 0, 0.5) 3px 3px 10px",
                }}
              />
            </Col>

            <Col span={12} style={{ fontSize: "12px" }}>
              <Row style={{ display: "flex" , flexWrap: "wrap"}}>
                <FontAwesomeIcon
                  icon={fabookmark}
                  size="xl"
                  color="black"
                  onClick={addWishList}
                  style={{ marginTop: 5 }}
                  hidden={!bookmark}
                  // color=""
                />
                <FontAwesomeIcon
                  icon={faBookmark}
                  size="xl"
                  color="black"
                  onClick={addWishList}
                  style={{ marginTop: 5 }}
                  hidden={bookmark}
                  // color=""
                />
              </Row>
              <hr style={{ margin: 2 }} />
              <Row style={{ display: "flex" , flexWrap: "wrap"}}>저자 : {props.bookAuthor}</Row>
              <hr style={{ margin: 2 }} />
              <Row style={{ display: "flex" , flexWrap: "wrap"}}>출판사 : {props.bookPublisher}</Row>
              <hr style={{ margin: 2 }} />
              <Row style={{ display: "flex" , flexWrap: "wrap"}}>출판일 : {props.bookPublishPredate}</Row>
              <hr style={{ margin: 2 }} />
              <Row style={{ display: "flex" , flexWrap: "wrap"}}>소개 : {props.bookIntroductionURL}</Row>
              <hr style={{ margin: 2 }} />

              <Row style={{ display: "flex" , flexWrap: "wrap"}}>동네북 위치 : {LockerNo}</Row>
            </Col>
          </Row>
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
