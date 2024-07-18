console.log("mypage.js")

myInfo();
function myInfo(){
    let myPage = document.querySelector("#myPage");
    let html = '';

    $.ajax({
        method : 'get',
        url : "/member/myInfo",
        success : function response (result){
            console.log(result)
            if(result == ''){
                alert("로그인 후 접속하세요");
                location.href="/member/login";
            }
            html = `<div>
                        <ul>
                            <li>
                            회원번호 : ${result.no}
                            </li>
                            <li>
                            아이디 : ${result.id}
                            </li>
                            <li>
                            이름 : ${result.name}
                            </li>
                            <li>
                            이메일 : ${result.email}
                            </li>
                            <li>
                            핸드폰 : ${result.phone}
                            </li>
                            
                        </ul>
                        </div>`;
                        myPage.innerHTML = html;
        }
    })
}