function onGeoOk(position) { // 유저 위치 받아오면
	showMap(position); // 지도 중심을 유저 현재위치로
} // onGeoOK

function onGeoError() { // 유저 위치 못받아오면
	showMap(0); // 서면 롯데백화점을 중심으로
} // onGeoError

navigator.geolocation.getCurrentPosition(onGeoOk, onGeoError ); // 유저 현재 위치 받아오기


function showMap(position) {
	if(position == 0) { // 유저 위치 정보 못받아왔으면
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
		  mapOption = {
		    center: new kakao.maps.LatLng(35.15683361469301, 129.05663293413534), // 지도의 중심좌표 - 서면 롯데백화점으로 설정
		    level: 4 // 지도의 확대 레벨, 
		  };
		
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	}else { // 유저 위치정보 받아왔으면
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
		  mapOption = {
		    center: new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude), // 지도의 중심좌표 - 서면 롯데백화점으로 설정
		    level: 4 // 지도의 확대 레벨, 
		  };
	
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		var imageSrc = './images/here.png', // 마커이미지의 주소입니다    
	    imageSize = new kakao.maps.Size(45, 65), // 마커이미지의 크기입니다
	    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
	      
		// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
		    markerPosition = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude); // 마커가 표시될 위치입니다
		
		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		    position: markerPosition, 
		    image: markerImage // 마커이미지 설정 
		});
		
		var iwContent = '<div style="padding:5px;">현재 위치</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
	    iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

		// 인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
		    content : iwContent,
		    removable : iwRemoveable
		});
	
	    infowindow.open(map, marker);  // 처음엔 인포윈도우 열린 상태임
	    // 마커에 클릭이벤트를 등록합니다
		kakao.maps.event.addListener(marker, 'click', function() {
		      // 마커 위에 인포윈도우를 표시합니다
		      infowindow.open(map, marker);  
		});
		
		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);  
	}
	
	
	// 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다 
	// var positions = [{
	//   content: '<div>예시1</div>',
	//   latlng: new kakao.maps.LatLng(127.387989439949, 36.3475950596837),
	// }];
	
	
	// async function data(pathToCsv) {
	//   let dataset = await d3.csv(pathToCsv, function (d) {
	//     if (d.poi_nm == "CGV") {
	//       let obj = {};
	//       obj.content = '<div>' + d.branch_nm + '</div>';
	//       obj.latlng = new kakao.maps.LatLng(d.x, d.y); 
	
	//       return obj;
	//     }
	//   })
	//   return dataset;
	// };
	
	// data("cgvList.csv").then(function(d) {
	//   d.forEach(function(p){
	//       console.log(p.content);
	//   })
	// });
	
	
	// 국내 영화관 목록 데이터 csv 파일 불러오기
	d3.csv("./main/cgvList.csv", function (data) {
	  if (data.poi_nm == "CGV") { // CGV만 파싱
	    let obj = {}; // 지점명과 주소, 위도 경도 반환
	
	    obj.content = 
	      '<div class="wrap">' +
	      '    <div class="info">' +
	      '        <div class="title">CGV ' +
	                  data.branch_nm + // 지점명
	      '        </div>' +
	      '        <div class="body">' +
	      '            <div class="desc">' +
	      '                <div class="ellipsis">'+
	                          data.sido_nm + " " + data.sgg_nm + " " + data.bemd_nm + " " + data.ri_nm + " " + data.beonji + // 주소
	      '                </div>' +
	      '                <div class="jibun ellipsis">(도로명) ' +
	                          data.rd_nm + " " + data.bld_num + // 도로명 주소
	      '                </div>' +
	      '            </div>' +
	      '        </div>' +
	      '    </div>' +
	      '</div>';
	    obj.latlng = new kakao.maps.LatLng(data.y, data.x); // 위도, 경도
	
	    return obj;
	  }
	}).then((positions) => {
	  for (var i = 0; i < positions.length; i++) {
	    // 마커를 생성합니다
	    var marker = new kakao.maps.Marker({
	      map: map, // 마커를 표시할 지도
	      position: positions[i].latlng // 마커의 위치
	    });
	
	    // 마커 위에 커스텀오버레이를 표시합니다
	    // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
	    var overlay = new kakao.maps.CustomOverlay({
	      content: positions[i].content,
	      map: map,
	      position: marker.getPosition()
	    });
	
	    overlay.setMap(null); // 처음엔 오버레이 다 닫은 상태
	
	    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, overlay)); // 마우스 올리면 오버레이 표시
	    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(overlay)); // 마우스 제거하면 오버레이 닫음
	
	  }
	});
} // showMap


// 오버레이를 표시하는 클로저를 만드는 함수
function makeOverListener(map, marker, overlay) {
  return function() {
    overlay.setMap(map);
  };
}

// 오버레이를 닫는 클로저를 만드는 함수
function makeOutListener(overlay) {
  return function() {
    overlay.setMap(null);
  };
}