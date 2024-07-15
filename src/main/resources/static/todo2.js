console.log("todo.js");
// let todoList = ["밥먹기,X"];
toDoReadAll();
function toDoCreate(){
    console.log("toDoCreate() load")
    //  1. HTML 입력받은 값 가져오기 
    let todoInput = document.querySelector(`#todoInput`);
    console.log(todoInput);
    let tContent = todoInput.value;
    console.log(tContent);

    //  2. AJAX (JQuery 라이브러리) 이용한 웹 서버(컨트롤러) 통신해서 요청과 응답하기 
    let option = {
        method : 'post',                            // HTTP 메소드 선택(GET, POST, PUT, DELETE)
        url : `/todo2/post?tContent=${tContent}`,   // HTTP 통신할 경로 작성(IP와 PORT 생략)
        success : function response(result){        // HTTP 통신 성공 시 응답값은 함수의 매개변수로 받는다.
            console.log(result);
            if(result == true){
                alert("등록 성공");
                todoInput.value = '';       // 입력 상자에 입력된 값 초기화
                toDoReadAll();              // 할 일 목록 전체 출력 함수 호출
            }else{
                alert("등록 실패");
            }
        } 
    }

    $.ajax(option);         // $는 JQuery 문법이다.

    // todoList.push(todo + `,X`);             console.log(todoList);      // ',X' 같이 저장되게 하는 이유
    //                                                                         // 스위치 함수를 만들 때 구분하기 위함
    // alert(`리스트 저장 성공`);

    // print();
}

function toDoReadAll(){
    //  1. 어디에
    let todoBox = document.querySelector(`#todoBox`);
    //  2. 무엇을
    let html = ``;
     
    $.ajax({
        method : 'get',
        url : "/todo2/get",
        success : function response(result){

            //  1. 어디에
            let todoBox = document.querySelector(`#todoBox`);
            //  2. 무엇을
            let html = ``;

            console.log(result);    // 결과받은 데이터의 타입은 Array/list
            //  1. 일반적인 for문 사용 가능
            //  2. 리스트명.forEach(반복변수명 =>{실행할 반복문})
            result.forEach(todoDto => {
                html += `<div id=${todoDto.tState == 0 ? "whiteBox" : "blackBox"}>
                        <span> ${todoDto.tContent} </span>
                        <div>
                            <button type="button" onclick="toDoUpdate(${todoDto.tNo})">변경</button>
                            <button type="button" onclick="toDoDelete(${todoDto.tNo})">삭제</button>
                        </div>
                    </div>`     
            });

            //  3. 출력
            todoBox.innerHTML = html;    
        }

    });
    
}
function toDoDelete(tNo){
    $.ajax({
        method : 'delete',
        url : `/todo2/delete?tNo=${tNo}`,
        // [화살표 함수] success : result => {}
        // [익명 함수]  success : function(result){}
        // [일반 함수]  success : function response(result){}
        success : function response(result){
            console.log(result);  
            if(result == true){
                toDoReadAll();
            }else{
                alert("삭제 실패");
            }
                      
        }
    });
}       
function toDoUpdate(tNo){ 
    $.ajax({
        method : 'put',
        url : `/todo2/put?tNo=${tNo}`,
        success : function response(result){
            console.log(result);
            if(result == true){
                toDoReadAll();
            }else{
               alert("수정 실패");     
            }
        }
    });

}