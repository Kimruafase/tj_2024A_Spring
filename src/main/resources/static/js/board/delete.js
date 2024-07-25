console.log("delete.js");

function doDelete(){
    let urlParams = new URL( location.href ).searchParams.get('bno');

    $.ajax({
        method : 'delete',
        url : "/board/delete",
        data : {bno : urlParams},
        success : function response (result){
            if(result){
                alert("글 삭제 성공");
                location.href="/board/all"
            }else{
                alert("글 삭제 실패");
            }
        }
    })
}