import React, { useEffect, useRef } from "react";
import './Map.css'
import { Card,CardBody,CardHeader,CardTitle,UncontrolledAlert } from "reactstrap";
import axios from "axios";
const { kakao } = window;
function submitHandler(event) {
    event.preventDefault();

   
    // console.log(context);
    axios.get('/naver/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=%EC%BD%94%EC%8A%A4%ED%94%BC')
    .then(data => {
      console.log(data)
    })
  }
function Kakao() {
    useEffect(() => {
        

    const container = document.getElementById('map')    //찾으려는 id 
    const options = {
        center : new kakao.maps.LatLng(37.49676871972202, 127.02474726969814),
        level : 3
    }

    

    if (navigator.geolocation) {
    
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition(function(position) {
        
        const lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도
        
        const locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
            message = '<div style="padding:5px;">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다
            options.center=locPosition;


        // 마커와 인포윈도우를 표시합니다
        displayMarker(locPosition, message);
            
    });
    
    } else {
        // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
        const locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
        message = 'geolocation을 사용할수 없어요..'
        
        displayMarker(locPosition, message);
    }
    const map = new kakao.maps.Map(container, options)
    function displayMarker(locPosition,message){
        const imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커이미지의 주소입니다    
          imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
          imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
    const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)


    // const markerPosition  = new kakao.maps.LatLng(37.49676871972202, 127.02474726969814); 

    // 마커를 생성합니다
    const marker = new kakao.maps.Marker({
        position: locPosition,
        image : markerImage
    });

    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);

    const iwContent = `<div style=width:"95%",height:"70vh"">${message} <div/>`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    // 인포윈도우를 생성합니다
    const infowindow = new kakao.maps.InfoWindow({
    content : iwContent,
    removable : iwRemoveable
    });
    // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
    const zoomControl = new kakao.maps.ZoomControl();

    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {
    // 마커 위에 인포윈도우를 표시합니다
        infowindow.open(map, marker)
    }
    
    )
    }
    }, [] )

    return(
        <div className="content">
            <div>
                <form>
                    <input type='text' maxLength='20' className='search_input' name='search' placeholder='검색어를 입력해주세요.'/>
                    <button
                        className="btn-search"
                        type="submit"
                        onClick={submitHandler}
                    >
                      검색
                    </button>
                </form>
            </div>
            <Card>
                <div id="map" style={{width:'100%',height:"70vh"}}></div>
            </Card>
            <Card>
            <CardHeader>
                <CardTitle tag="h4">검색결과</CardTitle>
              </CardHeader>
              <CardBody>
                
              </CardBody>
            </Card>
        </div>
      
        
    )
}

export default Kakao