<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
    rel="stylesheet" type="text/css">

  
<script type="text/javascript">
$(document).ready(function(){
	$('input:radio[name="checkPayment"]').change(function(){
		var checkpayment = $('input:radio[name=checkPayment]:checked').val();
		if(checkpayment=="creditcard"){
			var tableInfo="<table class='table'>"+
			"<tr>"+
			"<td class='col-md-3'>카드 선택</td>"+
			"<td class='col-md-4'><select name='cardInfo'>"+
			"<option value=''>---------------</option>"+
			"<option value='삼성카드'>삼성카드</option>"+
			"<option value='현대카드'>현대카드</option>"+
			"<option value='NH채움'>NH채움</option>"+
			"<option value='KB국민'>KB국민</option>"+
			"<option value='롯데카드'>롯데카드</option>"+
			"<option value='신한카드'>신한카드</option>"+
			"</select></td>"+
			"</tr>"+
			"<tr>"+
			"<td class='col-md-3'>할부 선택</td>"+
			"<td class='col-md-4'><select name='installmentPlan'>"+
			"<option value=''>---------------</option>"+
			"<option value='일시불'>일시불</option>"+
			"<option value='2개월'>2개월</option>"+
			"<option value='3개월'>3개월</option>"+
			"</select></td>"+
			"</tr>"+
			"<tr>"+
			"<tr><td class='col-md-3'>카드번호</td>"+
			"<td><input type='text' id='cardno1' name='cardno1' size=2 maxlength=4>"+" - "+
			"<input type='text' id='cardno2' name='cardno2' size=2 maxlength=4>"+" - "+
			"<input type='text' id='cardno3' name='cardno3' size=2 maxlength=4>"+" - "+
			"<input type='text' id='cardno4' name='cardno4' size=2 maxlength=4>"+
			"</td>"+
			"</tr>"+
			"<tr>"+
			"<td class='col-md-3'>유효 기간</td>"+
			"<td class='col-md-2'>"+
			"<select name='creditcardmonth'>"+
			"<option value=''>월</option>"+
			"<option value='1'>1</option>"+
			"<option value='2'>2</option>"+
			"<option value='3'>3</option>"+
			"<option value='4'>4</option>"+
			"<option value='5'>5</option>"+
			"<option value='6'>6</option>"+
			"<option value='7'>7</option>"+
			"<option value='8'>8</option>"+
			"<option value='9'>9</option>"+
			"<option value='10'>10</option>"+
			"<option value='11'>11</option>"+
			"<option value='12'>12</option>"+
			"</select></td>"+
			"<td class='col-md-2'><select name='creditcardyear'>"+
			"<option value=''>년</option>"+
			"<option value='2015'>2015</option>"+
			"<option value='2016'>2016</option>"+
			"<option value='2017'>2017</option>"+
			"<option value='2018'>2018</option>"+
			"<option value='2019'>2019</option>"+
			"<option value='2020'>2020</option>"+
			"</select></td>"+
			"</tr>"+
			"<tr><td class='col-md-3'>CVC</td>"+
			"<td><input type='text' id='cvc' name='cvc' size=4 maxlength=4></td>"+
			"</tr>"+
			"</table>";
			$("#paymentInfo").html("");
			$("#paymentInfo").html(tableInfo);
			var checkpayment="creditcard";
		}
	if(checkpayment=="accouttransfer"){
		var innNo=${requestScope.ivo.innNo};
		var checkpayment="accouttransfer";
		$("#paymentInfo").html("");
		$.ajax({
			type:"get",
			url:"getInnMasterAccount.do?innNo="+innNo,
			//data: "",
			dataType:"json",
			success:function(data){		
			var memberBank=data.memberBank;
			var memberBankAcount=data.memberBankAcount;
			var tableInfo="<table class='table'><tr><td>은행</td><td>계좌번호</td></tr>"+"<tr><td>"+memberBank+"</td><td>"+memberBankAcount+"</td></tr>"+"</table>"
				$("#paymentInfo").html(tableInfo);	
			}
		});//ajax 
		
	}		
	})
	$("#paybutton").click(function(){
		if($('input:radio[name=checkPayment]:checked').val()=="creditcard"){		
			if($("select[name=cardInfo]").val()==""){
				alert("카드를 선택하세요");
				return false;
			}
			if($("select[name=installmentPlan]").val()==""){
				alert("카드 할부 선택하세요");
				return false;
			}
			if($("#cardno1").val()==""||$("#cardno2").val()==""||$("#cardno3").val()==""||$("#cardno4").val()==""){
				alert("카드 번호를 입력하세요");
				return false;
			}
			if(isNaN($("#cardno1").val())==true||isNaN($("#cardno2").val())==true||isNaN($("#cardno3").val())==true||isNaN($("#cardno4").val())==true){
				alert("카드 번호를 숫자로 입력하세요");
				return false;
			}
			if($("select[name=creditcardmonth]").val()==""){
				alert("카드 유효 기간을 선택하세요");
				return false;
			}
			if($("select[name=creditcardyear]").val()==""){
				alert("카드 유효 기간을 선택하세요");
				return false;
			}	
			if($("#cvc").val()==""){
				alert("CVC를 입력하세요");
				return false;
			}
			if(isNaN($("#cvc").val())==true){
				alert("CVC를 숫자로 입력하세요");
				return false;
			}
		}
		
		if($('input:radio[name=checkPayment]:checked').val()==null){
			alert("결제방식을 선택하세요");
			 return false;
		}
		 if(!(confirm("해당 숙소를 예약하시겠습니까?"))){
			 return false;
		 }
})
})


</script>
        <div class="container">
          <div class="row text-center">
            <div class="col-md-12 text-center">
              <ul class="lead nav nav-justified nav-tabs">
                <li>
                  <a href='member_myprofile.do?memberId=${sessionScope.mvo.memberId}'>내프로필</a>
                </li>
                <li>
                  <a href='get_myinnlist.do'>숙소등록</a>
                </li>
                <li>
                  <a href='get_mybooklist.do'>예약숙소</a>
                </li>
                <li>
                  <a href="get_mytradelist.do">거래내역</a>
                </li>
                <li>
                  <a href='get_mywishlist.do'>위시리스트</a>
                </li>
                <li>
                  <a href="get_innReservation_list.do">예약관리</a>
                </li>
              </ul>
            </div>
          </div>
        </div>

            <div class="section">
        <div class="container">
          <div class="row">
            <div class="col-md-1">
                 <center>
                <img src="${requestScope.member.profilePicVO.filePath}"
               align="center" class="img-circle img-responsive" style="margin-top: 60px; margin-bottom: 50px; " width="50" height="70" >
              </center>
            </div>
            <div class="col-md-5" style="margin-top: 50px; margin-bottom: 50px; ">
              <h2>${sessionScope.mvo.memberName}님  님 결제페이지</h2>
            </div>
          </div>
        </div>
      </div>
      <div class="section text-center">
        <div class="container">
          <div class="row">
            <div class="col-md-15">
            			<div class="col-md-6">
				<table class="table">
						<thead colspan="2">
							<tr>
								<td>${sessionScope.mvo.memberName}님 예약 정보</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="col-md-1 " colspan="2">
								${requestScope.ivo.innName} 
								</td>
								</tr>
								<tr>
								<td  class="col-md-1"> <a class="pull-left" href="#"><img class="media-object" src="${requestScope.innMainPic.filePath}" height="150" width="150"></td>
							</tr>
							<tr>
								<td class="col-md-3">체크인 날짜</td>
								<td class="col-md-4">
								${requestScope.bookInfo.bookCheckIn}
								</td>	
							</tr>
							<tr>
							<td class="col-md-3">체크아웃 날짜</td>
								<td class="col-md-4">
								${requestScope.bookInfo.bookCheckOut}
								</td>
								</tr>
								<tr>
									<tr>
							<td class="col-md-3">숙박 인원</td>
								<td class="col-md-4">
								${requestScope.bookInfo.bookCount}
								</td>
								</tr>
								<td class="col-md-3">총 금액</td>
								<td class="col-md-4">
								${requestScope.payTotalPrice}
								</td>
							</tr>
						</tbody>
					</table>
					</div>
             <div class="col-md-6">
				<form action="book.do">
					<table class="table">
						<thead colspan="2">
							<tr>
								<td>결제 정보 입력</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="col-md-7 " colspan="2">
								<input type="radio" name="checkPayment" value="creditcard">신용카드결제
									<input type="radio" name="checkPayment" value="accouttransfer">무통장입금</td>
							</tr>
						</tbody>
					</table>
						<div id="paymentInfo"></div>
						<input type="submit" id="paybutton" name="paybutton"
							class="btn btn-danger btn-lg col-sm-10 input-lg" value="결제하기">
							<input type="hidden" id="innNo" name="innNo" value="${requestScope.ivo.innNo}">
							<input type="hidden" id="bookCheckIn" name="bookCheckIn" value="${requestScope.bookInfo.bookCheckIn}">
							<input type="hidden" id="bookCheckOut" name="bookCheckOut" value="${requestScope.bookInfo.bookCheckOut}">
							<input type="hidden" id="bookCount" name="bookCount" value="${requestScope.bookInfo.bookCount}">
							<input type="hidden" id="payTotalPrice" name="payTotalPrice" value="${requestScope.payTotalPrice}">
				</form>
				</div>
			</div>
		</div>
	</div>
</div>
