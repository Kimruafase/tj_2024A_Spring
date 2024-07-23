console.log("signUp.js");

/* 
    onclick = "함수()" : 마우스로 클릭했을 때 작동하는 이벤트
    onkeyup = "함수()" : 키보드에서 키를 누르고 떼었을 때 작동하는 이벤트

*/

// 아이디 유효성 검사
function idCheck(){
    console.log("idcheck()");
    //  1. 입력된 값 가져오기
    let id = document.querySelector("#idInput").value;
    console.log(id);
    //  2. 정규표현식
    let idReg = /^[a-zA-Z0-9]{5,30}$/;
    console.log(idReg.test(id));
    if(idReg.test(id)){
        $.ajax({
            method : 'get',             // method
            url : '/member/idCheck',    // url
            data : {id : id},           // data
            success : function response(result){        // 응답 성공 시 실행할 함수
                console.log(result);
                if(result){ //  result 가 true라면 아무것도 실행 X
                    
                }else{  //  result 가 false 라면 아래 실행
                    document.querySelector(".idCheckBox").innerHTML =" 사용 중인 아이디입니다." 
                    checkArray[0] = false;
                }
            }
        })
        document.querySelector(".idCheckBox").innerHTML = "사용 가능한 아이디입니다.";
        checkArray[0] = true;
    }else{
        document.querySelector(".idCheckBox").innerHTML = "영문 대소문자와 숫자 조합의 5 ~ 30글자 사이만 가능합니다.";
        checkArray[0] = false;
    }
    
}

//  비밀번호 유효성 검사
function pwCheck(){
    console.log("pwCheck()");
    let pw = document.querySelector("#pwInput").value;
    let pwConfirm = document.querySelector("#pwConfirm").value;
    let pwCheckBox = document.querySelector(".pwCheckBox");

    let pwReg = /^(?=.*[A-Za-z])(?=.*[0-9])[a-zA-Z0-9]{5,30}$/;
    console.log(pwReg.test(pw));
    console.log(pwReg.test(pwConfirm));
    if(pwReg.test(pw)){                 // 비밀번호 정규표현식 검사
        if(pwReg.test(pwConfirm)){    //  비밀번호 확인, 정규표현식 검사
            if(pw == pwConfirm){
                pwCheckBox.innerHTML = "통과";
                checkArray[1] = true;
                return;
            }else{
                pwCheckBox.innerHTML = "비밀번호가 일치하지 않습니다.";
                checkArray[1] = false;
                return;
            }
           
        }
    }
    pwCheckBox.innerHTML = "영문 대소문자와 숫자 조합의 5 ~ 30글자 사이만 가능합니다.";
    checkArray[1] = false;
}   //  method end

//  이름 유효성 검사
function nameCheck(){
    let name = document.querySelector("#nameInput").value;
    let nameCheckBox = document.querySelector(".nameCheckBox");
    let nameReg = /^[가-힣]{2,20}$/;
    if(nameReg.test(name)){
        nameCheckBox.innerHTML = "사용 가능한 이름입니다.";
        checkArray[2] = true;
    }else{
        nameCheckBox.innerHTML = "한글 2 ~ 20 글자로 입력해주세요.";
        checkArray[2] = false;
    }
}

//  전화번호 유효성 검사
function phoneCheck(){
    let phone = document.querySelector("#phoneInput").value;
    let phoneCheckBox = document.querySelector(".phoneCheckBox");
    //  000-0000-0000 or 00-000-0000
    let phoneReg = /^([0-9]{2,3})+[-]+([0-9]{3,4})+[-]+([0-9]{4})$/;
    if(phoneReg.test(phone)){
        phoneCheckBox.innerHTML = "사용 가능한 전화번호입니다.";
        checkArray[3] = true;
    }else{
        phoneCheckBox.innerHTML = "000-0000-0000 or 00-000-0000 의 양식을 지켜주세요.";
        checkArray[3] = false;
    }
}

// * 이메일 인증 버튼
let authBtn = document.querySelector(".authBtn");   // 이메일 인증 요청 버튼
let authBox = document.querySelector(".authBox");   // 이메일 인증 구역
let timerInterval = null;


//  이메일 유효성 검사
function emailCheck(){
    //  0. 이메일 인증 버튼 비활성화
    authBtn.disabled = true;
    let email = document.querySelector("#emailInput").value;
    let emailCheckBox = document.querySelector(".emailCheckBox");
    //  @ 앞에 패턴 1개 이상 존재해야 함
        //  ex) papaap@naver.com
        // \. 을 써야 문자 표현으로 사용 가능하다.
    let emailReg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\.[a-zA-Z]+$/;
    if(emailReg.test(email)){
        emailCheckBox.innerHTML = "등록 가능한 이메일입니다.";  // 이메일 중복 검사는 생략

        // 이메일 인증검사
            // 1. 인증 버튼 활성화
            authBtn.disabled = false;

    }else{
        emailCheckBox.innerHTML = "id@도메인주소 양식을 지켜주세요";
    }
}

//  인증 요청 버튼
function doAuth(){
    console.log("doAuth()");
    $.ajax({
        async : false, // 동기식
        method : 'get',
        url : "/auth/code",
        data : {email : document.querySelector("#emailInput").value},
        success : (result) =>{
            if(result){
                alert("메일로 인증 코드를 전송했습니다.");
            }else{
                alert("코드 전송 실패");
            }
        }
    })

    authBtn.disabled = true;    //  인증번호 버튼 비활성화
    //  1. 인증 번호 입력 구역 구성
    let html = `<span class = "timerBox"> 00 : 00 </span>
                <input type = "text" class = "authCodeInput"/>    
                <button type = "button" class = "authCodeBtn" onclick = "doAuthCode()"> 인증 </button>
                `;

    //  2. innerHTML
    authBox.innerHTML = html;

    //  3. 타이머 구현
    let timer = 180;    // 타이머 시간 설정(초)

    //  4. 인터벌 (JS 라이브러리) : 특정 주기에 따라 함수를 실행
        //  setInterval ( 함수 정의, ms)
        //  parseInt() : 정수로 타입 변환(소수점 자르기)
    timerInterval = setInterval(()=>{
        //  4-1. 분, 초 계산
        let m = parseInt(timer / 60);   // 분
        let s = parseInt(timer % 60);   // 초
        
        //  4-2. 두 자릿수 표현
        m = m < 10? "0" + m : m;    //  만약 m(분)이 10보다 작다면 "0" 붙이기
        s = s < 10? "0" + s : s;    //  만약 s(초)가 10보다 작다면 "0" 붙이기

        //  4-3. 분, 초 출력
        document.querySelector(".timerBox").innerHTML = `${m} : ${s}`;

        timer--;
        console.log(timer);

        //  4-4. 만약 타이머가 -1 이면 종료
        if(timer < 0){
                clearInterval(timerInterval);   // 해당 인터벌 종료
                authBox.innerHTML = "다시 인증 요청 해주세요.";
                checkArray[4] = false;
                authBtn.disabled = false;
            }
    },1000) // 인터벌 end
    

} // 함수 end

//  인증 코드 인증
function doAuthCode(){
    //  1. 입력한 입력 번호 가져오기
    let authCodeInput = document.querySelector(".authCodeInput").value;

    //  임의의 인증 번호(JS에서 인증번호를 관리하지 않는 이유 : JS는 클라이언트로부터 오픈 코드이기 때문이다.)
    $.ajax({
        async : false,
        method : 'post',
        url : "/auth/check",
        data : {authCodeInput : authCodeInput},
        success : (result) =>{
            if(result){
                authBox.innerHTML = "인증 성공";
                checkArray[4] = true;
                clearInterval(timerInterval);   // 인터벌 종료
            }else{
                alert("인증번호가 일치하지 않습니다.");
                checkArray[4] = false;
            }
        }
    })
    
    //  만약에 입력한 값이 인증번호와 같다면
    // if(authCodeInput == authCode){
    //     authBox.innerHTML = "인증 성공";
    //     clearInterval(timerInterval);   // 인터벌 종료
    // }else{
    //     alert("인증번호가 일치하지 않습니다.");
    // }
}


// **** 현재 유효성 체크 현황 ****  //
let checkArray = [false, false, false, false, false];
            // 아이디, 비밀번호, 이름, 전화번호, 이메일


function doSignUp(){
    // 유효성 검사 체크
    for(let i = 0; i <checkArray.length; i++){
        if(!checkArray[i]){
            alert("유효하지 않은 정보가 있습니다.!");
            return;
        }
    }

    //  html에서 입력 받은 값을 가져옴
    let id = document.querySelector("#idInput").value;
    let pw = document.querySelector("#pwInput").value;
    let name = document.querySelector("#nameInput").value;
    let email = document.querySelector("#emailInput").value;
    let phone = document.querySelector("#phoneInput").value;
    $.ajax({
        method : 'post',                //  method는 post 방식
        url : '/member/signup',         //  url은 /member/signup
        data : {id : id, pw : pw, name : name, email : email, phone : phone},   //  url에 보낼 키와 값
        success : function response (result){                                   //  응답 받을 것 
            if(result){                                                         //  result가 true라면 회원가입 성공 메시지 출력 후 로그인 화면 이동   
                console.log(result);
                alert("회원가입 성공");
                location.href="/member/login";
            }else{
                alert("회원가입 실패");                                          //  result가 false라면 회원가입 실패 메시지 출력  
            }
        }
    })
}

function doLogIn(){
    //  html에서 입력 받은 값을 가져옴
    let id = document.querySelector("#idInput").value;
    let pw = document.querySelector("#pwInput").value;
    $.ajax({
        method : 'post',            //  method는 post 방식
        url : '/member/login',      //  url은 /member/login
        data : {id : id, pw : pw},  //  url에 보낼 키와 값
        success : function response(result){    //  응답 받을 것
            if(result){                         //  result가 true 라면 로그인 성공 메시지 출력 후 홈 화면 이동
                console.log(result);
                alert("로그인 성공");
                location.href="/";
            }else{
                alert("로그인 실패");           //  result가 false라면 로그인 실패 메시지 출력
            }
        }
    })
}

function idInput(){
    let idInput = document.querySelector("#idSearch");
    let html = `<div>
                    <form>
                        이름 : <input id ="nameInput" type="text"/> <br/>
                        전화번호 : <input id="phoneInput1" type="text"/> <br/>

                        <button onclick ="idSearch()" type ="button"> 아이디 찾기 </button>
                    </form>
                    </div>`;
    idInput.innerHTML = html;
}
function idSearch(){
    let name = document.querySelector("#nameInput").value;
    let phone1 = document.querySelector("#phoneInput1").value;

    $.ajax({
        async: false,
        method : 'post',
        url : '/member/idSearch',
        data : {name : name, phone : phone1},
        success : function response(result){
            console.log(result);
            if(result == ''){
            let idInput = document.querySelector("#idSearch");
            let html = `<div>
                            <form>
                                이름 : <input id ="nameInput" type="text"/> <br/>
                                전화번호 : <input id="phoneInput1" type="text"/> <br/>

                                <button onclick ="idSearch()" type ="button"> 아이디 찾기 </button>
                            </form>
                            <br>
                            <span>
                               찾으시는 아이디가 없거나 이름, 전화번호가 틀렸습니다.
                            <span>
                            </div>`;
            idInput.innerHTML = html;
            }else{
            let idInput = document.querySelector("#idSearch");
            let html = `<div>
                            <form>
                                이름 : <input id ="nameInput" type="text"/> <br/>
                                전화번호 : <input id="phoneInput1" type="text"/> <br/>

                                <button onclick ="idSearch()" type ="button"> 아이디 찾기 </button>
                            </form>
                            <br>
                            <span>
                               아이디는 [${result}] 입니다.
                            <span>
                            </div>`;
            idInput.innerHTML = html;
            }
        }
        
    })
}

function pwInput(){
    let pwInput = document.querySelector("#pwSearch");
    let html = `<div>
                    <form>
                        아이디 : <input id ="idInput1" type="text"/> <br/>
                        전화번호 : <input id="phoneInput2" type="text"/> <br/>

                        <button onclick ="pwSearch()" type ="button"> 비밀번호 찾기 </button>
                    </form>
                    </div>`;
    pwInput.innerHTML = html;
}

function pwSearch(){
    let id = document.querySelector("#idInput1").value;
    let phone2 = document.querySelector("#phoneInput2").value;

    $.ajax({
        async: false,
        method : 'post',
        url : '/member/pwSearch',
        data : {id : id, phone : phone2},
        success : function response(result){
            console.log(result);
            if(result == ''){ 
            let pwInput = document.querySelector("#pwSearch");
            let html = `<div>
                            <form>
                                아이디 : <input id ="idInput1" type="text"/> <br/>
                                전화번호 : <input id="phoneInput2" type="text"/> <br/>

                                <button onclick ="pwSearch()" type ="button"> 비밀번호 찾기 </button>
                            </form>
                            <br/>
                            <span>
                               찾으시는 비밀번호가 없거나 아이디, 전화번호가 틀렸습니다.
                            <span>
                            </div>`;
            pwInput.innerHTML = html;
            }else{
            let pwInput = document.querySelector("#pwSearch");
            let html = `<div>
                            <form>
                                아이디 : <input id ="idInput1" type="text"/> <br/>
                                전화번호 : <input id="phoneInput2" type="text"/> <br/>

                                <button onclick ="pwSearch()" type ="button"> 비밀번호 찾기 </button>
                            </form>
                            <br/>
                            <span>
                               비밀번호는 [${result}]입니다.
                            <span>
                            </div>`;
            pwInput.innerHTML = html;
            }
        }
        
    })
}

