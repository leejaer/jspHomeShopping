$(function() {
	let detail =$('.basket');
	
	$('.productCancle').on('click',function(){
		detail.attr({
		"action":`${contextPath}/main`,
		"method": "post"})
		.submit();
	})
	
});


