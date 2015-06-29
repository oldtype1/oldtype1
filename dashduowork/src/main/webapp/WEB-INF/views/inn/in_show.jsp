<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page session="true"%>
<html>

<head>

<script type="text/javascript">
	$(document).ready(function() {
	
		var price = $("#totalPrice").val();
		$("#bookCount").change(function() {
			var bookCount = $("#bookCount").val();
			var start = $("#bookCheckIn").val();
			var end = $("#bookCheckOut").val();
			
			var dateStartArray = start.split("-");  
			var dateEndArray = end.split("-");  
			var dateStartObj = new Date(dateStartArray[0], Number(dateStartArray[1])-1, dateStartArray[2]);  
			var dateEndObj = new Date(dateEndArray[0], Number(dateEndArray[1])-1, dateEndArray[2]);  
			
			var betweenDay = (dateEndObj.getTime() - dateStartObj.getTime())/1000/60/60/24;
		
			if((start == "")||(end == ""))
				$("#totalPrice").val(price * bookCount);
			else
			$("#totalPrice").val(price * bookCount * Number(betweenDay));
			
			if($("#totalPrice").val()<0){
				alert("체크아웃은 반드시 체크인보다 후의 날짜이어야 합니다!");
			    $("#bookCheckIn").val("");
				$("#bookCheckOut").val("");
				$("#totalPrice").val(price);
			}
		});
		
/* 		$("#bookForm").submit(function(){
			var start = $("#bookCheckIn").val();
			var end = $("#bookCheckOut").val();
			
			var dateStartArray = start.split("-");  
			var dateEndArray = end.split("-");  
			var dateStartObj = new Date(dateStartArray[0], Number(dateStartArray[1])-1, dateStartArray[2]);  
			var dateEndObj = new Date(dateEndArray[0], Number(dateEndArray[1])-1, dateEndArray[2]);  
			
			var betweenDay = (dateEndObj.getTime() - dateStartObj.getTime())/1000/60/60/24;
			if(betweenDay<0){
				
				return false;
			}
		}); */
		
	}); // document
	
	
	function changeWishListPic(innNo){
		 $.ajax({
			type:"get",
			url:"changeWishListPic.do?innNo="+innNo,
			//data:"",
			dataType:"json",
			success:function(data){

					 $("#wishListImage").html("");
						$("#wishListImage").html( "<input type='image' src='${initParam.root }img/ins.jpg' onclick='InsertWishListPic("+innNo+")'>");
			}
		}); 	
	}
	function InsertWishListPic(innNo){
		 $.ajax({
			type:"get",
			url:"wishListReg.do?innNo="+innNo,
			//data:"",
			//dataType:"json",
			success:function(data){
				if(data){
					 $("#wishListImage").html("");
						$("#wishListImage").html( "<input type='image' src='${initParam.root }img/wish.jpg' onclick='changeWishListPic("+innNo+")'>");
				}
				else{
					location.href="${initParam.root }member/wishlist_fail.jsp";
				}
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
						action="paymentForm.do?innNo=${requestScope.VOMap.avo.innNo}&memberId=${sessionScope.mvo.memberId}"
						method="post" id="bookForm">
						<!-- 예약 가능 상황 시 기능 활성화 -->
		
						<div class="form-group">
							<div class="col-sm-4">
								<label for="inputEmail3" class="control-label input-lg">체크인</label>
							</div>
							<div class="col-sm-8">

								<input type="date" class="form-control" name="bookCheckIn"
									id="bookCheckIn" size="15" onfocus="this.value=''"
									min="${requestScope.VOMap.avo.availableDateSt}"
									max="${requestScope.VOMap.avo.availableDateEnd}"
									required="required">

							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-4">
								<label for="inputEmail3" class="control-label input-lg">체크아웃</label>
							</div>
							<div class="col-sm-8">

								<input type="date" class="form-control" name="bookCheckOut"
									size="15" id="bookCheckOut" onfocus="this.value=''"
									min="${requestScope.VOMap.avo.availableDateSt}"
									max="${requestScope.VOMap.avo.availableDateEnd}"
									required="required">

							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-4">
								<label class="control-label input-lg">인원수</label>
							</div>
							<div class="col-sm-8">

								<input type="number" name="bookCount" id="bookCount" min="1"
									max="${requestScope.VOMap.innVO.acceptableNo}" required="required">&ensp; 명
				
							</div>
						</div>
						<div class="form-group has-feedback">
							<div class="col-sm-4">
								<label for="inputEmail3" class="control-label input-lg">총
									가격</label>
							</div>
							<div class="col-sm-8">

								<input type="text" class="form-control input-lg" id="totalPrice"
									readonly="readonly"
									value="${requestScope.VOMap.innVO.innPrice}">

							</div>
						</div>
			
						
						<c:choose>
						<c:when test="${requestScope.VOMap.innVO.innAvailability =='Y' && sessionScope.mvo.memberId != null &&requestScope.VOMap.innVO.memberId != sessionScope.mvo.memberId}">
						<input type="submit"
							class="btn btn-danger btn-lg col-sm-10 input-lg" value="예약하기">
							<p id="wishListImage">
							<c:choose>
								<c:when test="${requestScope.wishFlag=='no' }">
									<%-- <a href="wishListReg.do?innNo=${requestScope.VOMap.avo.innNo}"><img src="${initParam.root }img/ins.jpg"></a> --%>
								<input type="image" src="${initParam.root }img/ins.jpg" onclick="InsertWishListPic('${requestScope.VOMap.avo.innNo}')">
								</c:when>
								<c:otherwise>
								<input type="image" src="${initParam.root }img/wish.jpg" onclick="changeWishListPic('${requestScope.VOMap.avo.innNo}')">
								<!-- </div> -->
								</c:otherwise>
							</c:choose>
							</p>
							</c:when>
							<c:when test="${requestScope.VOMap.innVO.innAvailability =='N' && sessionScope.mvo.memberId != null &&requestScope.VOMap.innVO.memberId != sessionScope.mvo.memberId}">
							    <input type="button"
						     	class="btn btn-danger btn-lg col-sm-10 input-lg" value="휴면중인 숙소입니다." disabled="disabled">
						    <p id="wishListImage">
							<c:choose>
								<c:when test="${requestScope.wishFlag=='no' }">
									<a href="wishListReg.do?innNo=${requestScope.VOMap.avo.innNo}"><img src="${initParam.root }img/ins.jpg"></a>
								</c:when>
								<c:otherwise>
								<input type="image" src="${initParam.root }img/wish.jpg" onclick="changeWishListPic('${requestScope.VOMap.avo.innNo}')">
								<!-- </div> -->
								</c:otherwise>
							</c:choose>
							</p>
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
							<c:if test="${requestScope.count!=0}">
					<c:forEach  begin="1" end="${requestScope.count}">	
		<img src="${initParam.root}img/star.jpg" style="margin-top: 15px; margin-left:5px" width="50" height="50" >
					</c:forEach>	
					<p style="margin-top: 15px; margin-left:5px" >${requestScope.peopleNum}명이 이 숙소를 평가하였습니다.</p>
					</c:if>
					<c:if test="${requestScope.count==0}">
						<br>
						<font size="4" style="margin-top: 20px; margin-left:10px">등록된 별점이 없습니다.</font>
					</c:if>
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
