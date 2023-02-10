// import classes from "./MeetupList.module.css";
import React from "react";
import MyPageDonateItem from "views/account/MyPageDonateItem";
import MyPageReceiveItem from "views/account/MyPageReceiveItem";

import {
  Alert,
  UncontrolledAlert,
  CardBody,
  Row,
  CardHeader,
  CardTitle,
  Card,
  Button,
} from "reactstrap";

function NoticeList(props) {
  const [hiddenpassword, sethiddenpassword] = React.useState(true);
  const [hiddenid, sethiddenid] = React.useState(false);
  const showid = () => {
    sethiddenpassword(true);
    sethiddenid(false);
    // setClicked(true);;
  };

  const showpassword = () => {
    sethiddenid(true);
    sethiddenpassword(false);
  };
  // console.log(props)

  console.log(props);
  return (
    <div>
      <CardBody>
        <Row style={{ justifyContent: "center" }}>
          <div>
            <Button
              color="primary"
              className="animation-on-hover"
              type="submit"
              onClick={showid}
              // className={classes.style}
              style={{ backgroundColor: hiddenid ? "black" : "white" }}
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
              style={{ backgroundColor: hiddenpassword ? "black" : "white" }}
            >
              수령
            </Button>
          </div>
        </Row>
      </CardBody>
      <CardTitle hidden={hiddenid}>나의 기부내역</CardTitle>
      <CardTitle hidden={hiddenpassword}>나의 수령내역</CardTitle>

      {props.Donates.map((donate) => (
        <div>
          <Alert color="info" hidden={hiddenid}>
            <MyPageDonateItem
              //   key={donate.id}
              id={donate.id}
              bookTitle={donate.bookTitle}
              bookLogDonateDateTime={donate.bookLogDonateDateTime}
            />
          </Alert>
        </div>
      ))}
      {props.Receives.map((receive) => (
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
      ))}
    </div>
  );
}
export default NoticeList;
