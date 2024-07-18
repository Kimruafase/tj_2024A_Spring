console.log("signUp.js");

function doSignUp(){
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

