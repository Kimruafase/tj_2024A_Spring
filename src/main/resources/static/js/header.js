console.log("header.js");

doLogInCheck();

function doLogInCheck(){
    $.ajax({
        method : 'get',
        url : '/member/login/check',
        success : function response(result){
            console.log(result);
            if(result == ''){
                console.log("비 로그인 상태")
            }else{
                console.log("로그인 상태")
            }
        }
    })
}

function doLogOut(){
    $.ajax({
        method : 'get',
        url : 'member/logout',
        success : function response(result){
        console.log(result);
        location.href="/";

        }
    })
}