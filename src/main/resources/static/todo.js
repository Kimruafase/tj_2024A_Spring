console.log("todo.js");
let todoList = {};
print();
function get(){
    todoInput = document.querySelector(`#todoInput`);           // HTML의 id="todoInput"의 입력 받음
    let todo = todoInput.value;
    let tno = todoInput.value;
    let tstate = todoInput.value;
    todoList = {todo : todo , tno : tno, tstate : tstate};
    $.ajax({
        url : "/todo/get",
        method : 'get',    
        data : {'key' : todo},
        success : function(response){
            console.log(response);
            alert(` 리스트 저장 성공`);                                  // 입력이 잘 됐다면 메시지 출력
            print();                                                    // 출력 함수로 입력을 내보냄.
        }
    })

                                     // value값을 todo에 저장
    // todoList.push(todo + `,X`);                                 // 저장받은 value값을 배열에 저장, `,X`도 같이 저장하는데 이유는 스위치 역할을 만들기 위함
    console.log(todoList);                                      // 배열이 잘 저장됐는 지 확인


}

function print(){
    let todoBox = document.querySelector(`#todoBox`);           // 출력할 HTML의 div 부분의 id를 todoBox에 대입
    let html = ``;                                               // HTML을 연결시킬 변수 초기화
        $.ajax({
            url : "/todo/post",
            method : 'post',
            success : function response(result){
                console.log(result)
                result.forEach(todoBox => {
                    if(todoBox.tState == 0){
                    html += `<div id = "whiteBox"> 
                                <span>
                                ${todoBox.toDo}
                                </span>
                    <button type="button" onclick="change(${ todoBox.tNo })"> 변경 </button>
                    <button type="button" onclick="remove( ${ todoBox.tNo } )"> 삭제 </button>
                    </div>`
                    } else if(todoBox.tState == 1){
                        html += `<div id = "blackBox"> 
                                <span>
                                ${todoBox.toDo}
                                </span>
                    <button type="button" onclick="change(${ todoBox.tNo })"> 변경 </button>
                    <button type="button" onclick="remove( ${ todoBox.tNo } )"> 삭제 </button>
                    </div>`
                    }
                });
        
                todoBox.innerHTML = html;
            }
        });
    
}
function remove(index){
       $.ajax({
        url : "/todo/delete",
        method : 'delete',
        data : {key : index},
        success : function(response){
        console.log(response);
        print();
        }
       })                                              // 삭제가 되면 배열의 상태가 변경되므로 배열의 상태를 다시 출력(화면 업데이트)
       
}
function change(index){ 
    let todoBox = document.querySelector(`#todoBox`);           // 출력할 HTML의 div 부분의 id를 todoBox에 대입
    $.ajax({
        url : "/todo/put",
        method : 'put',
        data : {key : index},
        success : function response(result){
        console.log(result)
        if(todoList.tstate == 0){
            todoList.tState == 1;
        }
        else if(todoList.tState == 1){
            todoList.tState == 0;
        }
        print();
       
        }
       })              
        
        // let s=todoList[i].split(",")[0]
        // let e=todoList[i].split(",")[1]
        // if(e=='O'){                                             // 만약 todoList의 i번째 인덱스에서 "," 뒤의 값이 'O'라면
        // todoList[i] = s + ",X"                                  // todoList의 i번째 인덱스에 "," 앞의 값에 ",X"를 연결하고 대입
        // }else{                                                  // 만약 todoList의 i번째 인덱스에서 "," 뒤의 값이 'X'라면
        // todoList[i] = s + ",O"                                  // todoList의 i번째 인덱스에 "," 앞의 값에 ",O"를 연결하고 대입
        // }console.log(e); console.log(todoList);                                        // todoList의 i번째 인덱스의 "," 뒤의 값을 확인
        // print();                                                // 바뀐 todoList 배열의 i번째 인덱스값을 토대로 다시 출력(화면 업데이트)
}