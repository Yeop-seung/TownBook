// import classes from "./MeetupList.module.css";
import React from "react";
import MyPageDonateItem from "views/account/MyPageDonateItem";
import MyPageReceiveItem from "views/account/MyPageReceiveItem";
import WishListItem from "./WishListItem";
import {
  Alert,
  UncontrolledAlert,
  CardBody,
  Row,
  CardHeader,
  CardTitle,
  Card,
  Button,
  Col,
} from "reactstrap";

function WishList(props) {
  console.log("위시리스트프롭", props);

  // console.log(props)

  console.log(props);
  return (
    <div>
      {props.wishList.map((wish) => (
        <div>
          <Alert style={{backgroundColor:"white", color:"black", borderColor:"ebecf1"}}>
            <WishListItem
              bookTitleURL = {wish.bookTitleURL}
              bookTitle={wish.bookTitle}
              bookLogNo={wish.bookLogNo}
              bookLogRegion={wish.lockerRegion}
              id={wish.id}
            />
          </Alert>
        </div>
      ))}
      {/* {props.Receives.map((receive) => (
        <div>
          <Alert  >
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
export default WishList;
