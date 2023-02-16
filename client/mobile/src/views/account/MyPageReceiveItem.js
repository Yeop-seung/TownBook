import classes from "./MeetupItem.module.css";
import { Card, Row, Col } from "reactstrap";
import NoticeDetail from "views/notice/NoticeDetail";
import { Route, Switch } from "react-router-dom";
import { Link } from "react-router-dom";
import { faBold } from "@fortawesome/free-solid-svg-icons";

function NoticeItem(props) {
    // console.log(props.id)
    // console.log(props.noticeTitle)
    let bookTitle;
    if (props.bookTitle.length > 11) {
      bookTitle = props.bookTitle.substr(0, 9) + "...";
    } else {
      bookTitle = props.bookTitle;
    }
    // console.log(props.noticeContent)

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
              noticeContent: props.bookLogReceiveDateTime,
            },
          }}
        > */}
          
            {/* <p style={{color:"white"}}>{props.id}</p>
            <p style={{color:"white"}}>{props.bookTitle}</p>
            <p style={{color:"white"}}>{props.bookLogDonateDateTime}</p> */}

            {/* <address>{props.noticeContent}</address> */}
          
        {/* </Link> */}
        <Row>
          <p style={{ color: "white" }}>{props.id}</p>

          <Col >
            <p style={{ color: "white" }}>{bookTitle}</p>
          </Col>
          <Col  style={{margin:0, padding:0}}>
              <p align="right" style={{ color: "white", margin: 0, marginRight: 5}}>
                {props.bookLogDonateDateTime.substr(0, 10)}
              </p>
              {/* <p align="right" style={{ color: "white", margin: 0, padding:0}}>{props.bookLogLocker}</p> */}
          </Col>
        </Row>
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
