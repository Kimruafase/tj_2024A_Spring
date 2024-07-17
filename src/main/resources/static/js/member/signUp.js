console.log("signUp.js");

function doSignUp(){
    //  html에서 입력 받은 값을 가져옴
    let id = document.querySelector("#idInput").value;
    let pw = document.querySelector("#pwInput").value;
    let name = document.querySelector("#nameInput").value;
    let email = document.querySelector("#emailInput").value;
    let phone = document.querySelector("#phoneInput").value;
    $.ajax({
        method : 'post',                //  method는 post 방식
        url : '/member/signup',         //  url은 /member/signup
        data : {id : id, pw : pw, name : name, email : email, phone : phone},   //  url에 보낼 키와 값
        success : function response (result){                                   //  응답 받을 것 
            if(result){                                                         //  result가 true라면 회원가입 성공 메시지 출력 후 로그인 화면 이동   
                console.log(result);
                alert("회원가입 성공");
                location.href="/member/login";
            }else{
                alert("회원가입 실패");                                          //  result가 false라면 회원가입 실패 메시지 출력  
            }
        }
    })
}

function doLogIn(){
    //  html에서 입력 받은 값을 가져옴
    let id = document.querySelector("#idInput").value;
    let pw = document.querySelector("#pwInput").value;
    $.ajax({
        method : 'post',            //  method는 post 방식
        url : '/member/login',      //  url은 /member/login
        data : {id : id, pw : pw},  //  url에 보낼 키와 값
        success : function response(result){    //  응답 받을 것
            if(result){                         //  result가 true 라면 로그인 성공 메시지 출력 후 홈 화면 이동
                console.log(result);
                alert("로그인 성공");
                location.href="/";
            }else{
                alert("로그인 실패");           //  result가 false라면 로그인 실패 메시지 출력
            }
        }
    })
}