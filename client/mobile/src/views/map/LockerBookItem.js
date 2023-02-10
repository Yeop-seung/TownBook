import classes from "./MeetupItem.module.css";
import { Card } from "reactstrap";
import NoticeDetail from "views/notice/NoticeDetail";
import { Route, Switch } from "react-router-dom";
import { Link } from "react-router-dom";
import { faBold } from "@fortawesome/free-solid-svg-icons";

function LockerBookItem(book) {
  // console.log(props.id)
  // console.log(props.noticeTitle)

  // console.log(props.noticeContent)
  console.log("라커item전달받은값", book);
  return (
    <div className={classes.item}>
      {/* // 컴포넌트로 감싸서 jsx 콘텐츠를 감싼다(children 개념) */}
      {/* <Card> */}
      {/* <div className={classes.image}>
          <img src={book.image} alt={book.contentTitle} />
        </div> */}
      <Link
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
        {/* <p style={{ color: "white" }}>{book.bookTitleURL}</p> */}

        {/* <address>{book.noticeContent}</address> */}
      </Link>
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
