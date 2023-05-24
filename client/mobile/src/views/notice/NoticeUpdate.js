import React, { useState } from "react";
import { useRef } from "react";
import { useHistory } from "react-router-dom";
import  classes  from "./NoticeWrite.module.css";
// reactstrap components
import {
  Button,
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  CardText,
  FormGroup,
  Form,
  Input,
  Row,
  Col,
} from "reactstrap";
import axios from "axios";
// import { isPropertySignature } from "typescript";

function NoticeUpdate(props) {
  const titleInputRef = useRef();
  const contentInputRef = useRef();
  // const [Id, setId] = useState(0)
  // const handleSubmit = (event) => {
  //   event.preventDefault();
    
  //   setId(Id + 1);
  // };
console.log(props)
  const history = useHistory();

  function submitHandler(event) {
    // if (event) {
    //   setId(Id + 1);

    // }
    event.preventDefault();
    const enteredTitle = titleInputRef.current.value;
    const enteredContent = contentInputRef.current.value;

    const userInfo = {
      noticeTitle: enteredTitle,
      noticeContent: enteredContent,
      noticeNo:props.id,
      noticeCategory: 0,
      // noticeId: Id,
    };
    console.log(userInfo);
    // props.onAddInfo(userInfo);

    axios
      .put(
        "https://도메인/backend/notice/modify",userInfo
      )
      // console.log("성공")
        //replace는 뒤로가기 버튼 비활성 이미 양식 제출했으므로
      .then((response) => {
        //then 대신에 asynce나 await가능
        alert("글작성 성공.")
        history.replace("/notice");
      })
        .catch((error) => {
          alert("작성에 실패하였습니다.");
      });
  }
  return (
    <>
      <div className="content">
        <Row>
          <Col md="8">
            <Card>
              <CardHeader>
                <h5 className="title">글쓰기</h5>
              </CardHeader>
              <CardBody>
                <Form>
                  <Col  md="10">
                      <div>
                        <label htmlFor="exampleInputEmail1">제목</label>
                      </div>
                      <div>
                        <textarea
                            defaultValue={props.location.state.noticeTitle}
                          type="email"
                          ref={titleInputRef}
                          className={classes.titlestyle}
                          required id='title'
                        />
                      </div>
                  </Col>

                  <Col  md="10">
                      <label>내용</label>
                      <div>
                        <textarea
                            defaultValue={props.location.state.noticeContent}
                            placeholder="글을 입력해주세요"
                          type="text"
                          ref={contentInputRef}
                          className={classes.contentstyle}
                          required id='content'
                        />
                      </div>
                  </Col>

                  {/* <Row>
                    <Col md="8">
                      <FormGroup>
                        <label>About Me</label>
                        <Input
                          cols="80"
                          defaultValue="Lamborghini Mercy, Your chick she so thirsty, I'm in
                            that two seat Lambo."
                          placeholder="Here can be your description"
                          rows="4"
                          type="textarea"
                        />
                      </FormGroup>
                    </Col>
                  </Row> */}
                </Form>
              </CardBody>
              <CardFooter >
                <button
                  className="btn-login"
                  // color="black"
                  type="submit"
                  
                  onClick={submitHandler}
                  style={{
                    marginLeft : 15,
                  }}
                >
                  작성
                </button>
              </CardFooter>
            </Card>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default NoticeUpdate;
