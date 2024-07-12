console.log("restcontroller.js");

//  rest3get()
function rest3get(){
    console.log(`rest3get`);
    //  1. ajax 옵션 객체 정의
    let option = {
        // url : "http://localhost:8080/example/rest3?key=qwe",        // 통신할 URL, SPRING의 컨트롤러 Mapping 주소
        url : "/example/rest3?key=qwe",
        method : 'get',                 // 통신할 HTTP 메소드 선택
        success : function(response){   // 통신 성공 시 응답받은 데이터
            console.log(response);
        }
    }
    // 2. ajax 메소드에 옵션 넣어서 실행
    $.ajax(option);
}

//  rest3post()
function rest3post(){
    console.log(`rest3post`);
    $.ajax({                            
        url : "/example/rest3?key=qwe",             // HTTP url 주소
        method : 'post',                            // HTTP 메소드
        success : function(response){               // HTTP 성공 응답, controller 가 return한 값
            console.log(response);
        }   // success end
    }) // ajax end
} // f end


//  rest3put()
function rest3put(){
    console.log(`rest3put`);
    let value = document.querySelector(`#value`).value;
    $.ajax({                            
        url : "/example/rest3",                     // HTTP url 주소
        method : 'put',                             // HTTP 메소드
        data : {'key' : value},                     // data = {form data}  {key : value, key : value}
        success : function(response){               // HTTP 성공 응답, controller 가 return한 값
            console.log(response);
        }   // success end
    }) // ajax end
}

//  rest3delete()
function rest3delete(){
    console.log('rest3delete');
    let value = document.querySelector(`#value`).value;
    $.ajax({                            
        url : "/example/rest3",                     // HTTP url 주소
        method : 'delete',                          // HTTP 메소드
        data : {'key' : value},                     // data = {form data}  
        success : function(response){               // HTTP 성공 응답, controller 가 return한 값
            console.log(response);
        }   // success end
    }) // ajax end

}