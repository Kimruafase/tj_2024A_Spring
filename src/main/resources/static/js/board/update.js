console.log("update.js");

function update(){
    let newTitle = document.querySelector("#newTitle").value;
    let newContent = document.querySelector("#newContent").value;
    let category = document.querySelector("#category").value;
    let urlParams = new URL( location.href ).searchParams.get('bno');

    $.ajax({
        method : 'put',
        url : "/board/update",
        data : JSON.stringify({btitle : newTitle, bcontent : newContent, bcno : category, bno : urlParams}),
        contentType : "application/json",
        success : function response (result){
            console.log(result);
            if(result){
                alert("글 수정 성공");
                location.href="/board/all"
            }else{
                alert("글 수정 실패")
            }
        }
    })
}