window.addEventListener('load', function(){
	
$.ajax({
    url: 'saleslist1',
    type: 'GET',
    success: function onData (data) {
        console.log(data);
    },
    error: function onError (error) {
        console.error(error);
    }
});

}); //window.onload end