$(function(){
	let detail =$('.productDetail');

	$('.productBuy').on('click',function(){
		detail.attr({
		"action":`${contextPath}/member/directBuy`,
		"method": "post"})
		.submit();
	})
});
