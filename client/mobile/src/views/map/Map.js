import React, { useEffect, useState, useRef } from "react";
import "./Map.css";
import markerimg from "assets/img/library2.png";
import classes from "./AdminNavbar.module.css";

import {
  Card,
  CardBody,
  CardHeader,
  CardTitle,
  UncontrolledAlert,
  Modal,
  Input,
  Button,
} from "reactstrap";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faXmark } from "@fortawesome/free-solid-svg-icons";
import BookList from "views/map/BookList";
import { Link, Switch, Route } from "react-router-dom";
import Notice from "views/notice/Notice";
import LockerBookList from "views/map/LockerBookList";
import { func } from "prop-types";
const { kakao } = window;
// function submitHandler(event) {
//   event.preventDefault();

//   // console.log(context);
//   axios
//     .get(
//       "/naver/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=%EC%BD%94%EC%8A%A4%ED%94%BC"
//     )
//     .then((data) => {
//       console.log(data);
//     });
// }

export function Map() {
  const [map, setmap] = useState(null);
  // const [state, setState] = useState({
  //   // 지도의 초기 위치
  //   center: { lat: 33.452613, lng: 126.570888 },
  //   // 지도 위치 변경시 panto를 이용할지에 대해서 정의
  //   isPanto: false,
  // });
  // const [map, setMap] = useState(null);
  const [lockerList, setlockerList] = useState([]);
  const [bookList, setbookList] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [Lockers, setLockers] = useState([]);
  const [modalSearch, setmodalSearch] = useState(false);
  const [modalSearch2, setmodalSearch2] = useState(false);
  let lat;
  let lon;
  // var map = null;
  let go;
  const [count, setCount] = useState(0);
  const toggleModalSearch = () => {
    setmodalSearch(!modalSearch);
  };
  const toggleModalSearch2 = () => {
    setmodalSearch2(!modalSearch2);
  };
  const searchbookRef = useRef();
  // const height = window.innerWidth < 993 ? '93vh' : '70vh';
  // const [styles, setstyles] = useState({width:"100%",height:"70vh"});
  const [test, settest] = useState();

  // function panTo() {
  //   // 이동할 위도 경도 위치를 생성합니다
  //   var moveLatLon = new kakao.maps.LatLng(33.45058, 126.574942);

  //   // 지도 중심을 부드럽게 이동시킵니다
  //   // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
  //   map.panTo(moveLatLon);
  // }
  useEffect(() => {
    // const onLoad = (map) => setMap(map);

    // const onUnmount = () => setMap(null);

    axios
      .get(`https://i8b201.p.ssafy.io/backend/locker`)
      .then((res) => {
        // console.log("락커들", res);
        const lockers = [];
        for (let i = 0; i < res.data.count; i++) {
          lockers.push({ ...res.data.data[i], id: i + 1 });
        }
        setLockers(lockers);
        setIsLoading(false);
      })
      .catch((error) => {
        alert("error");
      });

    console.log("테스트해보자", Lockers);

    // if (window.innerWidth < 993) {
    //   setstyles({width:"100%",height:"93vh"})
    // } else {
    //   setstyles({width:"100%", height:"70vh"})
    // }
    const container = document.getElementById("map"); //찾으려는 id
    const options = {
      center: new kakao.maps.LatLng(37.49676871972202, 127.02474726969814),
      level: 3,
    };

    const map = new kakao.maps.Map(container, options);
    setmap(map);
    // console.log("찐맵", map);

    navigator.geolocation.getCurrentPosition(function (position) {
      lat = position.coords.latitude; // 위도
      lon = position.coords.longitude; // 경도
    });

    if (navigator.geolocation) {
      // GeoLocation을 이용해서 접속 위치를 얻어옵니다
      navigator.geolocation.getCurrentPosition(function (position) {
        const lat = position.coords.latitude, // 위도
          lon = position.coords.longitude; // 경도

        const locPosition = new kakao.maps.LatLng(lat, lon); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
        const message = `'<div style="padding:10px;">여기에 계신가요?!</div>'`; // 인포윈도우에 표시될 내용입니다
        // console.log("찐찐맵", map);
        map.panTo(locPosition);
        // go = map.panTo(locPosition);
        // function goto() {
        //   map.panTo(locPosition);

        // }
        // function
        // console.log("위치찐", locPosition);

        // console.log("위도", lat);
        const userLatitude = lat;
        const userLongitude = lon;
        axios
          .post("https://i8b201.p.ssafy.io/backend/locker/findNearLocker", {
            userLatitude,
            userLongitude,
          })
          .then((res) => {
            // console.log("줍녀락커", res);
          });

        // 마커와 인포윈도우를 표시합니다
        displayMarker(locPosition, message);
      });
    } else {
      // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

      const locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
        message = "geolocation을 사용할수 없어요..";

      displayMarker(locPosition, message);
    }

    function displayMarker(locPosition, message) {
      // console.log("찐락", Lockers);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      // const zoomControl = new kakao.maps.ZoomControl();

      // map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다

      // const markerPosition  = new kakao.maps.LatLng(37.49676871972202, 127.02474726969814);
      // for (let i = 0;Lockers.lenth; i++) {
      //   const DBmarker`${i}` = Lockers[i]
      // }

      // object[`DBmarker${i}`];

      // 마커를 생성합니다
      // const myposition = new kakao.maps.Marker({
      //   position: locPosition,
      //   image: markerImage,
      // });

      const imageSrc = "https://ifh.cc/g/HqQFF7.png", // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      const markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );

      const imageSrc2 = "https://ifh.cc/g/X5DS1w.png", // 마커이미지의 주소입니다
        imageSize2 = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
        imageOption2 = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      const markerImage2 = new kakao.maps.MarkerImage(
        imageSrc2,
        imageSize2,
        imageOption2
      );

      //내위치
      const myposition = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: locPosition, // 마커를 표시할 위치
        content: "내위치", // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage2, // 마커 이미지
      });

      //동네북 위치 배열
      var positions = [];

      for (const locker of Lockers) {
        positions.push({
          title: locker.lockerNo,
          latlng: new kakao.maps.LatLng(
            locker.lockerLatitude,
            locker.lockerLongitude
          ),
          region: locker.lockerRegion,
        });
      }

      for (var i = 0; i < positions.length; i++) {
        // const lockerno = position.title;
        // console.log("이건 진짜 포지션", Lockers);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: positions[i].latlng, // 마커를 표시할 위치
          title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });

        //마커들에 클릭 이벤트 추가
        kakao.maps.event.addListener(
          marker,
          "click",
          // makeOverListener(map, marker, infowindow)
          (function (title) {
            return function () {
              searchLocker(title);
              // console.log(title);
            };
          })(positions[i].title)
        );
        const iwContent = `<div style=width:"95%",height:"70vh">${positions[i].region} 동네북 <div/>`; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        // iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

        // 인포윈도우를 생성합니다
        const infowindow = new kakao.maps.InfoWindow({
          content: iwContent,
          // removable: iwRemoveable,
        });
        kakao.maps.event.addListener(
          marker,
          "mouseover",
          makeOverListener(map, marker, infowindow)
        );
        kakao.maps.event.addListener(
          marker,
          "mouseout",
          makeOutListener(infowindow)
        );
      }

      function makeOverListener(map, marker, infowindow) {
        return function () {
          infowindow.open(map, marker);
        };
      }

      // 인포윈도우를 닫는 클로저를 만드는 함수입니다
      function makeOutListener(infowindow) {
        return function () {
          infowindow.close();
        };
      }
      // myposition.setMap(map);
      // DBmarker.setMap(map);

      // 마커에 클릭이벤트를 등록합니다

      // for (const locker of positions) {
      // kakao.maps.event.addListener(DBmarker, "click", function () {
      //   // 마커 위에 인포윈도우를 표시합니다
      //   // infowindow.open(map, DBmarker);

      // });
      // }
    }
  }, [isLoading]);
  if (isLoading) {
    <section>
      <p>Loading...</p>
    </section>;
  }

  // const navigateToLocker = () => {
  //   if (navigator.geolocation) {
  //     navigator.geolocation.getCurrentPosition(function (position) {
  //       const lat = position.coords.latitude; // 위도
  //       const lng = position.coords.longitude; // 경도

  //       const locPosition = new kakao.maps.LatLng(lat, lng);
  //       map.panTo(locPosition);
  //     });
  //   } else {
  //     alert("Your browser doesn't support geolocation.");
  //   }
  // };

  const navigateToCurrentLocation = () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(function (position) {
        const lat = position.coords.latitude; // 위도
        const lng = position.coords.longitude; // 경도

        const locPosition = new kakao.maps.LatLng(lat, lng);
        map.panTo(locPosition);
      });
    } else {
      alert("Your browser doesn't support geolocation.");
    }
  };

  function searchBook(event) {
    event.preventDefault();

    const enteredBookTitle = searchbookRef.current.value;

    axios
      .get(
        `https://i8b201.p.ssafy.io/backend/search/searchTitle/${enteredBookTitle}`
      )
      .then((res) => {
        console.log("북 res", res);
        const books = [];
        for (let i = 0; i < res.data.count; i++) {
          books.push({ ...res.data.data[i], id: i + 1, map: map });
        }
        setbookList(books);
        console.log("book", books);
        console.log("bookset", bookList);
        // navigateToCurrentLocation();
        // map.panTo(LocPosition)
        toggleModalSearch2();
      })

      .catch((error) => {
        alert("검색어를 입력해주세요.");
      });
  }

  function searchLocker(lockerNo) {
    Promise.all([
      axios.get(`https://i8b201.p.ssafy.io/backend/book/locker/${lockerNo}`),
      axios.get(`https://i8b201.p.ssafy.io/backend/locker/${lockerNo}`),
    ])

      .then(([res, res2]) => {
        console.log("라커 res", res);
        console.log("라커 res2", res2);
        const books = [];
        for (let i = 0; i < res.data.count; i++) {
          books.push({ ...res.data.data[i], id: i + 1, lockerNo });
        }
        setlockerList(books);
        console.log("lockerbooks", books);
        // console.log('lockerlist',lockerList)
        // setIsLoading(false);
        // const seek = new kakao.maps.LatLng(res2.data.data.lockerLatitude, res2.data.data.lockerLongitude)
        // map.panTo(seek)
        // navigateToCurrentLocation();
        toggleModalSearch();
      })

      .catch((error) => {
        alert("error.");
      });

    if (isLoading) {
      <section>
        <p>Loading...</p>
      </section>;
    }
  }

  return (
    <>
      {/* <button
        onClick={navigateToCurrentLocation}
        style={{ position: "absolute", zIndex: 20000, bottom: 70, right: 20 }}
      >
        My Location
      </button> */}
      {/* {searchBook} */}
      <a onClick={navigateToCurrentLocation} style={{ width:50,height:50,position: "absolute", zIndex: 300, bottom: 70, right: 10 }}>
        <img
          alt="..."
          className="avatar"
          src={require("assets/img/location3.png")}
          style={{boxShadow: "2px 2px 4px rgba(0, 0, 0, 0.2)",}}
        />

        
      </a>
      <div
        className={classes.searchbar2}
        // style={{
        //   position: "absolute",
        //   zIndex: 100,
        //   width: "100%",
        //   top: "20vh",
        //   paddingInline: 10,
        //   paddingTop: 15,
        // }}
      >
        <form>
          <div
            style={{
              display: "flex",
              marginBottom: "10px",
              justifyContent: "space-between",
              alignItems: "center",
            }}
          >
            <Input
              type="text"
              //   maxLength="20"
              className={classes.searchbar1}
              name="search"
              placeholder="검색할 도서를 입력해주세요."
              innerRef={searchbookRef}
              style={{
                border: "none",
                boxShadow: "rgba(0, 0, 0, 0.2) 3px 3px 3px",
              }}
            />
            <Button
              className="btn-simple"
              color="info"
              type="submit"
              onClick={searchBook}
              style={{
                width: "25%",
                backgroundColor: "#427bf1",
                color: "#ffffff",
                borderColor: "transparent",
                boxShadow: "rgba(0, 0, 0, 0.2) 3px 3px 3px",
                margin: 0,
                height: "5vh",
                marginLeft: 13,
              }}
            >
              검색
            </Button>
          </div>
        </form>
        {/* <form
          className={classes.dlgnone}
          style={{ position: "fixed", top: "35vh", zIndex: -100 }}
        >
          <div
            style={{
              display: "flex",
              marginBottom: "10px",
              justifyContent: "space-between",
              alignItems: "center",
            }}
          >
            <Input
              type="text"
              //   maxLength="20"
              className="search_input"
              name="search"
              placeholder="검색할 도서를 입력해주세요."
              innerRef={searchbookRef}
              style={{
                width: "40vh",
                boxShadow: "rgba(0, 0, 0, 0.2) 3px 3px 3px",
                border: 0,
                height: "5vh",
              }}
            />
            <Button
              // className="btn-simple"
              color="info"
              type="submit"
              onClick={searchBook}
              style={{
                width: "25%",
                backgroundColor: "#427bf1",
                color: "#ffffff",
                borderColor: "transparent",
                boxShadow: "rgba(0, 0, 0, 0.2) 3px 3px 3px",
                margin: 0,
                height: "5vh",
                marginLeft: 13,
              }}
            >
              검색
            </Button>
          </div>
        </form> */}
        {/* <button onClick={go}>버튼</button> */}
      </div>
      <Card style={{ padding: 0, margin: 0 }}>
        <div
          id="map"
          style={{ width: "100%", height: "93vh" }}
          // center={state.center}
          // isPanto={state.isPanto}
        ></div>
      </Card>

      <Modal
        modalClassName="modal-search"
        isOpen={modalSearch2}
        toggle={toggleModalSearch2}
        style={{
          position: "absolute",
          bottom: 0,
          left: 0,
          right: 0,
          height: "20%",
          backgroundColor: "transparent",
        }}
      >
        <button
          aria-label="Close"
          className="close"
          onClick={() => {
            toggleModalSearch2();
            // handle.clickButton();
          }}
        >
          <FontAwesomeIcon
            icon={faXmark}
            size="xl"
            color="black"
            style={{ margin: 0 }}
          />
        </button>

        <BookList
          // to={{ pathname: `/map/${searchbookRef}` }}
          bookList={bookList}
        />
      </Modal>

      <Modal
        modalClassName="modal-search"
        isOpen={modalSearch}
        toggle={toggleModalSearch}
        style={{ dispaly:"none",position:"fixed", bottom:100, zIndex:30000 }}
      >
        {/* <Input placeholder="QR이미지" type="text" /> */}
        <Card>
          <CardBody>
            <LockerBookList lockerList={lockerList} />
          </CardBody>
        </Card>
        <button
          aria-label="Close"
          className="close"
          onClick={() => {
            toggleModalSearch();
            // handle.clickButton();
          }}
        >
          <FontAwesomeIcon
            icon={faXmark}
            size="xl"
            color="black"
            style={{ margin: 0 }}
          />
        </button>
      </Modal>
    </>
  );
}

export default Map;
