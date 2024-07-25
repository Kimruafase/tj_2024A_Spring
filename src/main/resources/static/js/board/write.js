
console.log('write.js');

function _write(){

    let title = document.querySelector('#title').value;
    let content = document.querySelector('#content').value;
    let category = document.querySelector('#category').value;

        $.ajax({
            method: "post" ,
            url: "/board/post" ,
            data : JSON.stringify({btitle : title , bcontent : content , bcno : category}) ,
            contentType : "application/json",
            success:(result)=>{
                 console.log(result);
                 if(result){alert('글쓰기 성공')
                 }else{alert('글쓰기 실패')}

            } // success method end
    })
}