import classes from "./MeetupItem.module.css";
import { Card, Alert, Row, Col, Modal, CardBody } from "reactstrap";
import NoticeDetail from "views/notice/NoticeDetail";
import { Route, Switch } from "react-router-dom";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBold, faArrowLeft, faBookmark } from "@fortawesome/free-solid-svg-icons";
import React from "react";
import { faBookmark as fabookmark } from "@fortawesome/free-solid-svg-icons";
import axios
 from "axios";
function LockerBookItem(book) {
  const [modalSearch, setmodalSearch] = React.useState(false);
  const toggleModalSearch = () => {
    setmodalSearch(!modalSearch);
  };
  const [bookmark, setbookmark] = React.useState(false);

  // console.log(book.id)
  // console.log(book.noticeTitle)
  const [LockerRegion, setLockerRegion] = React.useState([]);
  const lockerNo = book.lockerNo
  axios
  .get(`https://i8b201.p.ssafy.io/backend/locker/${lockerNo}`)
  .then((res) => {
    console.log("락커액시오스", res);
    setLockerRegion(res.data.data.lockerRegion);
  })
  .catch((error) => {
    // alert("error")
  });
  // console.log(book.noticeContent)
  // console.log("라커item전달받은값", book);
  const toggleBookmark = () => {
    setbookmark(!bookmark);
  };
  function addWishList() {
    const accountNo = localStorage.getItem("accountNo");
    const bookLogNo = book.bookLogNo;
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
      <Link onClick={toggleModalSearch}>
        {/* <p style={{ color: "white" }}>{book.id}</p> */}
        {/* <Card style={{ margin: 0 }}> */}
        <Alert color="#ffffff">
          <Row>
            <img
              alt="..."
              // className="avatar"
              src={book.bookTitleURL}
              className={"image"}
              style={{
                height: "100%",
                width: "30%",
                boxShadow: "rgba(0, 0, 0, 0.5) 3px 3px 10px",
              }}
            />
            <Col>
              <p style={{ color: "#333333", fontSize: 16, fontWeight: "bold", marginBottom:3 }}>
                {book.bookTitle}
              </p>
              <p style={{ marginBottom:7, fontWeight: "bold"}}>저자: {book.bookAuthor}</p>
              <p style={{ color: "#ec217b", fontWeight: "bolder", marginBottom:3 }}>
                동네북 위치: {LockerRegion}
              </p>
            </Col>
          </Row>
        </Alert>
        <hr />
        {/* </Card> */}
      </Link>

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
              src={book.bookTitleURL}
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
            {book.bookTitle}
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
                <hr style={{ margin: 2 }} />
                <Row style={{ display: "flex", flexWrap: "wrap" }}>
                  저자 : {book.bookAuthor}
                </Row>
                <hr style={{ margin: 2 }} />
                <Row style={{ display: "flex", flexWrap: "wrap" }}>
                  출판사 : {book.bookPublisher}
                </Row>
                <hr style={{ margin: 2 }} />
                <Row style={{ display: "flex", flexWrap: "wrap" }}>
                  출판일 : {book.bookPublishPredate}
                </Row>
                <hr style={{ margin: 2 }} />
                <Row style={{ display: "flex", flexWrap: "wrap" }}>
                  {/* 소개 : {text} */}
                  {/* <img
                    alt="..."
                    // className="avatar"
                    src={book.bookIntroductionURL}
                  /> */}
                </Row>
                <hr style={{ margin: 2 }} />
                <Row style={{ display: "flex", flexWrap: "wrap" }}>
                  동네북 위치 : {LockerRegion}
                </Row>
              </Col>
            </Col>

            {/* <Card>
              <div id="map" style={{ width: "10vh", height: "10vh" }}></div>
            </Card> */}
          </CardBody>
          {/* </Row> */}
        </Card>
      </Modal>
      {/* // 컴포넌트로 감싸서 jsx 콘텐츠를 감싼다(children 개념) */}
      {/* <Card> */}
      {/* <div className={classes.image}>
          <img src={book.image} alt={book.contentTitle} />
        </div> */}
      {/* <Link
        to={{
          pathname: `/book/${book.id}`,
          state: {
            id: book.id,
            bookTitle: book.bookTitle,
            bookTitleURL: book.bookTitleURL,
            bookAuthor: book.bookAuthor,
            bookPublisher: book.bookPublisher,
            bookIntroductionURL: book.bookIntroductionURL,
            bookPublishPredate: book.bookPublishPredate,
            MapToken : true,
          },
        }}
      >
        <p style={{ color: "white" }}>{book.id}</p>
        <img
          alt="..."
          // className="avatar"
          src={book.bookTitleURL}
          className={"image"}
        />
        <p style={{ color: "white" }}>{book.bookTitle}</p>
        <p style={{ color: "white" }}>{LockerRegion}</p> */}

        {/* <address>{book.noticeContent}</address> */}
      {/* </Link> */}
      {/* <div className={classes.actions}>
          <button>To Favorites</button>
        </div> */}
      {/* <div>
            {book.noticeTitle}
            {book.noticeContent}
        </div> */}
      {/* </Card> */}
    </div>
  );
}

export default LockerBookItem;
