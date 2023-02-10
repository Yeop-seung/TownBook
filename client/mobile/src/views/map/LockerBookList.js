// import classes from "./MeetupList.module.css";
import React from "react";
import LockerBookItem from "views/map/LockerBookItem";
// import MyPageReceiveItem from "views/account/MyPageReceiveItem";
import {
  Alert,
  UncontrolledAlert,
  CardBody,
  Row,
  CardHeader,
  CardTitle,
  Card,
} from "reactstrap";
// import LockerBookItem from "./LockerBookItem";


function LockerBookList(props) {


  console.log("lockerlist전달받은값",props)
  return (
    <div>
      {props.lockerList.map((book) => (
          <Alert color="info" >
            
            <LockerBookItem
              //   key={donate.id}
              id={book.id}
              bookTitle={book.bookTitle}
              bookTitleURL={book.bookTitleURL}
            />
          </Alert>
      ))}
      </div>
  );
}
export default LockerBookList;
