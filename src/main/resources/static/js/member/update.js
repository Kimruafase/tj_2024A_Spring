console.log("update.js");

updateBtn();
function updateBtn(){
    let updateBtn = document.querySelector("#updatePage");

    updateBtn.innerHTML = ` 수정할 이름 : <input id = "nameUpdate" type="text"/> <br>
                            기존 비밀번호 : <input id = "pwOld" type="text"/> <br>
                            수정할 비밀번호 : <input id = "pwUpdate" type="text"/> <br>
                            수정할 전화번호 : <input id = "phoneUpdate" type="text"/> <br>
                            <button type="button" onclick="doUpdate()"> 수정하기 </button>
                            `;

}

function doUpdate(){
    let nameUpdate = document.querySelector("#nameUpdate").value;
    let pwOld = document.querySelector("#pwOld").value;
    let pwUpdate = document.querySelector("#pwUpdate").value;
    let phoneUpdate = document.querySelector("#phoneUpdate").value;
    $.ajax({
        method : 'put',
        url : "/member/myUpdate",
        data : {name : nameUpdate, newPw : pwUpdate, phone : phoneUpdate, oldPw : pwOld},
        success : function response(result){
            console.log(result);
            if(result){
                alert("회원 정보 수정 성공");
                location.href="/";
            }else{
                alert("회원 정보 수정 실패");
            }
        }
    })
}