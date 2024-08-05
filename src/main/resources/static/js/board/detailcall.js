console.log("detailCall.js");

let bno = new URL(location.href).searchParams.get('bno');

detailCall();
viewUpdate();
function detailCall() {
    let detailArea = document.querySelector("#detailArea");
    let html = ``;
    console.log(new URL(location.href))
    // 2. new URL( location.href ).searchParams       : 쿼리스트링( ? 뒤로 매개변수들 )
    console.log(new URL(location.href).searchParams)
    // 3. new URL( location.href ).searchParams.get('key')
    console.log(new URL(location.href).searchParams.get('bno'));
    // ================================================================== //

    // 1. 현재 URL 경로상의 'no' 이름의 매개변수 값 호출 , view.html?no=7

    $.ajax({
        method: 'get',
        url: "/board/detailcall",
        data: { bno: bno },
        success: function response(result) {

            html += `<div>
                        <div class="bcName">
                            ${result.bcname}
                        </div>
                        <div class="bTitle">
                            ${result.btitle}
                        </div>
                        <div class="etcBox">
                            ${result.id} , ${result.bview} , ${result.bdate}
                        </div>
                        <div class="bContent">
                            ${result.bcontent}
                        </div>
                        `;

            if (result.bfile == null) {

            } else {
                html += `<div> 첨부파일 :  ${result.bfile.split("_")[1]} <a href="/file/download?filename=${result.bfile}"> 다운로드 </a> </div>`;
            }
            html += `<div>
                        <button type="button" onclick="updateLoginTest()"> 수정 </button>
                    </div> 
                        `;
            detailArea.innerHTML = html;

        }
    })

}

function updateLoginTest() {
    $.ajax({
        async: false, // false 는 동기화
        method: 'get',
        url: '/member/login/check',
        success: r => {
            console.log(r);
            if (r == '') {
                alert("로그인 하고 오세요.");
                location.href = "/member/login"
            } else {
                location.href = `/board/update?bno=${bno}`;
            }
        }
    })
}

function viewUpdate() {
    $.ajax({
        method: 'get',
        url: "/board/viewupdate",
        data: { bno: bno },
        success: function response(result) {
            if (result) {

            } else {
                console.log("조회수 증가 X");
            }
        }
    })
}


// 댓글 쓰기
function doReplyWrite() {
    console.log("doReplyWrite()");
    // 1. 입력 받은 댓글 내용 가져오기
    let brcontent = document.querySelector(".brcontent").value;
    // 현재 보고 있는 게시물번호 -> bno
    let brindex = 0;    // 0 : 댓글(상위)
    //  2. ajax 구문 작성
    $.ajax({
        async: false,
        method: "post",
        url: "/board/reply/write",
        data: JSON.stringify({ brcontent: brcontent, brindex: brindex, bno: bno }),
        contentType: "application/json",
        //  contentType : "application/x-www-form-urlencoded" -? ajax 기본값(생략 가능)
        //  contentType : false -> multipart/form-data
        //  contentType : "application/json" -> JSON 전송할 떄 사용
        success: function response(result) {
            if (result) {
                alert("댓글 쓰기 성공");
            } else {
                alert("댓글 쓰기 실패 (로그인 했는 지 확인하세요) ");
            }
        }

    })

}
replyRead();
// 댓글 출력
function replyRead() {
    console.log("replyRead()");
    let replyBox = document.querySelector("#replyBox");
    let html = ``;
    $.ajax({
        async: false,
        method: "get",
        url: "/board/reply/read",
        data: { bno: bno },
        success: function response(result) {
            console.log(result);
            for (let i = 0; i < result.length; i++) {
                html += `<div>
                        작성자 : ${result[i].no}
                        </div>
                        <div>
                        내용 : ${result[i].brcontent}
                        </div>
                        <div>
                        작성일 : ${result[i].brdate}
                        </div>`;
            }

        }

    })
    replyBox.innerHTML = html;
}
