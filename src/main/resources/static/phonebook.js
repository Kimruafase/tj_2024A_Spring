console.log("phonebook.js 열림")
//let phonebookDB = [];

//  1.  등록 함수 : 등록 버튼을 클릭했을 때
function postPhone(){
    //  1) 입력받고
    let name = document.querySelector("#name").value;
    let phone = document.querySelector("#phone").value;
    //  2) 객체화
    let phoneDto = {name : name, phone : phone};

//    //  3) 객체를 controller 에게 전달
//    phonebookDB.push(phoneDto);
//    //  4) 안내 / 새로고침
//    alert('save');
//    getPhone();
    //  2. html 에 Jquery 라이브러리 가져왔다면 AJAX 함수 사용 가능
        //  2-1. AJAX 에 들어갈 옵션 객체 정의
    let option = {
        url : "http://localhost:8080/phone/create",       //  통신할 경로 -> Spring 의 Controller
        method : "post",        //  HTTP 가 지원하는 함수 중 사용할 함수명(post, get, put, delete)
        data : JSON.stringify(phoneDto) ,       //  통신할 경로에 보낼 데이터 -> Spring 의 Controller 에게 보낼 데이터
        contentType : "application/json",   //  data 옵션에 있는 타입
        success : function response(result){    //  통신에 성공했을 떄 응답 받을 함수를 정의하고 매개변수는 응답의 데이터가 들어온다.
            console.log(result);
            if(result){
                alert('save');
                getPhone();
            }else{
                alert('fail');
            }
        }
    }
    //  2-2. AJAX 함수 호출         ,$ : Jquery 문법
    $.ajax(option);

}

//  2.  출력 함수 : 등록 처리가 되었을 때 or js가 열렸을 때 최초 1번
getPhone();
function getPhone(){
    //  1) 어디에
    let phoneListBox = document.querySelector("#phoneListBox");
    //  2) 무엇을
    let html = "";

    let option = {
        url : "http://localhost:8080/phone/read",         //  누구에게
        method : "get",                                  //  어떤 방식으로
//        data :                                        //  무엇을 보내고
        success :  function response(result){          //  무엇을 받을 지
            console.log(result);
            result.forEach(phone =>{
                    html += `<div>
                                <span>
                                    ${phone.name}
                                </span>
                                <span>
                                    ${phone.phone}
                                </span>
                             </div>`
                });
                //  3) 출력
                phoneListBox.innerHTML = html;

        }
    }       //  ajax 통신 option 설정 end
    // AJAX 실행
    $.ajax(option);     //      $ is not defined



//    phonebookDB.forEach(phone =>{
//        html += `<div>
//                    <span>
//                        ${phone.name}
//                    </span>
//                    <span>
//                        ${phone.phone}
//                    </span>
//                 </div>`
//    });
//    //  3) 출력
//    phoneListBox.innerHTML = html;

}