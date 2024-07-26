console.log("detailCall.js");

detailCall();
viewUpdate();
function detailCall(){
    let detailArea = document.querySelector("#detailArea");
    let html = ``;
    console.log( new URL( location.href ) )
    // 2. new URL( location.href ).searchParams       : 쿼리스트링( ? 뒤로 매개변수들 )
    console.log( new URL( location.href ).searchParams )
    // 3. new URL( location.href ).searchParams.get('key')
    console.log( new URL( location.href ).searchParams.get('bno') );
    // ================================================================== //

    // 1. 현재 URL 경로상의 'no' 이름의 매개변수 값 호출 , view.html?no=7
    let urlParams = new URL( location.href ).searchParams.get('bno');

    $.ajax({
        method : 'get',
        url : "/board/detailcall",
        data : {bno : urlParams},
        success : function response (result){
            
                html += `<div> 카테고리 : ${result.bcname} </div>
                        <div> 게시물 번호 : ${result.bno}</div>
                        <div> 제목 : ${result.btitle} </div>
                        <div> 내용 : ${result.bcontent} </div>
                        <div> 작성자 id : ${result.id}, 작성일 : ${result.bdate}, 조회수 : ${result.bview} </div>
                        <div> 첨부파일 :  ${result.bfile == null ? '' : result.bfile} <a href="/file/download?filename=${result.bfile}"> 다운로드 </a> </div>

                        <div>
                            <button type="button" onclick="location.href='/board/update?bno=${urlParams}'"> 수정 </button>
                        </div> 
                        `;
                        detailArea.innerHTML = html;
            
        }    
    })

}

function viewUpdate(){
    let urlParams = new URL( location.href ).searchParams.get('bno');

    $.ajax({
        method : 'get',
        url : "/board/viewupdate",
        data : {bno : urlParams},
        success : function response (result){
            if(result){

            }else{
                console.log("조회수 증가 X");
            }
        }
    })
}