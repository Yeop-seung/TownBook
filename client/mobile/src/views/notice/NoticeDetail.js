import classes from "./MeetupItem.module.css";
import { Card, CardHeader, CardTitle, CardBody } from "reactstrap";
import { Link, useHistory } from "react-router-dom";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowLeft } from "@fortawesome/free-solid-svg-icons";
import React, { useEffect } from "react";

function NoticeDetail(props) {
  console.log('공지',props);
  const [adminId, setadminId] = React.useState(true);
  const ID = props.location.state.id;
  const history = useHistory();
  console.log(ID);
  const userInfo = {
    noticeNo: ID,

    // noticeId: Id,
  };
  useEffect(() => {
  if (localStorage.getItem("accountNo") === '3') {
    setadminId(false);
  } else {
    setadminId(true);
  }
},[]);
  function deleteHandler(params) {
    axios
      .put("https://도메인/backend/notice/remove", userInfo)
      // console.log("성공")
      //replace는 뒤로가기 버튼 비활성 이미 양식 제출했으므로
      .then((response) => {
        //then 대신에 asynce나 await가능
        alert("글삭제 성공.");
        history.replace("/notice");
      })
      .catch((error) => {
        alert("작성에 실패하였습니다.");
      });
  }
  //   function updateHandler(params) {
  //     axios
  //       .put(
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
          <Link to={"/notice"}>
            <FontAwesomeIcon icon={faArrowLeft} size="xl" color="#C1B5A9" />
          </Link>
        </CardHeader>
        {/* <div className={classes.image}>
          <img src={props.image} alt={props.contentTitle} />
        </div> */}

        <div className={classes.content}>
          <CardTitle><h3>{props.location.state.noticeTitle}</h3></CardTitle>
          <CardBody><address>{props.location.state.noticeContent}</address></CardBody>
          
          {/* <p>{props.description}</p> */}
        </div>
        <button
          onClick={deleteHandler}
          style={{
            backgroundColor: "#2D9CEE",
            border: "none",
            color: "white",
            padding: "10px 24px",
            textAlign: "center",
            textDecoration: "none",
            display: "inline-block",
            fontSize: 16,
            margin: "4px 2px",
            cursor: "pointer",
            borderRadius: 10,
            fontWeight: "bold",
            boxShadow: "2px 2px 4px rgba(0, 0, 0, 0.2)",
          }}
          hidden={adminId}
        >
          삭제
        </button>
        <Link
          to={{
            pathname: `/notice/modify/${props.location.state.id}`,
            state: {
              id: props.location.state.id,
              noticeTitle: props.location.state.noticeTitle,
              noticeContent: props.location.state.noticeContent,
            },
          }}
          style={{
            backgroundColor: "#2D9CEE",
            border: "none",
            color: "white",
            padding: "10px 24px",
            textAlign: "center",
            textDecoration: "none",
            display: "inline-block",
            fontSize: 16,
            margin: "4px 2px",
            cursor: "pointer",
            borderRadius: 10,
            fontWeight: "bold",
            boxShadow: "2px 2px 4px rgba(0, 0, 0, 0.2)",
          }}
          hidden={adminId}
        >
          수정
        </Link>

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

export default NoticeDetail;
