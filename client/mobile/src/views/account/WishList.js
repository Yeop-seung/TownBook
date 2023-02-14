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
  Col
} from "reactstrap";

function WishList(props) {
    console.log('위시리스트프롭',props)
   
  // console.log(props)

  console.log(props);
  return (
    <div>
      {/* <CardBody>
        <Row style={{ justifyContent: "center" }}>
          <div>
            <Button
              color="primary"
              className="animation-on-hover"
              type="submit"
              onClick={showid}
              // className={classes.style}
              style={{ backgroundColor: hiddenid ? "info" : "primary" }}
            >
              기부
            </Button>
          </div>

          <div>
            <Button
              color="info"
              className="animation-on-hover"
              // color="black"
              type="submit"
              onClick={showpassword}
              // className={classes.style}
              style={{ backgroundColor: hiddenpassword ? "info" : "primary" }}
            >
              수령
            </Button>
          </div>
        </Row>
      </CardBody> */}
      {/* <CardTitle hidden={hiddenid}>나의 기부내역</CardTitle>
      <CardTitle hidden={hiddenpassword}>나의 수령내역</CardTitle> */}

      {props.wishList.map((wish) => (
        <div>
          <Alert >
          <WishListItem bookTitle={wish.bookTitle} bookLogNo={wish.bookLogNo} id={wish.id}/>
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
