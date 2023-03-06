$(function() {
	let detail =$('.productDetail');
	
	$('.productDel').on('click',function(){
		detail.attr({
		"action":`${contextPath}/product/del`,
		"method": "post"})
		.submit();
	})
	
	$('.productMod').on('click',function(){
		detail.attr({
			"action":`${contextPath}/product/mod`,
			"method": "post"})
		.submit();
	})
	
	$('.repldel').on('click',function(e){
		e.preventDefault
		let rno = $(this).closest('tr').find('.replrno').val();
		let pno = $('.pnoClass').val();
		
		repleService.del(pno,rno);
		
	})
	
	$('.replMod').on('click',function(){
		
		if('readonly' == $(this).closest('tr').find('.replcontent').attr('readonly')){
			$(this).closest('tr').find('.replcontent').attr('readonly',false).val();
		}else{
			let content = $(this).closest('tr').find('.replcontent').val();
			let rno = $(this).closest('tr').find('.replrno').val();
			$(this).closest('tr').find('.replcontent').val('');				
			$(this).closest('tr').find('.replcontent').attr('readonly',true).val();
			let pno = $('.pnoClass').val();
			alert(content)
			let vo={
				content:content,
				rno:rno
			}
			repleService.mod(vo,pno);
			
		}
		
		
		//prop('hidden', false);//.removeAttr('hidden');//
		;
	})

	
	
});

let repleService ={
	del : function(pno,rno){
		$.ajax({
			type: 'post',
			url:`${contextPath}/repl/del`,
			data: {rno:rno},
			success: function(result){
				alert(result);
				location.href = 'detail?pno='+pno;	
			},
			error: function(){
				alert('댓글 등록 에러')
			}
		});
	},
	
	
	mod : function(vo,pno){
		$.ajax({
			type: 'post',
			url:`${contextPath}/repl/mod`,
			data: vo,
			success: function(result){
				alert(result);
				location.href = 'detail?pno='+pno;	
			},
			error: function(){
				alert('댓글 등록 에러')
			}
		});
	}
}
