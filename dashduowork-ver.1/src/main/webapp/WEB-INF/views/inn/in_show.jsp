<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page session="true"%>
<html>

<head>
<!-- <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js">
    </script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
    rel="stylesheet" type="text/css"> 
    
    <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
    rel="stylesheet" type="text/css"> -->
<script type="text/javascript">
	$(document).ready(function() {
		var price = $("#totalPrice").val();
		$("#bookCount").change(function() {
			var bookCount = $("#bookCount").val();
			$("#totalPrice").val(price * bookCount);

		});
	});
	function changeWishListPic(innNo){
		 $.ajax({
			type:"get",
			url:"changeWishListPic.do?innNo="+innNo,
			//data:"",
			dataType:"json",
			success:function(data){
				 $("#wishListImage").html("");
				$("#wishListImage").html( "<a href=wishListReg.do?innNo="+innNo+"><img src='${initParam.root }img/ins.jpg'></a>");
			}
		}); 	
	}
</script>

</head>

<body>
	<br>
	<br>
	<div class="section">
		<div class="container">
			<div class="row">
				<center>
					<h1 class="text-primary">${requestScope.VOMap.memberVO.memberName}님의
						"${requestScope.VOMap.innVO.innName}"</h1>
					<center>
			</div>
		</div>
	</div>
	<br>
	<div class="section">
		<div class="container">
			<div class="row">
				<div id="myCarousel" class="carousel slide col-md-5"
					data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<c:forEach begin="1" end="2" var="i">
							<li data-target="#myCarousel" data-slide-to="${i}"></li>
						</c:forEach>
						<!--  <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li> -->
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<%-- <img src="${requestScope.VOMap.picList.filePath}" style="width:500px; height: 350px;"> --%>
							<c:forEach items="${requestScope.VOMap.picList}" var="list"
								begin="0" end="0">
								<img src="${list.filePath}" style="width: 500px; height: 350px;">
							</c:forEach>
						</div>
						<c:forEach items="${requestScope.VOMap.picList}" var="list"
							begin="1" end="2">
							<div class="item">
								<img src="${list.filePath}" style="width: 500px; height: 350px;">
							</div>
						</c:forEach>
					</div>
					<!-- Left and right controls -->
					<a class="left carousel-control" href="#myCarousel" role="button"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#myCarousel" role="button"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
				<div class="col-md-7">
					<form class="form-horizontal" role="form"
						action="inn_book.do?innNo=${requestScope.VOMap.avo.innNo}&memberId=${sessionScope.mvo.memberId}"
						method="post">
						<!-- 예약 가능 상황 시 기능 활성화 -->
		
						<div class="form-group">
							<div class="col-sm-4">
								<label for="inputEmail3" class="control-label input-lg">체크인</label>
							</div>
							<div class="col-sm-8">
<%-- 					<c:choose>
					<c:when test="${( (requestScope.VOMap.innVO.innAvailability =='Y') && (sessionScope.mvo.memberId != null) )
						&& (requestScope.VOMap.innVO.memberId != sessionScope.mvo.memberId)}"> --%>
								<input type="date" class="form-control" name="bookCheckIn"
									id="bookCheckIn" size="15" onfocus="this.value=''"
									min="${requestScope.VOMap.avo.availableDateSt}"
									max="${requestScope.VOMap.avo.availableDateEnd}"
									required="required">
<%-- 					</c:when>
					<c:when test="${( (requestScope.VOMap.innVO.innAvailability =='Y' || requestScope.VOMap.innVO.innAvailability =='N')
					&&sessionScope.mvo.memberId == null)||(requestScope.VOMap.innVO.memberId == sessionScope.mvo.memberId)}">
						<input type="date" class="form-control" name="bookCheckIn"
									id="bookCheckIn" size="15" onfocus="this.value=''"
									min="${requestScope.VOMap.avo.availableDateSt}"
									max="${requestScope.VOMap.avo.availableDateEnd}"
									required="required" disabled="disabled">
					</c:when>
					</c:choose> --%>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-4">
								<label for="inputEmail3" class="control-label input-lg">체크아웃</label>
							</div>
							<div class="col-sm-8">
<%-- 							<c:choose>
					<c:when test="${(requestScope.VOMap.innVO.innAvailability =='Y' && sessionScope.mvo.memberId != null)
						&&(requestScope.VOMap.innVO.memberId != sessionScope.mvo.memberId)}"> --%>
								<input type="date" class="form-control" name="bookCheckOut"
									size="15" id="bookCheckOut" onfocus="this.value=''"
									min="${requestScope.VOMap.avo.availableDateSt}"
									max="${requestScope.VOMap.avo.availableDateEnd}"
									required="required">
<%-- 							</c:when>
					<c:otherwise>
								<input type="date" class="form-control" name="bookCheckOut"
									size="15" id="bookCheckOut" onfocus="this.value=''"
									min="${requestScope.VOMap.avo.availableDateSt}"
									max="${requestScope.VOMap.avo.availableDateEnd}"
									required="required" disabled="disabled">
				</c:otherwise>
						</c:choose>			 --%>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-4">
								<label class="control-label input-lg">인원수</label>
							</div>
							<div class="col-sm-8">
<%-- 							<c:choose>
						<c:when test="${(requestScope.VOMap.innVO.innAvailability =='Y' && sessionScope.mvo.memberId != null)
						&&(requestScope.VOMap.innVO.memberId != sessionScope.mvo.memberId)}"> --%>
								<input type="number" name="bookCount" id="bookCount" min="1"
									max="${requestScope.VOMap.innVO.acceptableNo}" required="required">&ensp; 명
<%-- 						</c:when>
						<c:when test="${(requestScope.VOMap.innVO.innAvailability =='N' && sessionScope.mvo.memberId != null)
					||(requestScope.VOMap.innVO.memberId == sessionScope.mvo.memberId)||sessionScope.mvo.memberId==null}">
						<input type="number" name="bookCount" id="bookCount" min="1"
									max="${requestScope.VOMap.innVO.acceptableNo}" required="required" disabled="disabled">&ensp; 명
									</c:when>
						</c:choose>	 --%>
							</div>
						</div>
						<div class="form-group has-feedback">
							<div class="col-sm-4">
								<label for="inputEmail3" class="control-label input-lg">총
									가격</label>
							</div>
							<div class="col-sm-8">
<%-- 						<c:choose>
						<c:when test="${(requestScope.VOMap.innVO.innAvailability =='Y' && sessionScope.mvo.memberId != null)
						&&(requestScope.VOMap.innVO.memberId != sessionScope.mvo.memberId)}"> --%>
								<input type="text" class="form-control input-lg" id="totalPrice"
									readonly="readonly"
									value="${requestScope.VOMap.innVO.innPrice}">
<%-- 						</c:when>
						<c:when test="${(requestScope.VOMap.innVO.innAvailability =='N' && sessionScope.mvo.memberId != null)
					||(requestScope.VOMap.innVO.memberId == sessionScope.mvo.memberId)||sessionScope.mvo.memberId==null}">
									<input type="text" class="form-control input-lg" id="totalPrice"
									readonly="readonly" value="${requestScope.VOMap.innVO.innPrice}" disabled="disabled">
						</c:when>
						</c:choose> --%>
							</div>
						</div>

						
						<c:choose>
						<c:when test="${requestScope.VOMap.innVO.innAvailability =='Y' && sessionScope.mvo.memberId != null &&requestScope.VOMap.innVO.memberId != sessionScope.mvo.memberId}">
						<input type="submit"
							class="btn btn-danger btn-lg col-sm-10 input-lg" value="예약하기">
							<div id="wishListImage">
							<c:choose>
								<c:when test="${requestScope.wishFlag=='no' }">
									<a href="wishListReg.do?innNo=${requestScope.VOMap.avo.innNo}"><img src="${initParam.root }img/ins.jpg"></a>
								</c:when>
								<c:otherwise>
								<input type="image" src="${initParam.root }img/wish.jpg" onclick="changeWishListPic('${requestScope.VOMap.avo.innNo}')">
								<!-- </div> -->
								</c:otherwise>
							</c:choose>
							</div>
							</c:when>
							<c:when test="${requestScope.VOMap.innVO.innAvailability =='N' && sessionScope.mvo.memberId != null &&requestScope.VOMap.innVO.memberId != sessionScope.mvo.memberId}">
							    <input type="button"
						     	class="btn btn-danger btn-lg col-sm-10 input-lg" value="휴면중인 숙소입니다." disabled="disabled">
						    <div id="wishListImage">
							<c:choose>
								<c:when test="${requestScope.wishFlag=='no' }">
									<a href="wishListReg.do?innNo=${requestScope.VOMap.avo.innNo}"><img src="${initParam.root }img/ins.jpg"></a>
								</c:when>
								<c:otherwise>
								<input type="image" src="${initParam.root }img/wish.jpg" onclick="changeWishListPic('${requestScope.VOMap.avo.innNo}')">
								<!-- </div> -->
								</c:otherwise>
							</c:choose>
							</div>
							</c:when>
							<c:when test="${(requestScope.VOMap.innVO.innAvailability =='Y' || requestScope.VOMap.innVO.innAvailability =='N')&&sessionScope.mvo.memberId == null}">
							<input type="button"
						     	class="btn btn-danger btn-lg col-sm-12 input-lg" value="로그인 후 이용하세요!." disabled="disabled">
							</c:when>
							<c:when test="${requestScope.VOMap.innVO.memberId == sessionScope.mvo.memberId}">
								<input type="button"
						     	class="btn btn-danger btn-lg col-sm-12 input-lg" value="${requestScope.VOMap.innVO.memberId}님의 숙소입니다!" disabled="disabled">
							</c:when>
							</c:choose>
					</form>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<div class="section">
		<div class="container">
			<div class="row"></div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<form class="form-horizontal text-center" role="form">
						<%--  <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputEmail3" class="control-label input-lg">숙소명</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control input-lg" id="inputEmail3" value="${requestScope.VOMap.innVO.innName}" readonly="readonly">
                </div>
              </div> --%>
						<div class="form-group">
							<div class="col-sm-2">
								<label for="inputEmail3" class="control-label input-lg">주소</label>
							</div>
							<div class="col-sm-10">
								<input type="text" class="form-control input-lg"
									id="inputEmail3" value="${requestScope.VOMap.innVO.innAddress}"
									readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2">
								<label class="control-label input-lg">상세보기</label>
							</div>
							<div class="col-sm-10">
								<textarea class="form-control " wrap="soft" placeholder="상세보기"
									cols="40" rows="10" readonly="readonly">${requestScope.VOMap.innVO.innInfo}</textarea>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
							<a href="member_myprofile.do?memberId=${requestScope.VOMap.memberVO.memberId}">
								<img src="${requestScope.VOMap.pvo.filePath}"
									class="img-circle img-responsive" width="200" height="200">
									</a>
							</div>
							<div class="col-md-5 text-left">
								<label class="control-label input-lg">이름</label> <input
									class="form-control input-lg " type="text"
									value="${requestScope.VOMap.memberVO.memberName}"
									readonly="readonly">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
