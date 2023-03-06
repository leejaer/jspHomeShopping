$(function(){
	$('.modifyMod').on('click',function(){
		let mno =$('.modMno').val()
		let addr=$('.modAddr').val()
		let email=$('.modEmail').val()
		let tel=$('.modTell').val()
		let pwd=$('.modPwd').val()

		let memberVO={
			mno:mno,
			pwd:pwd,
			email:email,
			addr:addr,
			tel:tel
		}
		member.update(memberVO);
	
	})
});

let member ={
	update : function(memberVO){
		$.ajax({
			type: 'post',
			url:`${contextPath}/member/update`,
			data: memberVO,
			success: function(result){
				alert(result);
			},
			error: function(){
				alert('에러')
			}
		});
	}
}