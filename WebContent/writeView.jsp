<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>KOOTD : Kyonggi Outfit Of The Day</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="css/custom.css">
	
	<!-- summernote의 스타일시트와 자바스크립트을 사용하기 위한 선언 -->
	<link href="sumnote/summernote.css" rel="stylesheet">
	<script src="sumnote/summernote.js"></script>
	<!-- include summernote-ko-KR -->
	<script src="sumnote/lang/summernote-ko-KR.js"></script>
	<script>
	function sendFile(file, editor, welEditable) {
        // 파일 전송을 위한 폼생성
 		data = new FormData();
 	    data.append("uploadFile", file);
 	    $.ajax({ // ajax를 통해 파일 업로드 처리
 	        data : data,
 	        type : "POST",
 	        url : "./ImageUploadServlet",
 	        cache : false,
 	        contentType : false,
 	        processData : false,
 	        enctype: 'multipart/form-data',
 	        success : function(data) { // 처리가 성공할 경우
                // 에디터에 이미지 출력
                console.log(data.url);
 	        	$(editor).summernote('insertImage', data.url);
 	        }
 	    });
 	}
	</script>
</head>
<body>
        <script>
            $(document).ready(function() {
                $('#summernote').summernote({ // summernote를 사용하기 위한 선언
                	toolbar: [
                		    // [groupName, [list of button]]
                		    ['style', ['bold', 'italic', 'underline', 'clear']],
                		    ['fontsize', ['fontsize']],
                		    ['color', ['color']],
                		    ['insert', ['picture', 'link']]
                	],
                	lang: 'ko-KR',
                    height: 600,
                    callbacks: {
	                	onImageUpload: function(files, editor, welEditable){
							sendFile(files[0], this, welEditable);
	                	}
                    }
                });
			});
		</script>
	<c:choose>
		<c:when test="${info.userID eq null}">
				<c:set var="messageContent" value="로그인을 먼저 하십시오." scope="session" />
				<c:set var="messageType" value="오류 메시지" scope="session" />
				<c:set var="homepage" value="writeView.do?boardKind=${boardKind}" scope="session" />
				<c:redirect url="login.do" />
		</c:when>
	</c:choose>
	<nav class="navbar navbar-inverse navbar-fixed-top" id="banner">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="main-header" href="main.jsp">KOOTD</a>
		</div>
		<div class="container-fluid" id="bs-example-navbar-collapse-1">
					<div class="nav navbar-nav navbar-right">
						<div class="dropdown">
							<button class="dropbtn">
								마이페이지<span class="caret"></span>
							</button>
							<div class="dropdown-content">
								<a href="userModify.jsp">정보수정</a>
								<a href="logout.jsp">로그아웃</a>
							</div>
						</div>
					</div>
				<div class="nav navbar-nav navbar-right">
					<div class="dropdown">
						<button class="dropbtn">
							이 달의<span class="caret"></span>
						</button>
						<div class="dropdown-content">
							<a href="board.do?boardKind=best">패셔니스타</a>
							<a href="board.do?boardKind=funBest">박장대소룩</a>
							<a href="board.do?boardKind=coord">코디네이터</a>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">
							누가누가 잘입나<span class="caret"></span>
						</button>
						<div class="dropdown-content">
							<a href="board.do?boardKind=faKing">도전! 패션킹</a>
							<a href="board.do?boardKind=faQueen">도전! 패션퀸</a>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">
							이게 패션이다<span class="caret"></span>
						</button>
						<div class="dropdown-content">
							<a href="board.do?boardKind=funKing">도전! 웃긴남</a>
							<a href="board.do?boardKind=funQueen">도전! 웃긴녀</a>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">
							이렇게만 입어줘<span class="caret"></span>
						</button>
						<div class="dropdown-content">
							<a href="board.do?boardKind=toMan">남자에게</a>
							<a href="board.do?boardKind=toWoman">여자에게</a>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">
							옷정보<span class="caret"></span>
						</button>
						<div class="dropdown-content">
							<a href="board.do?boardKind=manClothes">남자옷</a>
							<a href="board.do?boardKind=womanClothes">여자옷</a>
						</div>
					</div>					
					<div class="dropdown">
						<button class="dropbtn">
							피부<span class="caret"></span>
						</button>
						<div class="dropdown-content">
							<a href="board.do?boardKind=makeUp">화장품</a>
							<a href="board.do?boardKind=trouble">트러블</a>
						</div>
					</div>
				</div>
			</div>
	</nav>

	<div class="container" style="margin-top:150px; margin-bottom:150px;">
		<form method="post" action="write.do">
			<input type="hidden" name="boardKind" value="${boardKind}">
			<input type="hidden" name="boardUserID" value="${info.userID}">
			<input type="hidden" name="boardUserName" value="${info.userName}">
			<input type="hidden" name="boardUserMajor" value="${info.userMajor}">
			<table class="table table table-hover" style="text-align: center; border: none">
				<tbody>
					<tr>
						<td style="width: 70px;"><h5>제목</h5></td>
						<td><input class="form-control" type="text" id="title" name="title" maxlength="20" placeholder="제목을 입력하세요."></td>
					</tr>
					<tr>
						<td style="width: 70px;"><h5>내용</h5></td>
						<td><textarea id="summernote" name="content"></textarea></td>
					</tr>
				<c:if test="${boardKind ne 'makeUp' and boardKind ne 'trouble' and boardKind ne 'manClothes' and boardKind ne 'womanClothes'}">
					<tr>
						<td style="width: 70px;"><h5>의류</h5></td>
						<td>
							<div style="text-align:center;">
							<div class="style-select">
							모자 : <select onchange="loadDoc(this.value, 'cap')" required>
								<option value="">선택하세요.</option>
								<option value="있음">있음</option>
							    <option value="없음">없음</option>
							</select>
							<span id="cap"></span><br>
							</div>
							<div class="style-select">
							상의 : <select onchange="loadDoc(this.value, 'shirts')" required>
								<option value="">선택하세요.</option>
								<option value="있음">있음</option>
							    <option value="없음">없음</option>
							</select>
							<span id="shirts"></span>
							</div>
							<div class="style-select">
							외투 : <select onchange="loadDoc(this.value, 'jacket')" required>
								<option value="">선택하세요.</option>
								<option value="있음">있음</option>
							    <option value="없음">없음</option>
							</select>
							<span id="jacket"></span>
							</div>
							<div class="style-select">
							하의 : <select onchange="loadDoc(this.value, 'pants')" required>
								<option value="">선택하세요.</option>
								<option value="있음">있음</option>
							    <option value="없음">없음</option>
							</select>
							<span id="pants"></span><br>
							</div>
							<div class="style-select">
							신발 : <select onchange="loadDoc(this.value, 'shoes')" required>
								<option value="">선택하세요.</option>
								<option value="있음">있음</option>
							    <option value="없음">없음</option>
							</select>
							<span id="shoes"></span><br>
							</div>
							<div class="style-select">
							가방 : <select onchange="loadDoc(this.value, 'bag')" required>
								<option value="">선택하세요.</option>
								<option value="있음">있음</option>
							    <option value="없음">없음</option>
							</select>
							<span id="bag"></span><br>
							</div>
							<div class="style-select">
							액세서리 : <select onchange="loadDoc(this.value, 'acce')" required>
								<option value="">선택하세요.</option>
								<option value="있음">있음</option>
							    <option value="없음">없음</option>
							</select>
							<span id="acce"></span><br>
							</div>
							</div>
						</td>
					</tr>
				</c:if>
				</tbody>
			</table>
					<div class="navbar-right">
						<input class="board-button" type="submit" value="올리기">
						<input class="board-button" type="button" value="취소" onclick="history.go('-1')">
					</div>
		</form>
	</div>
	<script>
	function loadDoc(str, str2) {
	 var xhttp;  
	  	if (str == "") {
	    	document.getElementById(str2).innerHTML = "";
	    	return;
	  	}
		if(str == "없음"){
			document.getElementById(str2).innerHTML = "";
			return;
		}
		if(str == "있음"){
			xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
			    if (this.readyState == 4 && this.status == 200) {
			    	document.getElementById(str2).innerHTML = this.responseText;
			    }
			};
		}
	  	xhttp.open("GET", "text.jsp?str2="+str2, true);
	  	xhttp.send();
	}
	</script>
</body>
</html>