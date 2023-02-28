// import classes from "./MeetupList.module.css";
import React from "react";
import BookItem from "views/map/BookItem";
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


function BookList(props) {



  console.log("list전달받은값",props)
  return (
    <div>

      {props.bookList.map((book) => (
        <div>
          
            <BookItem
              // key={book.id}
              // id={book.id}
              bookTitle={book.book.bookTitle}
              bookTitleURL={book.book.bookTitleURL}
              bookLogNo={book.bookLog.bookLogNo}
              bookAuthor={book.book.bookAuthor}
              id={book.id}
              bookPublisher = {book.book.bookPublisher}
              bookIntroductionURL = {book.book.bookIntroductionURL}
              bookPublishPredate = {book.book.bookPublishPredate}
              lockerNo = {book.bookLog.lockerNo}
              map = {book.map}
            />
          

          
        </div>
      ))}

    </div>
  );
}
export default BookList;
