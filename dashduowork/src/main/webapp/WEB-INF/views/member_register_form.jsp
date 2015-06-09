<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
   <meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${initParam.root}css/styles.css">

<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- 헤더에 마우스 오버시 효과주는 자바스크립트로 추정 -->
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
   <script src="${initParam.root}script.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"
	type="text/javascript"></script>
  </head>
  <body>
     <c:import url="template/header.jsp"></c:import>
    <!--여기서부터 register_form 시작-->
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <form class="form-horizontal" role="form">
              <div class="form-group">
              <h1 align="center">회원가입</h1>
                <div class="col-sm-2">
                  <label class="control-label">프로필 사진</label>
                </div>
                <div class="col-sm-8">
                  <input type="file" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputEmail3" class="control-label">Email</label>
                </div>
                <div class="col-sm-8">
                  <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">password</label>
                </div>
                <div class="col-sm-8">
                  <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputcheckPassword3" class="control-label">check password</label>
                </div>
                <div class="col-sm-8">
                  <input type="password" class="form-control" id="inputcheckPassword3" placeholder="password again">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputName3" class="control-label">name</label>
                </div>
                <div class="col-sm-8">
                  <input type="text" class="form-control" id="inputName3" placeholder="name">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPhone3" class="control-label">phone</label>
                </div>
                <div class="col-sm-8">
                  <input type="text" class="form-control" id="inputPhone3" placeholder="phone">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputInfo3" class="control-label">자기 소개</label>
                </div>
                <div class="col-sm-8">
                  <!--<input type="password" class="form-control" id="inputPassword3" placeholder="자기소개">-->
                  <textarea rows="4" cols="50" class="form-control"></textarea>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-4 col-sm-8">
                  <button type="submit" class="btn btn-default">회원가입</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <button type="button"
                  class="btn btn-default">취소</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </body>

</html>