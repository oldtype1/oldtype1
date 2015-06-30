<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.photo_main {position: relative;}
.photo_main_up{ position: absolute;left: 10px; bottom: 50px;font-size: 1.83em;  width: 300px;
font-weight: bold;opacity: 0.7; filter: alpha(opacity=60);background-color: #C6C6C6 }
</style>
<script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script> 
<script>
function initialize() {
   var geocoder = new google.maps.Geocoder();
   var addr = $("#local").val();
   var lat = "";
   var lng = "";
   geocoder.geocode({
      'address' : addr
   },
   function(results, status) {
      if (results != "") {
         var location = results[0].geometry.location;
         lat = location.lat();
         lng = location.lng();
         var latlng = new google.maps.LatLng(lat, lng);
         var myOptions = {
            zoom : 16,
            center : latlng,
            mapTypeControl : true,
            mapTypeId : google.maps.MapTypeId.ROADMAP
         };
         
         var map = new google.maps.Map(document
               .getElementById("map_canvas"), myOptions);   
         
         var marker = new google.maps.Marker({ 
              position: latlng, 
              map: map, 
              title: "검색한키워드"//마커에 올렸을때 나타나는 내용
            }); 
         
         google.maps.event.addListener(marker, 'mouseover', function() { 
            var infowindow = new google.maps.InfoWindow( 
                        { content: addr, 
                          size: new google.maps.Size(100,100) 
                        })
            infowindow.open(map, marker);
            });      
         
      }else
         $("#map_canvas").html("위도와 경도를 찾을 수 없습니다.");
   })
}
 function mapping(innName,innAddress,detailAddress){
   var geocoder = new google.maps.Geocoder();
   var addr = innAddress+" "+detailAddress;
   var lat = "";
   var lng = "";
   geocoder.geocode({
      'address' : addr
   },
   function(results, status) { 
      if (results != "") {
         var location = results[0].geometry.location;
         lat = location.lat();
         lng = location.lng();
         var latlng = new google.maps.LatLng(lat, lng);
         var myOptions = {
            zoom : 16,
            center : latlng,
            mapTypeControl : true,
            mapTypeId : google.maps.MapTypeId.ROADMAP
         };
         var map = new google.maps.Map(document
               .getElementById("map_canvas"), myOptions);         
         var marker = new google.maps.Marker({ 
              position: latlng, 
              map: map, 
              title: "검색한 숙소"//마커에 올렸을때 나타나는 내용
            }); 
         google.maps.event.addListener(marker, 'mouseover', function() { 
            var infowindow = new google.maps.InfoWindow( 
                        { content: innName, 
                          size: new google.maps.Size(100,100) 
                        })
            infowindow.open(map, marker);
            });      
      }else
         $("#map_canvas").html("위도와 경도를 찾을 수 없습니다.");
   })
//클릭할때 마다 마커를 찍는다.
} 

$(document).ready(function(){
   $( "#dialog" ).dialog({
      position: [900,70]
   });
   var a = $("#directedFlag").val(); // 히든값 확인하기위해      
    $("#filter_detailForm").hide();
    $('#button_detail').click(function() {         
       if(a==0){
          $("#button_detail").attr("src","${initParam.root}img/directed-back.jpg");
          a=1;
       }else if(a==1){
          $("#button_detail").attr("src","${initParam.root}img/directed.jpg");
          a=0;
       }
       
       $("#filter_detailForm").slideToggle('fast', function() {
          return false;
       });
       return false;
    });
    
   $("#searchInnCityAjaxForm").submit(function(){
      if($("#searchCity").val()==""){
         alert("지역을 입력해주세요.");
         return false;
      }
   });
});
function SearchInnCityListByAjax() {       
   var availableTags = []; //자동 완성 해당 단어 저장할 배열(객체) 선언 / 항상 초기화 되어야함
   $.ajax({ //db에 저장되어있는 값들 ajax로 반환
      type : "post",
      url : "searchCityAuto.do",
      data : $("#searchInnCityAjaxForm").serialize(),
      dataType : "json",
      success : function(data) {
         $.each(data,function(index,result){
            if(data.length!=0){
               availableTags.push(result.innArea);
            }               
         });            
      }//success
   });//ajax
   
   
   $("#searchCity").autocomplete({//자동완성 기능을 searchCity id에 부여한다.
      source : availableTags //자동완성 소스는 availableTags 배열을 사용 한다.      
   });
}//function SearchInnCityListByAjax()
$(function() {
   $( "#slider-range" ).slider({
         range: true,
         min: 0,
         max: 40000,
         values: [ 1000, 50000 ],
         slide: function( event, ui ) {
           $( "#amount" ).val( "₩" + ui.values[ 0 ] + " - ₩" + ui.values[ 1 ] );
           $("#minPrice").val($( "#slider-range" ).slider( "values", 0 ));
           $("#maxPrice").val($( "#slider-range" ).slider( "values", 1 ));
         }
       });
       $( "#amount" ).val( "₩" + $( "#slider-range" ).slider( "values", 0 ) + " - ₩" + $( "#slider-range" ).slider( "values", 1 ) );
       
       $("#slider-range").mouseup(function(){
          /* alert($("#minPrice").val()); */
          $("#resultViewSearch").html("");
         //alert();list.getInnN
         $.ajax({
            type : "Post",
            url : "searchInnByWordDateNoWithFilter.do",
            data : $("#filterForm").serialize(),
            dataType : "json",
            success : function(innInfoList) {
               var tableInfo="<table><tr>";
               if (innInfoList.innList.length != 0) {
                  $.each(innInfoList.innList, function(index, info) {
                     if((index+1)%2==1){
                        tableInfo+="</tr><tr>";
                     }
                     tableInfo+="<td class=photo_main><a href='inn_in_show.do?innNo="+info.innNo+"'>";
                     tableInfo+="<img class='img-rounded' src='"+ info.innMainPic.filePath +"' height='320' width='350'><br>"+info.innName+"</a>";
                     tableInfo+="<span class='photo_main_up' style='width:120px;'>₩"+info.innPrice+"</span>/ "+info.innType;
                     tableInfo+="<a href='#'><img src='${initParam.root }/img/map.jpg' onclick='mapping("+info.innName+","+info.innArea+" ,"+info.innAddress+")'></a></td>";
                     /* if(info.innMainPic==null){
                        tableInfo+="<tr><td>"+info.innNo+"</td><td><img class='img-rounded' src='${initParam.root}img/no_img.gif' height='150' width='150'></td><td><a href='inn_in_show.do?innNo="+info.innNo+"'>"+info.innName+"</td><td>"+info.innArea+"</td><td>"+info.innType+"</td><td>"+info.acceptableNo+"</td><td>"+info.innPrice+"</td></tr>";
                     }else{
                        tableInfo+="<tr><td>"+info.innNo+"</td><td><img class='img-rounded' src='"+info.innMainPic.filePath+"' height='150' width='150'></td><td><a href='inn_in_show.do?innNo="+info.innNo+"'>"+info.innName+"</td><td>"+info.innArea+"</td><td>"+info.innType+"</td><td>"+info.acceptableNo+"</td><td>"+info.innPrice+"</td></tr>";
                     } */
                  }); //each
               }//if
               else if(innInfoList.innList.length == 0){
                  tableInfo+="<td align='center'>검색결과가 존재하지 않습니다.</td>";
               }
               tableInfo+="</tr></table>";
               $("#resultViewSearch").html(tableInfo);
            }
         });//ajax
       });//mouseup
       
      $("#checkin, #checkout").datepicker({
         dateFormat : 'yy-mm-dd'
      });
      $("#filterForm").change(function() {
         $("#resultViewSearch").html("");
         //alert();
         $.ajax({
            type : "Post",
            url : "searchInnByWordDateNoWithFilter.do",
            data : $("#filterForm").serialize(),
            dataType : "json",
            success : function(innInfoList) {
               var tableInfo="<table><tr>";
               if (innInfoList.innList.length != 0) {
                  $.each(innInfoList.innList, function(index, info) {
                     if((index+1)%2==1){
                        tableInfo+="</tr><tr>";
                     }
                     tableInfo+="<td class=photo_main><a href='inn_in_show.do?innNo="+info.innNo+"'>";
                     tableInfo+="<img class='img-rounded' src='"+ info.innMainPic.filePath +"' height='320' width='350'><br>"+info.innName+"</a>";
                     tableInfo+="<span class='photo_main_up' style='width:120px;'>₩"+info.innPrice+"</span>/ "+info.innType;
                     tableInfo+="<a href='#'><img src='${initParam.root }/img/map.jpg' onclick='mapping('"+info.innName+"','"+info.innArea+"','"+info.innAddress+"')'></a></td>";
                     /* if(info.innMainPic==null){
                        tableInfo+="<tr><td>"+info.innNo+"</td><td><img class='img-rounded' src='${initParam.root}img/no_img.gif' height='150' width='150'></td><td><a href='inn_in_show.do?innNo="+info.innNo+"'>"+info.innName+"</td><td>"+info.innArea+"</td><td>"+info.innType+"</td><td>"+info.acceptableNo+"</td><td>"+info.innPrice+"</td></tr>";
                     }else{
                        tableInfo+="<tr><td>"+info.innNo+"</td><td><img class='img-rounded' src='"+info.innMainPic.filePath+"' height='150' width='150'></td><td><a href='inn_in_show.do?innNo="+info.innNo+"'>"+info.innName+"</td><td>"+info.innArea+"</td><td>"+info.innType+"</td><td>"+info.acceptableNo+"</td><td>"+info.innPrice+"</td></tr>";
                     } */
                  }); //each
               }//if
               else if(innInfoList.innList.length == 0){
                  tableInfo+="<td align='center'>검색결과가 존재하지 않습니다.</td>";
               }
               tableInfo+="</tr></table>";
               $("#resultViewSearch").html(tableInfo);
            }
         });//ajax
      }); //change
   });
</script>

<div id="dialog" title="검색어 순위">
  <c:forEach var="list" items="${requestScope.wordlist }" varStatus="i" begin="0" end="4" step="1">
     ${i.count}. ${list.word }<br>
  </c:forEach>
</div>

<div class="section" style="position: relative; top: 30px;">
   <div class="container">
      <div class="row">
      
      <div class="col-md-8" style="top: 45px;">
            <form action="searchInnByWordDateNo.do" class="navbar-form navbar-left"
               role="search" id="searchInnCityAjaxForm">
               <input type="text" class="form-control" name="searchWord"
                  id="searchCity" placeholder="State" size="54"
                  onkeyup="SearchInnCityListByAjax()"><br> <input
                  type="text" class="form-control" name="searchStartDate" id="checkin"
                  size="15" onfocus="this.value=''" placeholder="Checkin"
                  style="width: 150px;"> <input type="text"
                  class="form-control" name="searchEndDate" size="15" id="checkout"
                  onfocus="this.value=''" placeholder="Checkout"
                  style="width: 150px;"> <select class="form-control"
                  name="searchPeopleNo" id="select">
                  <option value="1">게스트 1명</option>
                  <option value="2">게스트 2명</option>
                  <option value="3">게스트 3명</option>
                  <option value="4">게스트 4명</option>
                  <option value="5">게스트 5명</option>
                  <option value="6">게스트 6명</option>
               </select>
               <button type="submit" class="btn btn-default">검색</button>
            </form>

            <form id="filterForm" action="selectInnByCheckedAmenity.do" class="navbar-form navbar-left">
               <p>
                  <label for="amount">가격 범위:</label> 
                  <input type="text" id="amount"
                     readonly style="border: 0; color: black;">
               </p>
               <div id="slider-range" style="width: 500px;"></div>
               
               <input type="hidden" name="flag" value="0">
               <input type="hidden" name="searchWord"
                  value="${requestScope.filterVO.searchWord}"> <input
                  type="hidden" name="searchStartDate"`
                  value="${requestScope.filterVO.searchStartDate}"> <input
                  type="hidden" name="searchEndDate"
                  value="${requestScope.filterVO.searchEndDate}"> <input
                  type="hidden" name="searchPeopleNo"
                  value="${requestScope.filterVO.searchPeopleNo}"> <input
                  type="hidden" name="minPrice" id="minPrice" value="0"> <input
                  type="hidden" name="maxPrice" id="maxPrice" value="999999">
             <%--   <input type="image" src="${initParam.root}img/directed.jpg"   height="10 width="15" id="button_detail"> --%>
              <br>
              <input type="button" value="필터추가" id="button_detail" >
               <input type="hidden" src="${initParam.root}img/directed-back.jpg"   height="10" width="15" id="button_detailback">
               <input type="hidden"  id="directedFlag" value=0>
               <br>
               <br>
         <div id="filter_detailForm" >
         <div class="checkbox">
              <label class="checkbox-inline">
                        <input type="checkbox" name="amenityItems" value="1">무선인터넷</label>
                    <label class="checkbox-inline">
                      <input type="checkbox"  name="amenityItems" value="2">TV</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityItems" value="3">부엌</label>
               </div>   
         <div class="checkbox">
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
                          <label class="checkbox-inline">
                     <input type="checkbox" name="amenityItems" value="18">애완동물 입실 가능</label>
             </div>   
             <br>
             <br>
         </div>
    </form>
         </div>
      </div>
   </div>
</div>
<br>
<br>
<br>
<br>
<br>
<br>

<div class="section">
   <div class="container">
      <div class="row">
         <div class="col-md-8">
<input type="hidden" id="local" value="${requestScope.filterVO.searchWord}">
         지역 : ${requestScope.filterVO.searchWord} / 인원 : ${requestScope.filterVO.searchPeopleNo } 명 에 대한 검색결과 <span class="glyphicons glyphicons-google-maps"></span><br>
            <div id="resultViewSearch">
               <table>
                  <c:choose>
                     <c:when test="${requestScope.innListVO.innList.size()==0}" >
                        <td align="center">검색결과가 존재하지 않습니다.</td>
                     </c:when>   
                        <c:otherwise>
                           <tr>
                           <c:forEach var="list" items="${requestScope.innListVO.innList}" varStatus="status">
                              <c:if test="${(status.index+1)%2==1}"></tr><tr></c:if>
                              <td class=photo_main><a href="inn_in_show.do?innNo=${list.innNo}">
                              <img class="img-rounded" src="${list.innMainPic.filePath}" height="320" width="350">            
                               <br>
                               ${list.innName}</a>
                               <span class='photo_main_up'  style="width:120px;">₩${list.innPrice}</span>   
                               / ${list.innType}
                               <%-- <input type="button" value="위치확인" onclick="mapping('${list.innName}','${list.innArea}' ,'${list.innAddress}')" class="btn btn-default"> --%>
                               <a href="#"><img src="${initParam.root }/img/map.jpg" onclick="mapping('${list.innName}','${list.innArea}' ,'${list.innAddress}')"></a></td>
                           </c:forEach>
                           </tr>
                        </c:otherwise>
                  </c:choose>
               </table>
            </div>
         </div>
         <body onload="initialize()"> 
          <div id="map_canvas" style="z-index: 3; width:380px; height:800px; float: left;"> </div>
          </body>
      </div>
   </div>
</div>
 
 
<div class="section text-center">
        <div class="container">
          <div class="row">
            <div class="col-md-12 text-center">
            <div id="resultViewPage"> <!--ajax로 바꿔줄꺼야. 페이징빈 ...   -->
              <ul class="pagination pagination-sm">
                  
                  <c:set var="pb" value="${requestScope.innListVO.pagingBean}"></c:set>
                  
                  <c:if test="${pb.previousPageGroup}">
                  <li><a
                     href="searchInnByWordDateNo.do?pageNo=${pb.startPageOfPageGroup-1}">Prev</a>
                  </li>
                  </c:if>
                  
                  <c:forEach var="i" begin="${pb.startPageOfPageGroup}"
                     end="${pb.endPageOfPageGroup}">
                     <c:if test="${pb.nowPage!=i}">
                        <li><a href="searchInnByWordDateNo.do?pageNo=${i}&amenityItems=${requestScope.filterVO.amenityItems}&searchWord=${requestScope.filterVO.searchWord}&searchPeopleNo=${requestScope.filterVO.searchPeopleNo}&searchStartDate=${requestScope.filterVO.searchStartDate}&searchEndDate=${requestScope.filterVO.searchEndDate}&minPrice=${requestScope.filterVO.minPrice}&maxPrice=${requestScope.filterVO.maxPrice}&amenityCnt=${requestScope.filterVO.amenityCnt}">${i}</a>
                        <li>
                     </c:if>
                     <c:if test="${pb.nowPage==i}">
                        <li><a href="#">${i}</a></li>
                     </c:if>
                  &nbsp;               
                  </c:forEach>
         
               <c:if test="${pb.nextPageGroup}">
                  <li>
                     <a href="searchInnByWordDateNo.do?pageNo=${pb.endPageOfPageGroup+1}">Next</a>
                  </li>
               </c:if>
         
              </ul> 
         </div>    
            </div>
          </div>
        </div>
      </div>