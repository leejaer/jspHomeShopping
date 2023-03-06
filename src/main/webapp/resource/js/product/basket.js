
$(function(){
	$('.getBasket').on('click',function(e){
		e.preventDefault()
		var pno= $('.pnoClass').val();
		var userid=$('.userid').val();
		 
		let productVO={
			userid:userid,
			pno:pno
		}
		pService.basket(productVO);
	
	})
});

let pService ={
	basket : function(productVO){
		$.ajax({
			type: 'post',
			url:`${contextPath}/member/basket`,
			data: productVO,
			success: function(result){
				alert(result);
			},
			error: function(){
				alert('댓글 등록 에러')
			}
		});
	}
}