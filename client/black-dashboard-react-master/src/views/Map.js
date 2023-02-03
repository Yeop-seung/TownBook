import React, { useEffect } from "react";
import './Map.css'
import { Card } from "reactstrap";

const { kakao } = window;

function Kakao() {
    useEffect(() => {
        

    const container = document.getElementById('map')    //찾으려는 id 
    const options = {
        center : new kakao.maps.LatLng(37.49676871972202, 127.02474726969814),
        level : 3
    }

    const map = new kakao.maps.Map(container, options)

    const imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커이미지의 주소입니다    
          imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
          imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
    const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
          markerPosition = new kakao.maps.LatLng(37.49676871972202, 127.02474726969814); // 마커가 표시될 위치입니다


    // const markerPosition  = new kakao.maps.LatLng(37.49676871972202, 127.02474726969814); 

    // 마커를 생성합니다
    const marker = new kakao.maps.Marker({
        position: markerPosition,
        image : markerImage
    });

    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);

    const iwContent = '<div style=width:"95%",height:"70vh"">여기다가 책 넣어야 된다. <div/>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
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
    
    )}, [] )

    return(
      <div className="content"><Card><div id="map" style={{width:'100%',height:"70vh"}}></div></Card></div>
      
        
    )
}

export default Kakao