window.addEventListener('load', function(){
	 
	$.ajax({
		type : "post",
		url :"saleslist",
		data:{
			
		},		
		success : function(data){
		str = '<div>';
			$.each(data , function(i){
				str += '<div>' + data[i].year +"."+ data[i].month+ '</div>';		
				str += '</div>';
			});
			$('.sales-head').append(str);
			
			str1 ='<div>';
			$.each(data, function(i){
				str1 += '<div>' + data[i].sales +"원"+ '</div>';
				str1 += '</div>';						
			});
			$('.sales-body').append(str1);
			
		},
		
		error : function() {
			//alert('error');
		}
	})
}); //window.onload end