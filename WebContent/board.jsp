<%@page import="DAO.BoardDAO"%>
<%@page import="java.sql.Timestamp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="DTO.BoardDTO"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>KOOTD : Kyonggi Outfit Of The Day</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<link rel="stylesheet" href="css/winner.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/joinCheck.js" charset='UTF-8'></script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" id="banner">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="main-header" href="main.do">KOOTD</a>
		</div>
		<div class="container-fluid" id="bs-example-navbar-collapse-1">
			<c:choose>
				<c:when test="${info.userID eq null}">
					<div class="nav navbar-nav navbar-right">
						<form method="post" action="login.do?boardKind=${boardKind}">
							<input id="login-button" type="submit" value="로그인">
						</form>
					</div>
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
	<div class="container" style="margin-top: 150px;">
	<c:choose>
		<c:when test="${boardKind ne 'best' and boardKind ne 'funBest' and boardKind ne 'coord'}">
				<div class="navbar navbar-nav navbar-right">
					<form name="search" method="post" action="search.do">
						<input type="hidden" name="boardKind" value="${boardKind}">
						<span class="style-select">
					  		<select name="searchKind">
					      		<option>제목</option>
					   		</select> 
				   		</span>
				   		<input type="text" name="searchKwd"> 
				   		<input type="submit" value="검색" class="search-button">
					</form>
				</div>
				<table class="table table-bordered" style="text-align: center;">
					<thead>
						<tr>
							<th style="width: 50%;"><div class="form-font">제목</div></th>
							<th style="width: 15%;"><div class="form-font">날짜</div></th>
							<th style="width: 5%;"><div class="form-font">추천수</div></th>
							<th style="width: 5%;"><div class="form-font">조회수</div></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${preWinList}" var="win">
						<tr style="color : red; font-weight: bold;">
							<td style="width: 50%"><div class="form-font"><a href="isLogin.do?boardNum=${win.boardNum}" class="winner">${win.boardTitle}</a></div></td>
							<c:set var ="date" value="${win.boardDate}" />
							<td style="width: 15%"><div class="form-font">${fn:substring(date, 0, 11)}&nbsp;${fn:substring(date, 11, 13)}시&nbsp;${fn:substring(date, 14, 16)}분 </div></td>
							<td style="width: 5%"><div class="form-font">${win.boardLike}</div></td>
							<td style="width: 5%"><div class="form-font">${win.boardHit}</div></td>
						</tr>
					</c:forEach>
					<c:forEach items="${boardList}" var="board">
						<tr>
							<td style="width: 50%"><div class="form-font"><a href="isLogin.do?boardNum=${board.boardNum}">${board.boardTitle}</a></div></td>
							<c:set var ="date" value="${board.boardDate}" />
							<td style="width: 15%"><div class="form-font">${fn:substring(date, 0, 11)}&nbsp;${fn:substring(date, 11, 13)}시&nbsp;${fn:substring(date, 14, 16)}분 </div></td>
							<td style="width: 5%"><div class="form-font">${board.boardLike}</div></td>
							<td style="width: 5%"><div class="form-font">${board.boardHit}</div></td>
						</tr>
					</c:forEach>			
					</tbody>
				</table>
				<div class="nav navbar-nav navbar-right">
					<form action="writeView.do">
						<input type="hidden" name="boardKind" value="${boardKind }">
						<input type="submit" value="글쓰기" class="board-button">
					</form>
				</div>
		</c:when>
		<c:otherwise>
				<div class="navbar navbar-nav navbar-right">
					<form name="search" method="post" action="search.do">
						<input type="hidden" name="boardKind" value="${boardKind}">
						<span class="style-select">
					  		<select name="searchKind">
					      		<option>제목</option>
					      		<option>작성자</option>
					   		</select> 
				   		</span>
				   		<input type="text" name="searchKwd"> 
				   		<input type="submit" value="검색" class="search-button">
					</form>
				</div>
				<table class="table table-bordered" style="text-align: center;">
					<thead>
						<tr>
							<th style="width: 7%;"><div class="form-font">이름</div></th>
							<th style="width: 13%;"><div class="form-font">전공</div></th>
							<th style="width: 30%;"><div class="form-font">제목</div></th>
							<th style="width: 15%;"><div class="form-font">날짜</div></th>
							<th style="width: 5%;"><div class="form-font">추천수</div></th>
							<th style="width: 5%;"><div class="form-font">조회수</div></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${boardList}" var="board">
						<tr style="color : red; font-weight: bold;">
							<td style="width: 7%"><div class="form-font">${board.boardUserName}</div></td>
							<td style="width: 13%"><div class="form-font">${board.boardUserMajor}</div></td>
							<td style="width: 30%"><div class="form-font"><a href="isLogin.do?boardNum=${board.boardNum}" class="winner">${board.boardTitle}</a></div></td>
							<c:set var ="date" value="${board.boardDate}" />
							<td style="width: 15%"><div class="form-font">${fn:substring(date, 0, 11)}&nbsp;${fn:substring(date, 11, 13)}시&nbsp;${fn:substring(date, 14, 16)}분 </div></td>
							<td style="width: 5%"><div class="form-font">${board.boardLike}</div></td>
							<td style="width: 5%"><div class="form-font">${board.boardHit}</div></td>
						</tr>
					</c:forEach>			
					</tbody>
				</table>
		</c:otherwise>
	</c:choose>
	</div>
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