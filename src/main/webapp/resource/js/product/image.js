$(function() {
	$('input[type="file"]').on('change',function(){
		console.log();
		if(this.files[0]){
			let reader =new FileReader();//파일 읽기 객체
			reader.onload =function(e){//파일을 읽으면 이벤트 발생
				let value =e.target.result;
				if(value.startsWith("data:image/")){
					let imgTag =`<img src="${value}" alt="다운로드이미지">`;
					$('.preview').html(imgTag);
				}else{//이미지가 아닌경우
					$('.preview').html('');
					alert('이미지 파일만 등록하세요');
					$('input[name="imageFileName"]').val('');
				}
			}
			reader.readAsDataURL(this.files[0]);//파일 읽기 메소드 호출
		}
	});
	
});
