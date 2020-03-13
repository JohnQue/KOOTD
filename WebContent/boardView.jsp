<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>KOOTD : Kyonggi Outfit Of The Day</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<link rel="stylesheet" href="css/comment.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.5.0/css/all.css' integrity='sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU' crossorigin='anonymous'>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" id="banner">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="main-header" href="main.do">KOOTD</a>
		</div>
		<div class="container-fluid" id="bs-example-navbar-collapse-1">
			<c:choose>
				<c:when test="${info.userID eq null}">
					<c:set var="messageContent" value="로그인을 먼저 하십시오." scope="session" />
					<c:set var="messageType" value="오류 메시지" scope="session" />
					<c:set var="homepage" value="boardView.do?boardNum=${dto.boardNum}" scope="session" />
					<c:redirect url="login.do" />
				</c:when>
				<c:otherwise>
					<div class="nav navbar-nav navbar-right">
						<form method="post" action="myPage.do">
							<input id="login-button" type="submit" value="${info.userName}님">
						</form>
					</div>
				</c:otherwise>
			</c:choose>
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
							<a href="board.do?boardKind=funKing">도전! 개성남</a>
							<a href="board.do?boardKind=funQueen">도전! 개성녀</a>
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
	<div class="container" style="margin-top:150px;">
		<table class="table table table-hover" style="text-align: center; border: none">
			<tbody>
				<tr>
					<td style="width: 5%;"><h5>제목</h5></td>
					<td style="width: 60%"><h5>${dto.boardTitle}</h5></td>
				</tr>
				<tr>
					<td style="width: 5%;"><h5>내용</h5></td>
					<td style="width: 60%">
						<h5>${dto.boardContent}</h5>
						<c:if test="${dto.cap ne null}">
							<h5>모자 : ${dto.cap}</h5>
						</c:if>
						<c:if test="${dto.shirts ne null}">
							<h5>상의 : ${dto.shirts}</h5>
						</c:if>
						<c:if test="${dto.jacket ne null}">
							<h5>외투 : ${dto.jacket}</h5>
						</c:if>
						<c:if test="${dto.pants ne null}">
							<h5>하의 : ${dto.pants}</h5>
						</c:if>
						<c:if test="${dto.shoes ne null}">
							<h5>신발 : ${dto.shoes}</h5>
						</c:if>
						<c:if test="${dto.bag ne null}">
							<h5>가방 : ${dto.bag}</h5>
						</c:if>
						<c:if test="${dto.acce ne null}">
							<h5>액세서리 : ${dto.acce}</h5>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="container" style="text-align:center;">
			<a class="like-button" onclick="clickLike()"><img src="upload/KakaoTalk_20181211_132938342.png"></a>
			<a class="like-button" onclick="clickDislike()"><img src="upload/KakaoTalk_20181211_132938677.png"></a>
		</div>
		<div class="nav navbar-nav navbar-right">
				<c:if test="${dto.boardUserID eq info.userID}">
					<input class="board-button" type="submit" value="수정" onclick="location.href='modifyView.do?boardNum=${dto.boardNum}'">
					<input class="board-button" type="button" value="삭제" onclick="confirmCheck()">
				</c:if>
			<input class="board-button" type="submit" value="목록" onclick="location.href='board.do?boardKind=${dto.boardKind}'">
		</div>
		<div><h5 style="color:red;">현재 추천수 : ${dto.boardLike }</h5></div>
	</div>
	<div class="container" id="comment-box">
  		<form action="comment.do" method="post">
  			<input type="hidden" name="boardNum" value="${dto.boardNum}">
    		<input type="text" name="userComment" onkeydown="if(event.keyCode==13) {return false;}">
    		<input id="sb" type="submit" value="댓글달기">
  		</form>
  		
  		<c:forEach items="${comList}" var="com">
  			<div id="personal-comment">
  				<div id="${com.commentNum}">
		  			<div class="clearfix">
		  				<div class="box">
		  					<h5>
		  						<c:forEach begin="1" end="${com.commentIndent}">-</c:forEach>
									${com.userName}/${com.userMajor}
							</h5>
						</div>
		  				<div class="box" style="text-align: right;">${com.commentDate}</div>
		  			</div>
		  			<div class="clearfix">
		  				<div class="box"></div>
			  				<div class="box" style="text-align: right;">
				  				<c:if test="${com.userID eq info.userID}">
				  					<div class="comment-button">
				  						<a onclick="removeComment(${com.commentNum})"><i class='far fa-trash-alt' style='font-style:13px;'></i></a>
				  					</div>
				  					<div class="comment-button">
				  						<a onclick="modifyComment(${com.commentNum})"><i class='far fa-edit' style='font-style:13px;'></i></a>
				  					</div>
				  				</c:if>
					  				<div class="comment-button">
					  					<a onclick="replyComment(${com.commentNum})"><i class='fas fa-reply' style='font-style:13px;'></i></a>
					  				</div>
				  			</div>
			  		</div>
				  		${com.userComment}
			  	</div>
  			</div>
  			<div id="replyHere"></div>
  		</c:forEach>
	</div>
	<script>
		function confirmCheck(){
			var check = confirm("정말로 삭제하시겠습니까? 삭제시 되돌릴 수 없습니다.");
			if(check == true){
				alert("삭제되었습니다!");
				location.href="remove.do?boardNum=${dto.boardNum}&boardKind=${dto.boardKind}";
			}
		}
		function removeComment(value){
			var check = confirm("정말로 삭제하시겠습니까? 삭제시 되돌릴 수 없습니다.");
			if(check == true){
				alert("삭제되었습니다!");
				location.href="removeComment.do?boardNum=${dto.boardNum}&commentNum="+value;
			}
		}
		function modifyComment(value){
			var xhttp;
			xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function(){
				if(xhttp.readyState == 4 && xhttp.status == 200){
					document.getElementById(value).innerHTML = this.responseText;
				}
			};
			xhttp.open("POST", "CommentGetServlet?boardNum=${dto.boardNum}&commentNum="+value, true);
			xhttp.send();
		}
		function replyComment(value){
			var xhttp;
			xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function(){
				if(xhttp.readyState == 4 && xhttp.status == 200){
					document.getElementById("replyHere").innerHTML = this.responseText;
				}
			};
			xhttp.open("POST", "CommentReplyServlet?boardNum=${dto.boardNum}&commentNum="+value, true);
			xhttp.send();
		}
		function clickLike(){
			location.href="clickLike.do?userID=${info.userID}&boardNum=${dto.boardNum}&ownerID=${dto.boardUserID}";
		}
		function clickDislike(){
			location.href="clickDislike.do?userID=${info.userID}&boardNum=${dto.boardNum}&ownerID=${dto.boardUserID}";
		}
	</script>
	<c:if test="${messageContent ne null}">
		<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="vertical-alignment-helper">
				<div class="modal-dialog vertical-align-center">
					<div class="modal-content <c:choose>
					<c:when test='${messageType eq "오류 메시지"}'>
						<c:out value='panel-warning' />
					</c:when>
					<c:otherwise>
							<c:out value='panel-success' />
					</c:otherwise>
					</c:choose>">
						<div class="modal-header panel-heading">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times</span>
								<span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">
								<c:out value="${messageType}" />
							</h4>
						</div>
						<div class="modal-body">
							<c:out value="${messageContent}" />
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script>
			$('#messageModal').modal("show");
		</script>
		<c:remove var="messageContent" />
		<c:remove var="messageType" />
	</c:if>
</body>
</html>