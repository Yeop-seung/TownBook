import axios from "axios";

import { React, useState, useEffect, useRef } from "react";
// react plugin for creating notifications over the dashboard
import NotificationAlert from "react-notification-alert";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link } from "react-router-dom";
import NoticeList from "views/notice/NoticeList";
import { faArrowLeft } from "@fortawesome/free-solid-svg-icons";
import {
  faHeadset,
  faQrcode,
  faSearch,
  faComments,
  faBullhorn,
  faKey,
  faPlus,
} from "@fortawesome/free-solid-svg-icons";
// reactstrap components
import {
  Alert,
  UncontrolledAlert,
  Button,
  Card,
  CardHeader,
  CardBody,
  CardTitle,
  Row,
  Col,
} from "reactstrap";

function Notice(props) {
  const [adminId, setadminId] = useState(true);
  const [isLoading, setIsLoading] = useState(true);
  const [loadedMeetups, setLoadedMeetups] = useState([]);
  const notificationAlertRef = useRef(null);
  const notify = (place) => {
    var color = Math.floor(Math.random() * 5 + 1);
    var type;
    switch (color) {
      case 1:
        type = "primary";
        break;
      case 2:
        type = "success";
        break;
      case 3:
        type = "danger";
        break;
      case 4:
        type = "warning";
        break;
      case 5:
        type = "info";
        break;
      default:
        break;
    }
    var options = {};
    options = {
      place: place,
      message: (
        <div>
          <div>
            Welcome to <b>Black Dashboard React</b> - a beautiful freebie for
            every web developer.
          </div>
        </div>
      ),
      type: type,
      icon: "tim-icons icon-bell-55",
      autoDismiss: 7,
    };
    // notificationAlertRef.current.notificationAlert(options);
  };

  useEffect(() => {
    console.log(localStorage.getItem("accountNo"));
    if (localStorage.getItem("accountNo") === '3') {
      setadminId(false);
    } else {
      setadminId(true);
    }
    axios
      .get(`https://도메인/backend/notice`)
      // .get("https:///townbook/myPage/receive/${receiverNo}")
      .then((res) => {
        const notices = [];
        console.log("공지액시", res);
        // for (let i = 0; i < res.data.length; i++) {
        //   notices.push({ ...res.data[i], id: i + 1 });
        // }
        for (const key in res.data.data) {
          const notice = {
            id: key,
            ...res.data.data[key],
          };
          notices.push(notice);
        }
        // if(res=="true"){
        // alert("회원가입에 성공하였습니다.");
        // history.replace("/");
        // }
        // else{

        //   alert("회원가입에 실패하였습니다.");
        // }
        setIsLoading(false);
        setLoadedMeetups(notices);
        console.log("공지액시넣으넛", notices);
      })
      .catch((error) => {
        alert("글로딩에 실패하였습니다.");
      });
  }, [isLoading]);

  if (isLoading) {
    <section>
      <p>Loading...</p>
    </section>;
  }

  return (
    <>
      <Link to={"/map"}>
        <FontAwesomeIcon
          icon={faArrowLeft}
          size="xl"
          color="black"
          position="absolute"
          zIndex="2000"
          style={{ margin: 15, marginBottom: 5 }}
        />
      </Link>
      <div className="content">
        <div className="react-notification-alert-container">
          <NotificationAlert ref={notificationAlertRef} />
        </div>
        <Row>
          <Col md="6">
            <Card>
              <CardHeader>
                <Row
                  style={{ justifyContent: "space-between", paddingInline: 15 }}
                >
                  <CardTitle tag="h4">공지사항</CardTitle>

                  <Link to={"/notice/write"}>
                    <FontAwesomeIcon
                      icon={faPlus}
                      size="xl"
                      color="black"
                      hidden={adminId}
                    />
                  </Link>
                </Row>
              </CardHeader>
              <CardBody>
                {/* <Alert color="info">
                  <span>This is a plain notification</span>
                </Alert>

                <UncontrolledAlert color="info">
                  <span>This is a notification with close button.</span>
                </UncontrolledAlert> */}

                <NoticeList notices={loadedMeetups} />

                {/* 
                <UncontrolledAlert className="alert-with-icon" color="info">
                  <span className="tim-icons icon-bell-55" data-notify="icon" />
                  <span data-notify="message">
                    This is a notification with close button and icon.
                  </span>
                </UncontrolledAlert>

                <UncontrolledAlert className="alert-with-icon" color="info">
                  <span className="tim-icons icon-bell-55" data-notify="icon" />
                  <span data-notify="message">
                    This is a notification with close button and icon and have
                    many lines. You can see that the icon and the close button
                    are always vertically aligned. This is a beautiful
                    notification. So you don't have to worry about the style.
                  </span>
                </UncontrolledAlert> */}

                {/* <NoticeList/> */}
              </CardBody>
            </Card>
          </Col>
          {/* <Col md="6">
            <Card>
              <CardHeader>
                <CardTitle tag="h4">이용방법</CardTitle>
              </CardHeader>
              <CardBody>
                <UncontrolledAlert color="primary">
                  <span>
                    <b>Primary - </b>
                    This is a regular notification made with ".alert-primary"
                  </span>
                </UncontrolledAlert>
                <UncontrolledAlert color="info">
                  <span>
                    <b>Info - </b>
                    This is a regular notification made with ".alert-info"
                  </span>
                </UncontrolledAlert>
                <UncontrolledAlert color="success">
                  <span>
                    <b>Success - </b>
                    This is a regular notification made with ".alert-success"
                  </span>
                </UncontrolledAlert>
                <UncontrolledAlert color="warning">
                  <span>
                    <b>Warning - </b>
                    This is a regular notification made with ".alert-warning"
                  </span>
                </UncontrolledAlert>
                <UncontrolledAlert color="danger">
                  <span>
                    <b>Danger - </b>
                    This is a regular notification made with ".alert-danger"
                  </span>
                </UncontrolledAlert>
              </CardBody>
            </Card>
          </Col>
          <Col md="12">
            <Card>
              <CardBody>
                <div className="places-buttons">
                  <Row>
                    <Col className="ml-auto mr-auto text-center" md="6">
                      <CardTitle tag="h4">
                        Notifications Places
                        <p className="category">Click to view notifications</p>
                      </CardTitle>
                    </Col>
                  </Row>
                  <Row>
                    <Col className="ml-auto mr-auto" lg="8">
                      <Row>
                        <Col md="4">
                          <Button
                            block
                            color="primary"
                            onClick={() => notify("tl")}
                          >
                            Top Left
                          </Button>
                        </Col>
                        <Col md="4">
                          <Button
                            block
                            color="primary"
                            onClick={() => notify("tc")}
                          >
                            Top Center
                          </Button>
                        </Col>
                        <Col md="4">
                          <Button
                            block
                            color="primary"
                            onClick={() => notify("tr")}
                          >
                            Top Right
                          </Button>
                        </Col>
                      </Row>
                    </Col>
                  </Row>
                  <Row>
                    <Col className="ml-auto mr-auto" lg="8">
                      <Row>
                        <Col md="4">
                          <Button
                            block
                            color="primary"
                            onClick={() => notify("bl")}
                          >
                            Bottom Left
                          </Button>
                        </Col>
                        <Col md="4">
                          <Button
                            block
                            color="primary"
                            onClick={() => notify("bc")}
                          >
                            Bottom Center
                          </Button>
                        </Col>
                        <Col md="4">
                          <Button
                            block
                            color="primary"
                            onClick={() => notify("br")}
                          >
                            Bottom Right
                          </Button>
                        </Col>
                      </Row>
                    </Col>
                  </Row>
                </div>
              </CardBody>
            </Card>
          </Col> */}
        </Row>
      </div>
    </>
  );
}

export default Notice;
