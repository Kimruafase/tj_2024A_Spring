console.log("header.js");

doLogInCheck();

function doLogInCheck() {
    $.ajax({
        async: false, // false 는 동기화
        method: 'get',
        url: '/member/login/check',
        success: function response(result) {
            console.log(result);
            if (result == '') {
                console.log("비 로그인 상태")
                html = `<li class="nav-item">
                            <a class="nav-link" href="/member/signup">
                                회원가입
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/member/login">
                                로그인
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/member/search">
                                아이디 / 비밀번호 찾기
                            </a>
                        </li>`;

            } else {
                console.log("로그인 상태")
                html = `<li class="nav-item">
                        ${result.id}님
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#" onclick="doLogOut()">
                                로그아웃
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/member/mypage">
                                내 정보
                            </a>
                        </li>`;

            }
            document.querySelector("#loginMenu").innerHTML = html;
        }
    })
}

function doLogOut() {
    $.ajax({
        method: 'get',
        url: '/member/logout',
        success: function response(result) {
            console.log(result);
            location.href = "/";

        }
    })
}