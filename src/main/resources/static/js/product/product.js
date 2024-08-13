console.log("product.js");

productRead();

function onRegister() {
    console.log("onRegister()");

    //  대용량 첨부파일 보내기
    //  1. 폼 가져오기
    const productForm = document.querySelector("#productForm");
    console.log(productForm)
    //  2. 폼 데이터를 바이트(binary)로 변환, 첨부파일은 JSON, TEXT(문자)형식으로 보낼 수 없다.
    const productFormData = new FormData(productForm);
    console.log(productFormData)

    //  3. ajax 이용한 데이터 전송
    $.ajax({
        async: false,
        method: "post",
        url: "/product/register",
        data: productFormData,
        contentType: false,     // ajax에서 멀티 파트 타입 전송 방법
        processData: false,     // ajax에서 멀티 파트 타입 전송 방법
        success: r => {
            console.log(r);
        },
        error: e => {
            console.log(e);
        }
    })

}

function productRead() {
    let productArea = document.querySelector("#productArea");
    let html = ``;
    $.ajax({
        async: false,
        method: "get",
        url: "/product/read",
        success: function response(result) {
            console.log(result);
            result.forEach(r => {
                html += `<div>
                            제품번호 : ${r.pno}
                        </div>
                        <div>
                           제품명 : ${r.pname}
                        </div>
                        <div>
                           제품 정보 : ${r.pinformation}
                        </div>
                        <div>
                           제품 가격 : ${r.pprice}
                        </div>
                        <div class = "productBox">`
                    ;
                // 여러개의 이미지 파일
                r.fileNames.forEach(imgName => {
                    // 업로드된 이미지 파일명을 서버로 요청을 해서 응답 받아 img 마크업 src 속성에 대입
                    html += `
                           <img style="width : 100px;" src = "/upload/${imgName}"/>`
                })
                html += `
                        </div>
                        <div>
                           등록일 : ${r.pdate}
                        </div>
                        <div>
                            조회수 : ${r.pview}
                        </div>
                        <br>
                                `;
            });

            productArea.innerHTML = html;
        }
    })
}