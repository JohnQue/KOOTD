<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  	<title>KOOTD : Kyonggi Outfit Of The Day</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	<link rel="stylesheet" href="css/mypage-button.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/joinCheck.js" charset='UTF-8'></script>
	<script src="js/effect.js"></script>
  
  
  
</head>
<body>
	<c:set var="info" value="${info}" />
	<nav class="navbar navbar-default navbar-fixed-top" id="banner">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="main-header" href="main.do">KOOTD</a>
		</div>
		<div class="container-fluid" id="bs-example-navbar-collapse-1">
			<c:choose>
				<c:when test="${info.userID eq null }">
					<c:set var="messageContent" value="로그인을 먼저 해야합니다." scope="session" />
					<c:set var="messageType" value="오류 메시지"  scope="session" />
					<c:redirect url="login.do" />
				</c:when>
				<c:otherwise>
					<c:remove var="messageContent" />
					<c:remove var="messageType" />	
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
	<div class="container" style="margin-top:200px;" >
		<form action="userModify.do" method="post">
			<button id="select-button1" value="modify" style="background-color: green"><span>회원정보 수정</span></button>
		</form>
		<form action="logout.do" method="post">
			<button id="select-button2" value="logout" style="background-color: skyblue"><span>로그아웃</span></button>
		</form>
	</div>
	
	<!-- 메시지 모달 창 -->
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