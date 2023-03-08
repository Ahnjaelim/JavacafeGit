window.addEventListener('load', function(){
	 
	$.ajax({
		type : "post",
		url :"saleslist",
		data:{
			
		},		
		success : function(data){
			 
			console.log(data);
			
			
		},
		error : function(){
			alert('불러오기실패')
		}			
	})
}); //window.onload end