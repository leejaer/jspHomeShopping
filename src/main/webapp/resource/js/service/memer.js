let memberService ={
	write : function(replyVO){
		$.ajax({
			type: 'post',
			url:`${contextPath}/member/join`,
			data: replyVO,
			success: function(result){
				$('#feedback').find('.modal.body').html(result);	
				$('#feedback').modal('show');	
			},
			error: function(){
				alert('댓글 등록 에러')
			}
		});
	}
}

$(function(){
	$('.memberJoin').on('click',function(){
		let id =$('.id').val()
		let name=$('.name').val()
		let pwd=$('.pwd').val()
		let addr=$('.addr').val()
		let tel=$('.tel').val()

		let memberVO={
			id:id,
			name:name,
			pwd:pwd,
			addr:addr,
			tel:tel
		}
		console.log(memberVO)
		memberService.write(memberVO);
	
	})
});