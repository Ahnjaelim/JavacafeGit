 window.addEventListener('load', function(){

//등록	
const formObj = document.querySelector("#f1")

	
document.querySelector(".regbtn").addEventListener("click", function(e){
	e.preventDefault()
	e.stopPropagation()
	
	const id = document.getElementById('id').value;
	const pw = document.getElementById('pw').value;
	
	if(id == ""){
		alert('ID를 입력하세요')
		document.getElementById('id').focus();
		event.preventDefault();
	}
	if(pw ==""){
		alert('PW를 입력하세요')
		document.getElementById('pw').focus();
		event.preventDefault();
		
	} 
	
	if(id != "" && pw != ""){
	formObj.action = `/admin/register`
	formObj.method = 'post'
	formObj.submit()
	}
},false)

 
 
});