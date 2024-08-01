console.log("delete.js");

function deleteLoginTest() {
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
                doDelete();
            }
        }
    })
}




function doDelete() {
    let urlParams = new URL(location.href).searchParams.get('bno');

    $.ajax({
        async: false,
        method: 'delete',
        url: "/board/delete",
        data: { bno: urlParams },
        success: function response(result) {
            if (result) {
                alert("글 삭제 성공");
                location.href = "/board/all"
            } else {
                alert("글 삭제 실패");
            }
        }
    })
}