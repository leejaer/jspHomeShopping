$(function(){
		let userIdCheck = RegExp(/^[A-Za-z0-9_\-]{5,20}$/);
		let passwdCheck = RegExp(/^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/);
	//	let passwdCheck = RegExp(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/);
		let nameCheck = RegExp(/^[가-힣]{2,6}$/);
	//	let nickNameCheck = RegExp(/^[가-힣a-zA-Z0-9]{2,10}$/);
		let emailCheck = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
		let birthdayCheck = RegExp(/^(19|20)[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/);
		let phonNumberCheck = RegExp(/^01[0179][0-9]{7,8}$/);

	$('.memberJoin').on('click',function(){
		let id =$.trim($('.id').val())
		let name=$.trim($('.name').val())
		let pwd=$.trim($('.pwd').val())
		let pwdCheck=$.trim($('.pwdCheck').val())
		let addr=$.trim($('.addr').val())
		let tel=$.trim($('.tel').val())
		let email=$.trim($('.email').val())
		
		if(userIdCheck.test(id)){
			if(pwd==pwdCheck && passwdCheck.test(pwd)){
				if(nameCheck.test(name)){
					let memberVO={
						id:id,
						name:name,
						pwd:pwd,
						addr:addr,
						tel:tel,
						email:email
					}
					memberService.write(memberVO);
				}else{
					alert('5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.')					
				}
			}else{
			alert('8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.')
			}
		}else{
			alert('아이디 너무 짧거나 잘못된 입력입니다')
		}
	}),

	//keyup
	$('.pwdCheck').on('keyup',function(){
		let pwdCheck=$('.pwdCheck').val()
		let pwd=$('.pwd').val()
		if(passwdCheck.test(pwd)){
			if(pwdCheck == pwd){
				$('.pwdChecked').val('가능한 비밀번호입니다')
			}else{
				$('.pwdChecked').val('일치하지 않습니다')
			}
		}else{
				$('.pwdChecked').val('8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.')
		}
	})
	
	$('.id').on('keyup',function(e){
		
		e.preventDefault();
		let id=$('.id').val()
		if(userIdCheck.test(id)){
				$('.idChecked').val('가능한 아이디')
		}else{
				$('.idChecked').val('불가능')
		}
	})
	
	
});

let memberService ={
	write : function(replyVO){
		$.ajax({
			type: 'post',
			url:`${contextPath}/member/join`,
			data: replyVO,
			success: function(result){
				alert(result);
				location.href = '../main/';
			},
			error: function(){
				alert('에러')
			}
		});
	}
	

}

