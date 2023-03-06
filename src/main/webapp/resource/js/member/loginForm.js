$(function(){

	$('.LoginBtn').on('click',function(){
		let id =$('.loginId').val()
		let pwd=$('.loginPwd').val()
	

		let memberVO={
			id:id,
			pwd:pwd,
		}
		console.log(memberVO)
		memberService.login(memberVO);
	
	})
});

let memberService ={
	login : function(replyVO){
		$.ajax({
			type: 'post',
			url:`${contextPath}/member/login`,
			data: replyVO,
			success: function(result){
				if(result=='실패'){
				alert('아이디 또는 비밀번호가 일치하지 않습니다')
				location.reload;	
				}else{
				alert(result)
				location.href = '../main/';}
			},
			error: function(){
				alert('에러')
			}
		});
	}
}