<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Spring Expression Language (SpEL) 선언부 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="jquery-1.11.0.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#inputcheckPassword3").keyup(
						function() {
							if ($("#inputcheckPassword3").val() != $(
									"#inputPassword3").val()) {
								$("#passCheckView").html("비밀번호가 다릅니다!").css(
										"background", "#ffe4e1");
								$("#regOk").hide();

							} else if ($("#inputcheckPassword3").val() == $(
									"#inputPassword3").val()) {
								$("#passCheckView").html("비밀번호같아용~~").css(
										"background", "#f0ffff");
								$("#regOk").show();
							}
						});
				$("#inputEmail3").keyup(function() {
					var memberId = $("#inputEmail3").val().trim();
					//trim() : 공란제거
					$("#checkResult").text("").css("background", "white");
					$.ajax({
						type : "post",
						url : "member_idcheck.do?memberId=" + memberId,
						success : function(data) {
							if (data == "fail") {
								alert(memberId + " 아이디가 중복됩니다.");
								$("#regOk").hide();
							} else {
								$("#regOk").show();
							}
						}
					});
				});
				$("#regCancel").click(function() {
					location.href = "home.do";
				});
			});
</script>

<!--여기서부터 register_form 시작-->
<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<form:form class="form-horizontal" role="form"
					action="${initParam.root}member_register.do"
					enctype="multipart/form-data" method="post" commandName="memberVO"
					id="regForm">
					<div class="form-group">
						<h1 align="center">회원가입</h1>
						<div class="col-sm-2">
							<label class="control-label">프로필 사진</label>
						</div>
						<div class="col-sm-8">
							<input type="file" class="form-control" name="file">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputEmail3" class="control-label">id</label>
						</div>
						<div class="col-sm-8">
							<form:input type="email" class="form-control" id="inputEmail3"
								placeholder="Email" name="memberId" path="memberId" />
						</div>
						<span id="checkResult"></span> <font color="red"><form:errors
								path="memberId"></form:errors></font>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputPassword3" class="control-label">password</label>
						</div>
						<div class="col-sm-8">
							<form:input type="password" class="form-control"
								id="inputPassword3" placeholder="Password" name="memberPass"
								path="memberPass" />
						</div>
						<font color="red"><form:errors path="memberPass"></form:errors></font>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputcheckPassword3" class="control-label">check
								password</label>
						</div>
						<div class="col-sm-8">
							<input type="password" class="form-control"
								id="inputcheckPassword3" placeholder="password again"
								required="required" />
						</div>
						<span id="passCheckView"></span>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputName3" class="control-label">name</label>
						</div>
						<div class="col-sm-8">
							<form:input type="text" class="form-control" id="inputName3"
								placeholder="name" name="memberName" path="memberName" />
						</div>
						<font color="red"><form:errors path="memberName"></form:errors></font>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputPhone3" class="control-label">phone</label>
						</div>
						<div class="col-sm-8">
							<form:input type="text" class="form-control" id="inputPhone3"
								placeholder="phone" name="memberTel" path="memberTel" />
						</div>
						<font color="red"><form:errors path="memberTel"></form:errors></font>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputInfo3" class="control-label">자기 소개</label>
						</div>
						<div class="col-sm-8">
							<form:textarea rows="4" cols="50" class="form-control"
								name="memberInfo" placeholder="자기소개" path="memberInfo"></form:textarea>
						</div>
						<font color="red"><form:errors path="memberInfo"></form:errors></font>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputInfo3" class="control-label">비밀번호 체크 문제</label>
						</div>
						<div class="col-sm-8">
				  <select class="form-control" name="memberAnswerType" id="memberAnswerType" required="required">
               			 <option value="">질문을 선택하세요. 비밀번호 분실시 힌트를 제공하기 위함입니다.</option>
 						 <option value="나의 신발사이즈는?">나의 신발사이즈는?</option>
 						 <option value="나의 보물 1호는?">나의 보물 1호는?</option>
 						 <option value="나의 부모님 성함은?">나의 부모님 성함은?</option>
 					     <option value="나의 초등학교는?">나의 초등학교는?</option>
  		          </select>
						</div>
						<font color="red"><form:errors path="memberAnswerType"></form:errors></font>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputInfo3" class="control-label">비밀번호 체크 정답</label>
						</div>
						<div class="col-sm-8">
							<form:input type="text" class="form-control" id="memberPassAnswer"
								placeholder="질문에 대한 정답을 적어주세요." name="memberPassAnswer" path="memberPassAnswer" />
						</div>
						<font color="red"><form:errors path="memberPassAnswer"></form:errors></font>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-default" id="regOk">회원가입</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-default" id="regCancel">취소</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>