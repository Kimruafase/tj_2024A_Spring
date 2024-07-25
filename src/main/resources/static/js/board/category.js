console.log('category.js');

category();
function category(){

let category= document.querySelector('#category');
let html="";

$.ajax({
method: "get" ,
url: "/board/category" ,
success:(result)=>{
     console.log(result);

    for(let i=0; i<result.length; i++){
      console.log(result[i].bcno);
       html += `<option value="${result[i].bcno}">${result[i].bcname}</option>`
    }
        category.innerHTML = html;



} // success method end


})


}