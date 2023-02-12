import classes from "./MeetupItem.module.css";
import { Card } from "reactstrap";
import BookDetail from "views/map/BookDetail";
import { Route, Switch } from "react-router-dom";
import { Link } from "react-router-dom";
import { faBold } from "@fortawesome/free-solid-svg-icons";

function BookItem(props) {
    // console.log(props.id)
    // console.log(props.noticeTitle)

    // console.log(props.noticeContent)
    // console.log("item전달받은값",props)
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
              bookTitleURL: props.bookTitleURL,
              MapToken: true,
            },
          }}
        >
          
          {/* <p style={{ color: "white" }}>{props.id}</p> */}
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

export default BookItem;
