import classes from "./MeetupItem.module.css";
import { Card, Row, Col } from "reactstrap";
import NoticeDetail from "views/notice/NoticeDetail";
import { Route, Switch } from "react-router-dom";
import { Link } from "react-router-dom";
import { faBold } from "@fortawesome/free-solid-svg-icons";

function NoticeItem(props) {
  // console.log(props.id)
  // console.log(props.noticeTitle)
  // console.log(props.bookTitle.length)
  let bookTitle;
  if (props.bookTitle.length > 11) {
    bookTitle = props.bookTitle.substr(0, 11) + "...";
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
      <Link
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
      >
        <Row>
          <p style={{ color: "white" }}>{props.id}</p>

          <Col >
            <p style={{ color: "white" }}>{bookTitle}</p>
          </Col>
          <Col  style={{margin:0, padding:0}}>
              <p align="right" style={{ color: "white", margin: 0, marginRight: 5}}>
                {donateTime.substr(0, 10)}
              </p>
              <p align="right" style={{ color: "white", margin: 0, padding:0}}>{props.bookLogLocker}</p>
          </Col>
        </Row>

        {/* <address>{props.noticeContent}</address> */}
      </Link>
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

export default NoticeItem;
