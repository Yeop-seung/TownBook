import classes from "./MeetupItem.module.css";
import { Card, CardHeader } from "reactstrap";
import { Link, useHistory } from "react-router-dom";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowLeft, faBookmark } from "@fortawesome/free-solid-svg-icons";

function BookDetail(props) {
  console.log(props);
  const book = props.location.state;
  const history = useHistory();
  //   console.log(ID);

  //     function deleteHandler(params) {
  //     axios
  //       .delete(
  //         `https://react-getting-started-9d228-default-rtdb.firebaseio.com/notices/${ID}.json`
  //       )
  //       // console.log("성공")
  //         //replace는 뒤로가기 버튼 비활성 이미 양식 제출했으므로
  //       .then((response) => {
  //         //then 대신에 asynce나 await가능
  //         alert("글삭제 성공.")
  //         history.replace("/notice");
  //       })
  //         .catch((error) => {
  //           alert("작성에 실패하였습니다.");
  //       });
  //   }

  return (
    <div className="content">
      {/* // 컴포넌트로 감싸서 jsx 콘텐츠를 감싼다(children 개념) */}
      <Card>
        <CardHeader>
          <Link to={"/map"}>
            <FontAwesomeIcon icon={faArrowLeft} size="xl" color="#C1B5A9" hidden={book.MyPageToken}/>
          </Link>
          <Link to={"/myPage"}>
            <FontAwesomeIcon icon={faArrowLeft} size="xl" color="#C1B5A9" hidden={book.MapToken}/>
          </Link>
        </CardHeader>

        <div className={classes.content}>
          <div style={{ display: "flex" }}>
            <div>책상세</div>
            <div></div>
            </div>
          <hr />
          <div style={{ display: "flex" }}>
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
          </div>
          <ul>
            <li> {book.bookTitle}</li>
            <li> {book.bookAuthor}</li>
            <li> {book.bookPublisher}</li>
            <li> {book.bookPublishPredate}</li>
            <li> {book.bookIntroductionURL}</li>
          </ul>
          {/* <p>{props.description}</p> */}
        </div>
        {/* <Link  to={{
            pathname: `/notice/modify/${book.id}`,
            state: {
              id: book.id,
              noticeTitle: book.noticeTitle,
              noticeContent: book.noticeContent,
            },
          }}>수정</Link> */}

        {/* <div className={classes.actions}>
          <button>To Favorites</button>
        </div> */}
        {/* <div>
            {props.noticeTitle}
            {props.noticeContent}
        </div> */}
      </Card>
    </div>
  );
}

export default BookDetail;
