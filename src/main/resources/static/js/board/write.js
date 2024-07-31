
console.log('write.js');
//  1. 첨부파일을 쓰지 않는 게시물 쓰기 함수
// function _write(){

//     let title = document.querySelector('#title').value;
//     let content = document.querySelector('#content').value;
//     let category = document.querySelector('#category').value;
//     // let file = document.querySelector("#file").value;

//     console.log(file);
//         $.ajax({
//             method: "post" ,
//             url: "/board/post" ,
//             data : JSON.stringify({btitle : title , bcontent : content , bcno : category}) ,
//             contentType : "application/json",
//             success:(result)=>{
//                  console.log(result);
//                  if(result){
//                  alert('글쓰기 성공');
//                  location.href="/board/all";
//                  }else{
//                  alert('글쓰기 실패');
//                  }

//             } // success method end
//     })
// }



// 2. 첨부파일을 전송하는 대용량 form 타입의 통신
function _write() {
    //  1) form 가져오기 
    let boardWriteForm = document.querySelector(".boardWriteForm");
    console.log(boardWriteForm);
    //  2) form HTML 을 byte로 변환 시켜주는 객체
    let boardWriteFormData = new FormData(boardWriteForm);
    console.log(boardWriteFormData);
    $.ajax({
        async: false,
        method: "post",
        url: "/board/post",
        data: boardWriteFormData,
        contentType: false,
        processData: false,
        success: r => {
            console.log(r);
            location.href = "/board/all"
        },
        error: e => {
            console.log(e);
        }

    })
}

// 3. 서머노트 실행
$(document).ready(function () {

    //  서머노트 옵션
    let option = {
        height: 500,   // 에디터 높이
        lang: 'ko-KR'  // 도움말을 한글로 표기
    }

    $('#summernote').summernote(option);
});