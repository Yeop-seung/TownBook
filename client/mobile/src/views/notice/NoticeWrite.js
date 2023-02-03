import React from "react";
import { useRef } from "react";
import { useHistory } from "react-router-dom";

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
// import { isPropertySignature } from "typescript";

function NoticeWrite(props) {
  const emailInputRef = useRef();
  const pwInputRef = useRef();

  const history = useHistory();

  function submitHandler(event) {
    event.preventDefault();

    const enteredEmail = emailInputRef.current.value;
    const enteredPw = pwInputRef.current.value;

    const userInfo = {
      accountEmail: enteredEmail,
      accountPw: enteredPw,
    };
    console.log(userInfo);
    // props.onAddInfo(userInfo);

    fetch(
      "https://react-getting-started-9d228-default-rtdb.firebaseio.com/meetups.json",
      {
        method: "POST",
        body: JSON.stringify(userInfo),
        headers: {
          "Content-Type": "application/json",
        },
      }
      //replace는 뒤로가기 버튼 비활성 이미 양식 제출했으므로
    ).then(() => {
      //then 대신에 asynce나 await가능
      history.replace("/");
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
                  <Col className="pr-md-1" md="5">
                    <FormGroup>
                      <div>
                        <label htmlFor="exampleInputEmail1">제목</label>
                      </div>
                      <div>
                        <textarea
                          placeholder="제목을 입력해주세요."
                          type="email"
                          ref={emailInputRef}
                          style={{
                            width: 400,
                            height: 50,
                            padding: 10,
                            border : "1px solid rgba(193,181,169,1)",
                            borderRadius: 5,
                            fontSize: 16,
                          }}
                        />
                      </div>
                    </FormGroup>
                  </Col>

                  <Col className="pr-md-1" md="5">
                    <FormGroup>
                      <label>내용</label>
                      <div>
                        <textarea
                          //   defaultValue="Mike"
                          placeholder="글을 입력해주세요"
                          type="text"
                          ref={pwInputRef}
                          style={{
                            width: 400,
                            height: 200,
                            padding: 10,
                            border : "1px solid rgba(193,181,169,1)",
                            borderRadius: 5,
                            fontSize: 16,
                        }}
                        />
                      </div>
                    </FormGroup>
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

export default NoticeWrite;
