window.addEventListener('load', function() {


	// shop list ajax로 받기
	totalCount = 0;
	cnt = 1;
	var data;
	var recipedata;
	$.ajax({
		type: "post",
		url: "demandlist",
		async: false,
		dataType: "json",

		success: function(list) {
			data = list; //데이터 차트데이터로 뿌려주기		 
		},
		error: function() {
			alert('error');
		}
	}) //ajax end

	//=================recipe목록 불러오기
	$.ajax({
		type: "post",
		url: "recipelist",
		async: false,
		dataType: "json",
		success: function(result) {
			recipedata = result;
		},
		error: function() {
			alert('ajax레시피불러오기 실패')
		}
	})//recipe ajax end


	// =================================================

	var k = 0; var s = 0; var num = 1;

	arrM = new Array(); //chart x축 (음료명 나열하기)
	for (var i = 0; i <= recipedata.length - 1; i++) {
		arrM[k] = num + ". " + recipedata[i].rname;	//rno 빠른순서대로 들어감 
		k++; num++;
	}

	var test = 0;
	count = new Array();
	arrS = new Array(); // chart 그래프 (주문내역 갯수확인)
	for (var i = 0; i <= recipedata.length - 1; i++) {
		for (var j = 0; j <= data.length - 1; j++) {
			if (recipedata[i].rno == data[j].rno) {
				test += 2 ;
				
				console.log(i + "번째 test : " + test)
				
			}
		}
		count[i] = test;
		test = 0;

	}


	var context = document.getElementById('demandChart').getContext('2d');
	var demandChart = new Chart(context, {
		type: 'bar', // 차트의 형태
		data: { // 차트에 들어갈 데이터
			labels: //x 축 
				arrM,
			datasets: [
				{ //데이터
					label: 'test1', //차트 제목
					fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
					data: arrS,
					backgroundColor: [
						//색상
						'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)',
						'rgba(255, 206, 86, 0.2)',
						'rgba(75, 192, 192, 0.2)',
						'rgba(153, 102, 255, 0.2)',
						'rgba(255, 159, 64, 0.2)'
					],
					borderColor: [
						//경계선 색상
						'rgba(255, 99, 132, 1)',
						'rgba(54, 162, 235, 1)',
						'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)',
						'rgba(153, 102, 255, 1)',
						'rgba(255, 159, 64, 1)'
					],
					borderWidth: 1 //경계선 굵기
				}/* ,
                        {
                            label: 'test2',
                            fill: false,
                            data: [
                                8, 34, 12, 24
                            ],
                            backgroundColor: 'rgb(157, 109, 12)',
                            borderColor: 'rgb(157, 109, 12)'
                        } */
			]
		},
		options: {
			scales: {
				yAxes: [
					{
						ticks: {
							beginAtZero: true
						}
					}
				]
			}
		}
	});


})