var old_count = 1, young_count = 0;

function changeCount(type, val){
	console.log(type, val);
	if(type == "old"){
		if(old_count + val >= 1 && old_count + val <= 5) {
			old_count+=val;	
			$("#old_count").html(old_count);
		}	

	}
	if(type == "young"){
		if(young_count + val >= 0 && young_count + val <= 5) {
			young_count+=val;		
			$("#young_count").html(young_count);
		}
	}
}

$('.from_point').focusin(function() {
	$("#predict_input").show();
	$("#predict_input_to").hide();
});

$('.from_point').on('input', function() {
	$("#predict_input").css("visibility","visible");
	var text = $(this).val();
	$.ajax({
		url: "/predinput",
		method: "POST",
		data: {text:text},
		success: function(data) {
			$(".predict_val").remove();
			for(let i = 0; i < data.length; ++i){
				$('#predict_input').append('<div class="predict_val">'+data[i].name+'</div>');

				$("body").on("click",".predict_val",function(){
					$(".from_point").val($(this).html());
					$("#predict_input").hide();
				});

			}
		}
	});
});

	//====================================================

	$('.to_point').focusin(function() {
		$("#predict_input_to").show();
		$("#predict_input").hide();

	});

	$('.to_point').on('input', function() {

		$("#predict_input_to").css("visibility","visible");
		var text = $(this).val();

		$.ajax({
			url: "/predinput",
			method: "POST",
			data: {text:text},
			success: function(data) {
				$(".predict_val_to").remove();
				for(let i = 0; i < data.length; ++i){
					$('#predict_input_to').append('<div class="predict_val_to">'+data[i].name+'</div>');

					$("body").on("click",".predict_val_to",function(){
						$(".to_point").val($(this).html());
						$("#predict_input_to").hide();
					});

				}
			}
		});
	});

	function search(){
		var from_point_val = $(".from_point").val();
		var to_point_val = $(".to_point").val();
		var date_val = $(".date").val();

		// $.ajax({
		// 	url: "/search",
		// 	method: "POST",
		// 	data: {from:from_point_val, to:to_point_val, date:date_val, old:old_count,young:young_count},
		// 	success: function(data) {
		// 		console.log(data);
		// 	}
		// });

		window.location.href = "/search?from="+from_point_val+"&to="+to_point_val+"&date="+date_val+"&old="+old_count+"&young="+young_count;
	}