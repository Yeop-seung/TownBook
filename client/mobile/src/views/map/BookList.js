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
  // const [hiddenpassword, sethiddenpassword] = React.useState(true);
  // const [hiddenid, sethiddenid] = React.useState(false);
  // const showid = () => {
  //   sethiddenpassword(true);
  //   sethiddenid(false);
  //   // setClicked(true);;
  // };

  // const showpassword = () => {
  //   sethiddenid(true);
  //   sethiddenpassword(false);
  // };
  // console.log(props)



  console.log("list전달받은값",props)
  return (
    <div>
      {/* <CardBody>
        <Row style={{ justifyContent: "center" }}>
          <div>
            <button
              // color="black"
              type="submit"
              onClick={showid}
              // className={classes.style}
              style={{ backgroundColor: hiddenid ? "black" : "white" }}
            >
              기부
            </button>
          </div>

          <div>
            <button
              // color="black"
              type="submit"
              onClick={showpassword}
              // className={classes.style}
              style={{ backgroundColor: hiddenpassword ? "black" : "white" }}
            >
              수령
            </button>
          </div>
        </Row>
      </CardBody>
      <CardTitle hidden={hiddenid}>나의 기부내역</CardTitle>
      <CardTitle hidden={hiddenpassword}>나의 수령내역</CardTitle> */}

      {props.bookList.map((book) => (
        <div>
          <Alert color="info" >
            
            <BookItem
              //   key={donate.id}
              id={book.id}
              bookTitle={book.bookTitle}
              bookLogDonateDateTime={book.bookLogDonateDateTime}
            />
          </Alert>

          
        </div>
      ))}
      {/* {props.Receives.map((receive) => (
        <div>

          <Alert color="info" hidden={hiddenpassword}>
            
            <MyPageReceiveItem
              //   key={receive.id}
              id={receive.id}
              bookTitle={receive.bookTitle}
              bookLogDonateDateTime={receive.bookLogDonateDateTime}
            />
          </Alert>
        </div>
      ))} */}
    </div>
  );
}
export default BookList;
