// JavaScript 수정

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = { 
    center: new kakao.maps.LatLng(37.514804143289204, 126.93799202869118), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

//마커가 표시될 위치 변수 선언
var markerPosition = new kakao.maps.LatLng(37.514804143289204, 126.93799202869118);
// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition, //해당 표시할 위치 시킨다.
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);
// 지도 js --------------------------------------------------