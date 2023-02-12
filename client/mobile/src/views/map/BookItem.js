import classes from "./MeetupItem.module.css";
import { Card, CardBody, Modal, Alert } from "reactstrap";
import BookDetail from "views/map/BookDetail";
import { Route, Switch } from "react-router-dom";
import { Link } from "react-router-dom";
import { faBold } from "@fortawesome/free-solid-svg-icons";
import React from "react";
function BookItem(props) {
  // console.log(props.id)
  // console.log(props.noticeTitle)

  // console.log(props.noticeContent)
  // console.log("item전달받은값",props)
  const [modalSearch, setmodalSearch] = React.useState(false);
  const toggleModalSearch = () => {
    setmodalSearch(!modalSearch);
  };
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
              bookTitleURL: props.bookTitleURL,
              MapToken: true,
            },
          }}
        > */}
      <Link onClick={toggleModalSearch}>
        {/* <p style={{ color: "white" }}>{props.id}</p> */}
        <Card style={{margin:0}}>
          <Alert color="info" >
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
            </Alert>
        </Card>
      </Link>
      {/* <address>{props.noticeContent}</address> */}

      {/* </Link> */}
      <Modal
        modalClassName="modal-search"
        isOpen={modalSearch}
        toggle={toggleModalSearch}
        style={{ height: "100%" }}
      >
        <Card style={{ height: "90vh" }}>
          <div style={{ display: "flex" }}>
            <div>
              <button
                aria-label="Close"
                className="close"
                onClick={() => {
                  toggleModalSearch();
                  // handle.clickButton();
                }}
              >
                <i className="tim-icons icon-simple-remove" />
              </button>
            </div>
          </div>
          <hr />
          <div style={{ display: "flex" }}>
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
          </div>
          <ul>
            <li> {props.bookTitle}</li>
            <li> {props.bookAuthor}</li>
            <li> {props.bookPublisher}</li>
            <li> {props.bookPublishPredate}</li>
            <li> {props.bookIntroductionURL}</li>
          </ul>
        </Card>
      </Modal>
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
