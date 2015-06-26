<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#updateAmenity").hide();
		$("#haveAmenity").show();
		$("#updatebtn").click(function(){
			if(confirm("기존의 정보는 삭제되며 처음부터 다시 선택하셔야 합니다.\n수정하시겠습니까?")){
			$("#updateAmenity").show();
			$("#haveAmenity").hide();
			}
		});
	});
    $(function(){
		$("#availableDateSt, #availableDateEnd").datepicker({
          dateFormat : 'yy-mm-dd'
       }); 
    });
	function deleteItem(innPicNo,innNo,resultViewNo){
		 $.ajax({
 			type:"get",
 			url:"deleteInnPic.do?innPicNo="+innPicNo+"&innNo="+innNo,
 			//data:"",
 			dataType:"json",
 			success:function(data){
 				 if((data + '').length==0){   
  				 	$("#"+resultViewNo+"").html("");
  				    }else{
  				 $.each(data,function(index,d){
  					$("#"+resultViewNo+"").html("");
 			});
  				    }

 			}
 		}); 
		
	}
    

 </script>

<!-- regiet_from -->
<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<center>
					<h2>등록한 숙소 수정</h2>
				</center>
			</div>
		</div>
	</div>
</div>
<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<form class="form-horizontal" role="form" action="inn_update.do"
					enctype="multipart/form-data" method="post">
					<div class="form-group">
						<div class="col-sm-2">
							<label class="control-label">집유형</label>
						</div>
						<div class="col-sm-10">
							<select class="form-control input-lg" name="innType">
								<option>집 전체</option>
								<option>개인실</option>
								<option>다인실</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputPassword3" class="control-label">숙소명</label>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control input-lg"
								id="inputPassword3" value="${requestScope.VOMap.innVO.innName}"
								placeholder="${requestScope.VOMap.innVO.innAddress}"
								name="innName">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputPassword3" class="control-label">지역</label>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control input-lg"
								id="inputPassword3"
								placeholder="${requestScope.VOMap.innVO.innArea}"
								value="${requestScope.VOMap.innVO.innArea}" name="innArea">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputPassword3" class="control-label">상세주소</label>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control input-lg"
								id="inputPassword3"
								placeholder="${requestScope.VOMap.innVO.innAddress}"
								value="${requestScope.VOMap.innVO.innAddress}" name="innAddress">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputPassword3" class="control-label">인원수</label>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control input-lg"
								id="inputPassword3"
								placeholder="${requestScope.VOMap.innVO.acceptableNo}"
								value="${requestScope.VOMap.innVO.acceptableNo}"
								name="acceptableNo">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputPassword3" class="control-label">가격</label>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control input-lg"
								id="inputPassword3"
								placeholder="${requestScope.VOMap.innVO.innPrice}"
								value="${requestScope.VOMap.innVO.innPrice}" name="innPrice">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label class="control-label">날짜</label>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="availableDateSt"
								id="availableDateSt" size="15"
								placeholder="${requestScope.VOMap.avvo.availableDateSt}"
								value="${requestScope.VOMap.avvo.availableDateSt}">
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="availableDateEnd"
								size="15" id="availableDateEnd"
								placeholder="${requestScope.VOMap.avvo.availableDateEnd}"
								value="${requestScope.VOMap.avvo.availableDateEnd}">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputPassword3" class="control-label">부대시설</label>
						</div>
						<div class="col-sm-10" id="haveAmenity">
							<div align="justify"><input type="button" id="updatebtn" value="수정하기"></div>
							<div class="checkbox">

								<c:forEach items="${requestScope.VOMap.avo}" var="list" varStatus="i">
									<c:choose>
										<c:when test="${list.amenityItem == '1'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="1"
												checked="checked" disabled="disabled">무선인터넷
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '2'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="2"
												checked="checked" disabled="disabled">TV
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '3'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="3"
												checked="checked" disabled="disabled">부엌
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '4'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="4"
												checked="checked" disabled="disabled">가족/어린이 환영
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '5'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="5"
												checked="checked" disabled="disabled">건조기
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '6'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="6"
												checked="checked" disabled="disabled">구급 상자
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '7'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="7"
												checked="checked" disabled="disabled">난방
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '8'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="8"
												checked="checked" disabled="disabled">도어락
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '9'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="9"
												checked="checked" disabled="disabled">무료 주차 포함
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '10'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="10"
												checked="checked" disabled="disabled">무료 헬스장
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '11'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="11"
												checked="checked" disabled="disabled">샴푸
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '12'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="12"
												checked="checked" disabled="disabled">세탁기
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '13'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="13"
												checked="checked" disabled="disabled">소화기
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '14'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="14"
												checked="checked" disabled="disabled">수영장
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '15'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="15"
												checked="checked" disabled="disabled">실내 벽난로
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '16'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="16"
												checked="checked" disabled="disabled">아침식사
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '17'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="17"
												checked="checked" disabled="disabled">안전 카드
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '18'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="18"
												checked="checked" disabled="disabled">애완동물 입실 가능
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '19'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="19"
												checked="checked" disabled="disabled">에어콘
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '20'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="20"
												checked="checked" disabled="disabled">엘리베이터
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '21'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="21"
												checked="checked" disabled="disabled">욕조
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '22'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="22"
												checked="checked" disabled="disabled">이벤트/행사 기능
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '23'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="23"
												checked="checked" disabled="disabled">인터넷
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '24'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="24"
												checked="checked" disabled="disabled">일산화탄소 탐지기
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '25'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="25"
												checked="checked" disabled="disabled">초인종/인터폰
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '26'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="26"
												checked="checked" disabled="disabled">케이블 TV
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '27'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="27"
												checked="checked" disabled="disabled">필수품목
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '28'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="28"
												checked="checked" disabled="disabled">화재 감지기
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '29'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="29"
												checked="checked" disabled="disabled">휠체어 사용 가능
											</label>
										</c:when>
										<c:when test="${list.amenityItem == '30'}">
											<label class="checkbox-inline"> <input
												type="checkbox" name="amenityItemsView" value="30"
												checked="checked" disabled="disabled">흡연가능
											</label>
										</c:when>
									</c:choose>
								</c:forEach>
							</div>
						</div>
					</div>
	
		  <div class="form-group" id="updateAmenity">
                <div class="col-sm-2">
                </div>
                 <div class="col-sm-10">
                  <div class="checkbox">
                    <label class="checkbox-inline">
	                     <input type="checkbox" name="amenityItems" value="1">무선인터넷</label>
                    <label class="checkbox-inline">
                      <input type="checkbox"  name="amenityItems" value="2">TV</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityItems" value="3">부엌</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityItems" value="4">가족/어린이 환영</label>
                    <label class="checkbox-inline">
                      <input type="checkbox"  name="amenityItems" value="5">건조기</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityItems" value="6">구급 상자</label>
                   <label class="checkbox-inline">
                   <input type="checkbox"  name="amenityItems" value="7">난방</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityItems" value="8">도어락</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityItems" value="9">무료 주차 포함</label>
	               </div>
                  
                   <div class="checkbox">
                   <label class="checkbox-inline">
                      <input type="checkbox"  name="amenityItems" value="10">무료 헬스장</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityItems" value="11">샴푸</label>
                    <label class="checkbox-inline">
                      <input type="checkbox"  name="amenityItems" value="12">세탁기</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityItems" value="13">소화기</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityItems" value="14">수영장</label>
                    <label class="checkbox-inline">
                      <input type="checkbox"  name="amenityItems" value="15">실내 벽난로</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityItems" value="16">아침식사</label>
                    <label class="checkbox-inline">
                  	 <input type="checkbox"  name="amenityItems" value="17">안전 카드</label>
                    <label class="checkbox-inline">
                     <input type="checkbox" name="amenityItems" value="18">애완동물 입실 가능</label>
                    </div>
                  
                  <div class="checkbox">
                     <label class="checkbox-inline">
                      <input type="checkbox" name="amenityItems" value="19">에어콘</label>
                    <label class="checkbox-inline">
                      <input type="checkbox"  name="amenityItems" value="20">엘리베이터</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityItems" value="21">욕조</label>
                    <label class="checkbox-inline">
                      <input type="checkbox"  name="amenityItems" value="22">이벤트/행사 기능</label>
             	    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityItems" value="23">인터넷</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityItems" value="24">일산화탄소 탐지기</label>
                    <label class="checkbox-inline">
                      <input type="checkbox"  name="amenityItems" value="25">초인종/인터폰</label>
                 </div>

				 <div class="checkbox">
                    <label class="checkbox-inline">
				   <input type="checkbox" name="amenityItems" value="26">케이블 TV</label>
                    <label class="checkbox-inline">
                      <input type="checkbox"  name="amenityItems" value="27">필수품목</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityItems" value="28">화재 감지기</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityItems" value="29">휠체어 사용 가능</label>
                    <label class="checkbox-inline">
                      <input type="checkbox"  name="amenityItems" value="30">흡연가능</label>  
				 </div>	
                </div>
              </div>
					
		
					<div class="form-group">
						<div class="col-sm-2">
							<label class="control-label">변경할 사진</label>
						</div>
						<div class="col-sm-3">
							<input type="file" class="form-control" name="file[0]"
								required="required">
						</div>
						<div class="col-sm-3">
							<input type="file" class="form-control" name="file[1]"
								required="required">
						</div>
						<div class="col-sm-3">
							<input type="file" class="form-control" name="file[2]"
								required="required">
						</div>
						<div class="col-sm-2">
							<label class="control-label">현재 사진</label>
						</div>
						<c:forEach items="${requestScope.picList}" var="item"
							varStatus="i">
							<!-- 기존 db사진 -->
							<div class="col-sm-3" id="${i.index }">
								<img src="${item.filePath}" height="140" width="120">
								<%--  ${item.filePath} --%>
								<input type="button" value="사진삭제" id="deleteInnPicBtn"
									onclick="deleteItem('${item.innPicNo}','${item.innNo}',${i.index })">
							</div>
						</c:forEach>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label class="control-label">설명</label>
						</div>
						<div class="col-sm-10">
							<textarea class="form-control"
								placeholder="${requestScope.VOMap.innVO.innInfo}" name="innInfo">${requestScope.VOMap.innVO.innInfo}</textarea>
						</div>
					</div>

					<!-- <div class="form-group"></div> -->
					<center>
						<input type="hidden" id="availableDateNo" name="availableDateNo"
							value="${requestScope.VOMap.avvo.availableDateNo}"> <input
							type="hidden" id="innNo" name="innNo"
							value="${requestScope.VOMap.innVO.innNo}">
						<button type="submit"
							class="btn btn-lg btn-primary  col-sm-5 col-sm-offset-3">수정</button>
					</center>
				</form>
			</div>
		</div>
	</div>
</div>
