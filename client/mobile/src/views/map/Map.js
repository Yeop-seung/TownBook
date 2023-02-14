import React, { useEffect, useRef } from "react";
import "./Map.css";
import markerimg from "assets/img/library2.png";
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
import BookList from "views/map/BookList";
import { Link, Switch, Route } from "react-router-dom";
import Notice from "views/notice/Notice";
import LockerBookList from "views/map/LockerBookList";
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

function Map() {
  const [lockerList, setlockerList] = React.useState([]);
  const [bookList, setbookList] = React.useState([]);
  const [isLoading, setIsLoading] = React.useState(true);
  const [Lockers, setLockers] = React.useState([]);
  const [modalSearch, setmodalSearch] = React.useState(false);
  const [modalSearch2, setmodalSearch2] = React.useState(false);

  const [count, setCount] = React.useState(0);
  const toggleModalSearch = () => {
    setmodalSearch(!modalSearch);
  };
  const toggleModalSearch2 = () => {
    setmodalSearch2(!modalSearch2);
  };
  const searchbookRef = useRef();
  // const height = window.innerWidth < 993 ? '93vh' : '70vh';
  // const [styles, setstyles] = React.useState({width:"100%",height:"70vh"});
  const [test, settest] = React.useState();
  useEffect(() => {
    axios
      .get(`https://i8b201.p.ssafy.io/backend/locker`)
      .then((res) => {
        console.log("락커들", res);
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

    if (navigator.geolocation) {
      // GeoLocation을 이용해서 접속 위치를 얻어옵니다
      navigator.geolocation.getCurrentPosition(function (position) {
        const lat = position.coords.latitude, // 위도
          lon = position.coords.longitude; // 경도

        const locPosition = new kakao.maps.LatLng(lat, lon); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
        const message = `'<div style="padding:10px;">여기에 계신가요?!</div>'`; // 인포윈도우에 표시될 내용입니다
        map.panTo(locPosition);

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
      console.log("찐락", Lockers);

      const iwContent = `<div style=width:"95%",height:"70vh">${message} <div/>`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      // 인포윈도우를 생성합니다
      const infowindow = new kakao.maps.InfoWindow({
        content: iwContent,
        removable: iwRemoveable,
      });
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
      const imageSrc =
          "https://ifh.cc/g/HqQFF7.png", // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      const markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );

      const imageSrc2 =
          "https://ifh.cc/g/X5DS1w.png", // 마커이미지의 주소입니다
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
        });
      }

      for (var i = 0; i < positions.length; i++) {
        // const lockerno = position.title;
        // console.log("이건 진짜 라커넘버", lockerno);

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
              console.log(title);
            };
          })(positions[i].title)
        );
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
          books.push({ ...res.data.data[i], id: i + 1 });
        }
        setbookList(books);
        console.log("book", books);
        console.log("bookset", bookList);
        toggleModalSearch2();
      })

      .catch((error) => {
        alert("검색어를 입력해주세요.");
      });
  }

  function searchLocker(title) {
    axios
      .get(`https://i8b201.p.ssafy.io/backend/book/locker/${title}`)
      .then((res) => {
        console.log("라커 res", res);
        const books = [];
        for (let i = 0; i < res.data.count; i++) {
          books.push({ ...res.data.data[i], id: i + 1 });
        }
        setlockerList(books);
        console.log("lockerbooks", books);
        // console.log('lockerlist',lockerList)
        setIsLoading(false);
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
      {/* {searchBook} */}
      <div
        style={{
          position: "absolute",
          zIndex: 100,
          width: "100%",
          top: 10,
          paddingInline: 10,
          paddingTop: 15,
        }}
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
              className="search_input"
              name="search"
              placeholder="검색할 도서를 입력해주세요."
              innerRef={searchbookRef}
              style={{
                width: "70%",
                boxShadow: "rgba(0, 0, 0, 0.2) 3px 3px 3px",
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
              }}
            >
              검색
            </Button>
          </div>
        </form>
      </div>
      <Card>
        <div id="map" style={{ width: "100%", height: "93vh" }}></div>
      </Card>

      <Modal
        modalClassName="modal-search"
        isOpen={modalSearch2}
        toggle={toggleModalSearch2}
        style={{position: "absolute",
          bottom: 0,
          left: 0,
          right: 0,
          height: "20%",
          backgroundColor: "transparent",}}
      >
        <button
          aria-label="Close"
          className="close"
          onClick={() => {
            toggleModalSearch2();
            // handle.clickButton();
          }}
        >
          <i className="tim-icons icon-simple-remove" />
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
        // style={{ width: "70%" }}
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
          <i className="tim-icons icon-simple-remove" />
        </button>
      </Modal>
    </>
  );
}

export default Map;
