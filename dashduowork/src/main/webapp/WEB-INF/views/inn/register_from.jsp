<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <script type="text/javascript">
    $(function(){
		$("#availableDateSt, #availableDateEnd").datepicker({
          dateFormat : 'yy-mm-dd'
       }); 
    });
/*     $(document).ready(function(){
    	$("#registerForm").submit(function() {
            $("input[name=amenity]:checked").each(function() {
               var test = $(this).val();
               alert(test);
            });
    	});
    }); */
 </script>
 <c:choose>
 <c:when test="${sessionScope.mvo == null}">
 	<script>
 		alert("로그인 후 이용해주세요!\n회원이 아니시면 등록하실 수 없습니다!");
 		location.href ="home.do";
 	</script>
 </c:when>
 <c:otherwise>
 <!-- regiet_from -->
 <div class="section">
      <div class="container">
        <div class="row">
        <div class="col-md-12">
    <center>
      <h2>숙소 등록</h2>
	<br>
    </center>
    </div>
    </div>
    </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <form class="form-horizontal" role="form" action="inn_register.do" 
            enctype="multipart/form-data"  method="post" id="registerForm">
              <div class="form-group">
                <div class="col-sm-2">
                  <label class="control-label">집유형</label>
                </div>
                <div class="col-sm-10">
                  <select class="form-control input-lg" name="innType" required="required">
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
                  <input type="text" class="form-control input-lg" id="inputPassword3" placeholder="숙소명입력" name="innName"  required="required">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label" >지역</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control input-lg"id="inputPassword3" placeholder="예)'서울시 종로구 답십리동' -> 동까지 적어주셔야 합니다!" name="innArea" required="required">
                </div>
              </div>
              
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label" >상세주소</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control input-lg"  id="inputPassword3"
                   placeholder="예)서울아파트 444동 4444호 -> 동 이후의 상세번호를 적어주세요!"  name="innAddress" required="required">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">인원수</label>
                </div>
                <div class="col-sm-10">
                  <input type="number" class="form-control input-lg"  id="inputPassword3" placeholder="인원수입력" name="acceptableNo"  required="required" min="1" max="5">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">가격</label>
                </div>
                <div class="col-sm-10">
                  <input type="number" class="form-control input-lg"  id="inputPassword3" placeholder="가격을 입력" name="innPrice"  min="1000" max="10000000" required="required"/>
                </div>

              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label class="control-label">날짜</label>
                </div>
                <div class="col-sm-3">
                   <input type="text" class="form-control" name="availableDateSt" id="availableDateSt" size="15" onfocus="this.value=''" placeholder="Checkin" required="required">
                </div>
                <div class="col-sm-3">
                   <input type="text" class="form-control" name="availableDateEnd" size="15" id="availableDateEnd" onfocus="this.value=''" placeholder="Checkout" required="required">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">부대시설</label>
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
                  <label class="control-label">사진</label>
                </div>
                <div class="col-sm-3">
                  <input type="file" path="file" class="form-control"  name="file[0]" required="required">
                </div>
                <div class="col-sm-3">
                  <input type="file" path="file" class="form-control" name="file[1]" required="required">
                </div>
                <div class="col-sm-3">
                  <input type="file"  class="form-control" name="file[2]" required="required">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label class="control-label">설명</label>
                </div>
                <div class="col-sm-10">
                  <textarea class="form-control"  placeholder="설명을 입력" 
                  name="innInfo" required="required" cols="20" rows="15" style="resize: none;"></textarea>
                </div>
              </div>

              <!-- <div class="form-group"></div> -->
  		  
  		  <div class="col-sm-2"></div>
          <div class="col-sm-10">
            <center>
              <button type="submit"  class="btn btn-lg btn-primary  col-sm-5 col-sm-offset-3" >등록</a></button>
            </center>
            </div>   
            </form>
          </div>
        </div>
      </div>
    </div>
    </c:otherwise>
    </c:choose>