
console.log("all.js");

allPrint();
function allPrint(){
    let board = document.querySelector(".board");
    let html = '';
    $.ajax({
        method : 'get',
        url : "/board/call",
        success : function response (result){console.log(result)
            result.forEach(r => {
                html += `
                <tr> <th> ${r.bno}  </th> <th><a href="/board/detail?bno=${r.bno}">${r.btitle}</a>  </th> <th> ${r.id}  </th> <th> ${r.bdate}  </th> <th> ${r.bview}  </th>  </tr>
                `;
            });
            board.innerHTML = html;
        }
    })
}