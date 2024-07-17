console.log("signUp.js");

function doSignUp(){
    let id = document.querySelector("#idInput").value;
    let pw = document.querySelector("#pwInput").value;
    let name = document.querySelector("#nameInput").value;
    let email = document.querySelector("#emailInput").value;
    let phone = document.querySelector("#phoneInput").value;
    $.ajax({
        method : 'post',
        url : '/member/signup',
        data : {id : id, pw : pw, name : name, email : email, phone : phone},
        success : function response (result){
            if(result){
                console.log(result);
                alert("회원가입 성공");
                location.href="/member/login";
            }else{
                alert("회원가입 실패");
            }
        }
    })
}

function doLogIn(){
    let id = document.querySelector("#idInput").value;
    let pw = document.querySelector("#pwInput").value;
    $.ajax({
        method : 'post',
        url : '/member/login',
        data : {id : id, pw : pw},
        success : function response(result){
            if(result){
                console.log(result);
                alert("로그인 성공");
                location.href="/";
            }else{
                alert("로그인 실패");
            }
        }
    })
}