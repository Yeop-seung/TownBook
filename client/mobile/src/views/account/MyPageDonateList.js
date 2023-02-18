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

  console.log('리스트입니다.',props);
  return (
    <div>
      <CardBody>
        <Row style={{ justifyContent: "center" }}>
          <div>
            <button
              // color="primary"
              className="animation-on-hover"
              type="submit"
              onClick={showid}
              // className={classes.style}
              style={{  
                backgroundColor: hiddenid ?  "#FFFFFF" : "#2D9CEE",
                border: "none",
                color: hiddenid ? "black" : "white" ,
                padding: "10px 24px",
                textAlign: "center",
                textDecoration: "none",
                display: "inline-block",
                fontSize: 16,
                margin: "4px 2px",
                cursor: "pointer",
                borderRadius: 10,
                fontWeight:"bold",
                boxShadow: "2px 2px 4px rgba(0, 0, 0, 0.2)",
              }}
            >
              기부
            </button>
          </div>

          <div>
            <button
              // color="info"
              className="animation-on-hover"
              // color="black"
              type="submit"
              onClick={showpassword}
              // className={classes.style}
              // style={{ backgroundColor: hiddenpassword ? "#333333" : "#111111" }}
              style={{  
                backgroundColor: hiddenpassword ?  "#FFFFFF" : "#2D9CEE",
                border: "none",
                color: hiddenpassword ?  "black" : "white"  ,
                padding: "10px 24px",
                textAlign: "center",
                textDecoration: "none",
                display: "inline-block",
                fontSize: 16,
                margin: "4px 2px",
                cursor: "pointer",
                borderRadius: 10,
                fontWeight:"bold",
                boxShadow: "2px 2px 4px rgba(0, 0, 0, 0.2)",
              }}
            >
              수령
            </button>
          </div>
        </Row>
      </CardBody>
      {/* <CardTitle hidden={hiddenid}>나의 기부내역</CardTitle>
      <CardTitle hidden={hiddenpassword}>나의 수령내역</CardTitle> */}

      {props.Donates.map((donate) => (
        <div style={{borderColor:"ebecf1"}}>
          <Alert  hidden={hiddenid} style={{backgroundColor:"white", color:"black", borderColor:"ebecf1"}}>
            <MyPageDonateItem
              //   key={donate.id}
              id={donate.id}
              bookTitle={donate.bookTitle}
              bookLogDonateDateTime={donate.bookLogDonateDateTime}
              bookLogLocker={donate.bookLogLocker}
            />
          </Alert>
        </div>
      ))}
      {props.Receives.map((receive) => (
        <div>
          <Alert  hidden={hiddenpassword} style={{backgroundColor:"white", color:"black", borderColor:"ebecf1"}}>
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
