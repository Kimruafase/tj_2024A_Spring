
console.log("all.js");


//  매개변수
//  page : 페이지 번호 -> 초기값 1, 초기 페이지를 보여주기 위해서
//  bcno : 카테고리 번호 -> 초기값 0, 카테고리 전체를 보여주기 위해서


allPrint(1);
function allPrint(page, bcno) {
    let board = document.querySelector(".board");
    let html = '';
    let boardPageDto = {};   // ajax로 부터 응답받은 객체를 저장하는 변수
    $.ajax({
        async: false,
        method: 'get',
        url: "/board/call",
        data: { page: page, bcno: bcno },
        success: function response(result) {
            console.log(result)
            boardPageDto = result;
            let list = boardPageDto.data;
            list.forEach(r => {
                html += `
                <tr> <th> ${r.bno}  </th> <th><a href="/board/detail?bno=${r.bno}">${r.btitle}</a>  </th> <th> ${r.id}  </th> <th> ${r.bdate}  </th> <th> ${r.bview}  </th>  </tr>
                `;
            });
            board.innerHTML = html;


        }


    })
    // 페이지네이션 (페이지 버튼) 구성
    // 어디에
    let pagination = document.querySelector(".pagination")

    // 무엇을
    let html1 = ``;
    // 이전 버튼, page : 현재 함수의 매개변수이면서 현재 페이지 번호를 의미, 현재 페이지 - 1 현재 페이지가 1 미만일 경우 1로 떨어지면 안 되기 때문에 1로 고정함
    html1 += `<li class="page-item"><a class="page-link" onclick="allPrint(${page - 1 < 1 ? 1 : page - 1})">Previous</a></li>`;

    // 페이지 버튼
    // 페이지 마다 시작 버튼 수 : startBtn
    let startBtn = boardPageDto.startBtn;
    // 페이지 마다 끝 버튼 수 : endBtn
    let endBtn = boardPageDto.endBtn;
    // 최대 페이지 수 : totalPage
    let totalPage = boardPageDto.totalPage;
    console.log(totalPage);
    for (let i = startBtn; i <= endBtn; i++) {
        html1 += `<li class="page-item"><a class="page-link ${i == page ? 'active' : ''}" onclick="allPrint(${i})">${i}</a></li>`;
    }

    // 다음 버튼, page : 현재 함수의 매개변수이면서 현재 페이지 번호를 의미, 현재 페이지 + 1
    html1 += `<li class="page-item"><a class="page-link" onclick="allPrint(${page + 1 > totalPage ? totalPage : page + 1})">Next</a></li>`;

    // 출력
    pagination.innerHTML = html1;


}

