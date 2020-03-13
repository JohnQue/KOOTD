// Ajax는 한마디로 지금 내가 있는 홈페이지에서 처리를 도와주니까, 비밀번호가 동일한지 실시간 체크
// 그리고 아이디는 중복확인 누르면 페이지 변경 없이 요 코드들을 돌려서 중복인지 테스트 해줌.
function checkIDequalSNFunction(){
	var userID = $('#userID').val();
	if(parseInt(userID) == NaN || userID.length != 9){
		$('#checkIDequalSNMessage').html('아이디는 학번으로 숫자 9글자여야 합니다.');
		$('#checkIDequalSNMessage').css({"color": "red"});
	}else{
		$('#checkIDequalSNMessage').html('');
	}
}
function passwordCheckFunction(){
	var userPassword1 = $('#userPassword1').val();
	var userPassword2 = $('#userPassword2').val();
	if(userPassword1 != '' && userPassword2 != ''){
		if(userPassword1 != userPassword2){
			$('#passwordCheckMessage').html('비밀번호가 서로 일치하지 않습니다.');
			$('#passwordCheckMessage').css({"color": "red"});
		}else if(userPassword1 == userPassword2){
			$('#passwordCheckMessage').html('비밀번호가 일치합니다.');
			$('#passwordCheckMessage').css({"color": "green"});
		}
	}else{
		$('#passwordCheckMessage').html('');
	}
}
function showMajor(str) {
	  var xhttp;
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      document.getElementById("selMajor").innerHTML = this.responseText;
	    }
	  };
	  xhttp.open("GET", "ChooseMajor?univ="+str, true);
	  xhttp.send();
}