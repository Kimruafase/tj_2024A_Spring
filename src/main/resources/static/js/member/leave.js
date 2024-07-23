console.log("delete.js");

deleteBtn();
function deleteBtn(){
    let deleteBtn = document.querySelector("#deletePage");
    deleteBtn.innerHTML = ` 
                           삭제할 계정의 비밀번호 : <input id = "pwDelete" type="text"/> <br>
                            <button type="button" onclick="doDelete()"> 탈퇴하기 </button>
                            `;
}

function doDelete(){
    let pwDelete = document.querySelector("#pwDelete").value;
    $.ajax({
        method : 'delete',
        url : "/member/myDelete",
        data : {pw : pwDelete},
        success : function response(result){
            console.log(result);
            if(result){
                alert("회원 삭제 성공");
                location.href="/";
            }else{
                alert("회원 삭제 실패");
            }
        }
    })
}