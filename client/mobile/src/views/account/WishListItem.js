import classes from "./MeetupItem.module.css";
import { Card, Row, Col } from "reactstrap";
import NoticeDetail from "views/notice/NoticeDetail";
import { Route, Switch } from "react-router-dom";
import { Link } from "react-router-dom";
import { faBold } from "@fortawesome/free-solid-svg-icons";

function WishListItem(props) {
  // console.log(props.id)
  // console.log(props.noticeTitle)
  // console.log(props.bookTitle.length)
  // const enteredBookTitle =/ props.bookTitle
  // axios
  // .get(
  //   `https://i8b201.p.ssafy.io/backend/search/searchTitle/${enteredBookTitle}`
  // )
  // .then((res) => {
  //   console.log("북 res", res);
  //   const books = [];
  //   for (let i = 0; i < res.data.count; i++) {
  //     books.push({ ...res.data.data[i], id: i + 1 });
  //   }
  //   setbookList(books);
  //   console.log("book", books);
  //   console.log("bookset", bookList);
  //   toggleModalSearch2();
  // })

  // .catch((error) => {
  //   alert("검색어를 입력해주세요.");
  // });
  let bookTitle;
  if (props.bookTitle.length > 11) {
    bookTitle = props.bookTitle.substr(0, 25) + "...";
  } else {
    bookTitle = props.bookTitle;
  }
  // console.log(props.noticeContent)

  const donateTime = props.bookLogDonateDateTime;
  console.log("아이템프롭스", props);

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
            noticeContent: props.bookLogDonateDateTime,
            bookTitleURL: props.bookTitleURL,
            bookAuthor: props.bookAuthor,
            bookPublisher: props.bookPublisher,
            bookIntroductionURL: props.bookIntroductionURL,
            bookPublishPredate: props.bookPublishPredate,
            MyPageToken: true,
          },
        }}
      > */}
        <Row>
          {/* <p style={{ color: "white" }}>{props.bookTitleURL}</p> */}
          

          <Col style={{paddingRight:0}}>
            
            <img
              alt="..."
              // className="avatar"
              src={props.bookTitleURL}
              className={"image"}
              style={{
                height: "100%",
                width: "70%",
                boxShadow: "rgba(0, 0, 0, 0.5) 3px 3px 10px",
              }}
            />
          </Col>
          <Col  style={{margin:0, padding:0}}>
          <p style={{ color: "#5f5f5f ", fontWeight:"bold" }}>{props.bookTitle}</p>
            <p align="right" style={{ color: "#5f5f5f ", fontWeight:"bold", margin: 0, padding:0, display:"flex", color:"#333333"}}>동네북 위치 : {props.bookLogRegion}</p>
          </Col>
        </Row>

        {/* <address>{props.noticeContent}</address> */}
      {/* </Link> */}
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

export default WishListItem;
