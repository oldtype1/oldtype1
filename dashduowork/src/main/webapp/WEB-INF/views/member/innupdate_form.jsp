<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <script type="text/javascript">
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
            <form class="form-horizontal" role="form" action="inn_update.do" enctype="multipart/form-data"  method="post">
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
                  <input type="text" class="form-control input-lg" id="inputPassword3" value="${requestScope.VOMap.innVO.innName}" 
                  placeholder="${requestScope.VOMap.innVO.innAddress}" name="innName" >
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label" >주소</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control input-lg" id="inputPassword3" placeholder="${requestScope.VOMap.innVO.innAddress}" 
                  value="${requestScope.VOMap.innVO.innAddress}" name="innAddress">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">대지역</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control input-lg" id="inputPassword3" placeholder="${requestScope.VOMap.innVO.innCity}" 
                  value="${requestScope.VOMap.innVO.innCity}" name="innCity">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">소지역</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control input-lg" id="inputPassword3" placeholder="${requestScope.VOMap.innVO.innArea}" 
                  value="${requestScope.VOMap.innVO.innArea}" name="innArea">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">인원수</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control input-lg" id="inputPassword3" placeholder="${requestScope.VOMap.innVO.acceptableNo}" 
                  value="${requestScope.VOMap.innVO.acceptableNo}"   name="acceptableNo" >
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">가격</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control input-lg" id="inputPassword3" placeholder="${requestScope.VOMap.innVO.innPrice}" 
                  value="${requestScope.VOMap.innVO.innPrice}" name="innPrice" >
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label class="control-label">날짜</label>
                </div>
                <div class="col-sm-3">
                  <input type="text" class="form-control" name="availableDateSt" id="availableDateSt" size="15"  placeholder="${requestScope.avvo.availableDateSt}" value="${requestScope.avvo.availableDateSt}">
                </div>
                <div class="col-sm-3">
                   <input type="text" class="form-control" name="availableDateEnd" size="15" id="availableDateEnd" placeholder="${requestScope.avvo.availableDateEnd}" value="${requestScope.avvo.availableDateEnd}">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">상세설명</label>
                </div>
                <div class="col-sm-10">
                  <div class="checkbox">
                    <label class="checkbox-inline">
                    <c:choose>
                    <c:when test="${requestScope.avo.amenityWiFi=='N'}">
                         <input type="checkbox" name="amenityWiFi" value="Y">wi-fi</label>
                    </c:when>
                    <c:otherwise>
                         <input type="checkbox" name="amenityWiFi" value="Y" checked="checked">wi-fi</label>
                    </c:otherwise>
                    </c:choose>
                    <label class="checkbox-inline">
                        <c:choose>
                    <c:when test="${requestScope.avo.amenityBed=='N'}">
                          <input type="checkbox" name="amenityBed" value="Y">bed</label>
                    </c:when>
                    <c:otherwise>
                          <input type="checkbox" name="amenityBed" value="Y" checked="checked">bed</label>
                    </c:otherwise>
                    </c:choose>
                    <label class="checkbox-inline">
                           <c:choose>
                    <c:when test="${requestScope.avo.amenityTV=='N'}">
                            <input type="checkbox" name="amenityTV" value="Y">tv</label>
                    </c:when>
                    <c:otherwise>
                            <input type="checkbox" name="amenityTV" value="Y" checked="checked">tv</label>
                    </c:otherwise>
                    </c:choose>
                
                    <label class="checkbox-inline">
                       <c:choose>
                    <c:when test="${requestScope.avo.amenityKitchen=='N'}">
                             <input type="checkbox" name="amenityKitchen" value="Y">kitchen</label>
                    </c:when>
                    <c:otherwise>
                            <input type="checkbox" name="amenityKitchen" value="Y" checked="checked">kitchen</label>
                    </c:otherwise>
                    </c:choose>
                     
                    <label class="checkbox-inline">
                    <c:choose>
                    <c:when test="${requestScope.avo.amenityBBQ=='N'}">
                                       <input type="checkbox" name="amenityBBQ" value="Y">bbq</label>
                    </c:when>
                    <c:otherwise>
                                     <input type="checkbox" name="amenityBBQ" value="Y" checked="checked">bbq</label>
                    </c:otherwise>
                    </c:choose>
           
                  </div>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label class="control-label">변경할 사진</label>
                </div>
                <div class="col-sm-3">
                  <input type="file" class="form-control"  name="file[0]" required="required">
                </div>   
                <div class="col-sm-3">
                  <input type="file" class="form-control" name="file[1]" required="required">
                </div>
                <div class="col-sm-3">
                  <input type="file" class="form-control" name="file[2]"required="required">
                </div>
                <div class="col-sm-2">
                  <label class="control-label">현재 사진</label>
                </div>
                  <c:forEach items="${requestScope.picList}" var="item" varStatus="i">
                  <!-- 기존 db사진 -->
                   <div class="col-sm-3" id="${i.index }">
                 <img src="${item.filePath}" height="140" width="120">
                <%--  ${item.filePath} --%>
                	<input type="button" value="사진삭제" id="deleteInnPicBtn" onclick="deleteItem('${item.innPicNo}','${item.innNo}',${i.index })">
				    	  </div>      
                </c:forEach>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label class="control-label">설명</label>
                </div>
                <div class="col-sm-10">
                  <textarea class="form-control" placeholder="${requestScope.VOMap.innVO.innInfo}" name="innInfo">${requestScope.VOMap.innVO.innInfo}</textarea>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label class="control-label">숙소 가능 여부</label>
                </div>
                <div class="col-sm-10">
                  <select class="form-control" name="innAvailability">
                    <option>Y</option>
                    <option>N</option>
                  </select>
                </div>
              </div>
              <!-- <div class="form-group"></div> -->
            <center>
             <input type="hidden" id="availableDateNo" name="availableDateNo"value="${requestScope.avvo.availableDateNo}"> 
            <input type="hidden" id="innNo" name="innNo"value="${requestScope.VOMap.innVO.innNo}"> 
              <button type="submit"  class="btn btn-lg btn-primary  col-sm-5 col-sm-offset-3" >수정</a></button>
            </center>
            </form>
          </div>
        </div>
      </div>
    </div>
 