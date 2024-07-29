/*
    > 공공 데이터 : https://www.data.go.kr/
        > 공공 기관에서 제공하는 정보들
        > 1. 로그인 / 회원가입
        > 2. API 검색
        > 3. 오픈 API -> 활용신청 
 */

// 부평구 주유소 현황
api1();
api2();
function api1() {
    $.ajax({
        async: false,
        method: "get",
        url: "http://api.odcloud.kr/api/15102672/v1/uddi:5e2a4b30-28fb-4e8f-bc44-9a6db8a6a8db?page=1&perPage=39&serviceKey=B0W4tkw5nadW%2BBvYqTIXEdEcJt2fj5EDSlsycqtw4jwxbJdkg%2BsqhEdEofUfKo4iLluKdlMXYSNQ8L15LOeZ3g%3D%3D",
        success: r => {
            console.log(r);
            let dataArray = r.data;
            console.log(dataArray);

            let html = ``;
            dataArray.forEach(data => {
                html += `<tr>
                            <th>
                                ${data.상호}
                            </th>
                            <th>
                                ${data.전화번호}
                            </th>
                            <th>
                                ${data.주소}
                            </th>
                        </tr>
                            `;
            });
            document.querySelector(".api1Tbody").innerHTML = html;
        }

    })
}

function api2() {
    $.ajax({
        async: false,
        method: "get",
        url: "http://api.odcloud.kr/api/15051492/v1/uddi:852bbc11-63ed-493e-ab09-caaaf54fd144?page=1&perPage=35&serviceKey=B0W4tkw5nadW%2BBvYqTIXEdEcJt2fj5EDSlsycqtw4jwxbJdkg%2BsqhEdEofUfKo4iLluKdlMXYSNQ8L15LOeZ3g%3D%3D",
        success: r => {
            console.log(r);
            let dataArray = r.data;
            console.log(dataArray);

            let html = ``;
            dataArray.forEach(data => {
                html += `<tr>
                            <th>
                                ${data.약국명}
                            </th>
                            <th>
                                ${data.전화번호}
                            </th>
                            <th>
                                ${data.소재지도로명주소}
                            </th>
                        </tr>`;
            })
            document.querySelector(".api2Tbody").innerHTML = html;
            api3(r.data);
        }
    })
}


//  카카오 지도 API

/*

    1. 카카오 개발자 센터 : https://developers.kakao.com/
    2. 로그인
    3. 플랫폼 신청
        > 상단 메뉴 -> 내 어플리케이션 -> 어플리케이션 추가
    4. 어플리케이션 선택 클릭
        > 앱 키 -> JavaScript 앱 키 복사
    []


*/
function api3(data) {



    var container = document.querySelector('#map'); //지도를 담을 영역의 DOM 레퍼런스
    var options = { //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.4562557, 126.7052062), //지도의 중심좌표.
        level: 8 //지도의 레벨(확대, 축소 정도)
    };

    var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

    // 마커를 표시할 위치와 title 객체 배열입니다 
    // positions : 여러개 마커의 위도와 경도, {} : 하나의 마커의 위도와 경도, latlng : new kakao.maps.LatLng(위도, 경도)
    // 리스트명.forEach(반복 요소 =>{}); : 리스트 내 요소를 하나씩 반환해서 반복
    // let 반환 리스트 = 리스트명.map(반복 요소 =>{ return 반환값 }); : 리스트 내 요소를 하나씩 반환해서 반복, 실행문에서 반환이 가능하다,
    // 차이점 : 실행문 결과를 반환 가능, 반복 반환 결과를 배열로 최종 반환
    // data는 api2 함수에서 공공데이터 포털의 약국명과 위도 경도를 전달받은 객체
    /*
     for( let i = 0 ; i < data.length ; i++ ){
        // 1. 객체 생성
        let location = { title : data[i].약국명 , latlng : new kakao.maps.LatLng( data[i].위도 , data[i].경도 ) }
        positions.push( location );
    }
    console.log( positions );
    */

    var positions = data.map(d => {
        console.log(d);
        // 1. 객체 생성
        let location = { title: d.약국명, latlng: new kakao.maps.LatLng(d.위도, d.경도) }
        console.log(location);
        // 2. 객체 반환
        return location;    // 반복하면서 생성된 객체를 반환해서 positions 배열에 최종 대입
    })
    console.log(positions);

    // 마커 이미지의 이미지 주소입니다
    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

    for (var i = 0; i < positions.length; i++) {

        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다    
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage // 마커 이미지 
        });
    }

}