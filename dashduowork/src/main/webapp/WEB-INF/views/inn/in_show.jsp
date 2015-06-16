<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="false" %>
<html>
    
  <head>
    <!-- <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js">
    </script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
    rel="stylesheet" type="text/css"> -->
      <script type="text/javascript">
   $(function() {
      $("#availableDateSt, #availableDateEnd").datepicker({
         dateFormat : 'yy-mm-dd'
      });
   });
  </script>
    <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
    rel="stylesheet" type="text/css">
  </head>

  <body>
  
<div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-5">
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-5">
            <div id="fullcarousel-example" data-interval="false" class="carousel slide"
            data-ride="carousel">
              <div class="carousel-inner">
                <div class="item active">
                  <img src="http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png">
                </div>
              </div>
              <a class="left carousel-control" href="#fullcarousel-example" data-slide="prev"><i class="icon-prev fa fa-angle-left"></i></a>
              <a class="right carousel-control" href="#fullcarousel-example" data-slide="next"><i class="icon-next fa fa-angle-right"></i></a>
            </div>
          </div>
          <div class="col-md-7">
            <form class="form-horizontal" role="form">
              <div class="form-group">
                <div class="col-sm-4">
                  <label for="inputEmail3" class="control-label input-lg">체크인</label>
                </div>
                <div class="col-sm-8">
                  <input type="text" class="form-control" name="availableDateSt" id="availableDateSt" size="15" onfocus="this.value=''" placeholder="Checkin">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-4">
                  <label for="inputEmail3" class="control-label input-lg">체크아웃</label>
                </div>
                <div class="col-sm-8">
                 <input type="text" class="form-control" name="availableDateEnd" size="15" id="availableDateEnd"
         onfocus="this.value=''" placeholder="Checkout">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-4">
                  <label class="control-label input-lg">인원수</label>
                </div>
                <div class="col-sm-8">
                  <select class="form-control input-lg">
                    <option>1</option>
                    <option>2</option>
                  </select>
                </div>
              </div>
              <div class="form-group has-feedback">
                <div class="col-sm-4">
                  <label for="inputEmail3" class="control-label input-lg">총가격</label>
                </div>
                <div class="col-sm-8">
                  <input type="text" class="form-control input-lg" id="inputEmail3" placeholder="가격">
                </div>
              </div>
              <center>
                <a class="btn btn-danger btn-lg col-sm-12 input-lg">예약하기</a>
              </center>
            </form>
          </div>
        </div>
      </div>
    </div>
    </div>
    </div>
   </div>
   </div>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <form class="form-horizontal text-center" role="form">
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputEmail3" class="control-label input-lg">숙소명</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control input-lg" id="inputEmail3" placeholder="숙소명">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputEmail3" class="control-label input-lg">주소</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control input-lg" id="inputEmail3" placeholder="주소">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label class="control-label input-lg">상세보기</label>
                </div>
                <div class="col-sm-10">
                  <textarea class="form-control " placeholder="상세보기" cols="40" rows="10"></textarea>
                </div>
              </div>
              <div class="row">
                <div class="col-md-2">
                  <img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png"
                  class="img-circle img-responsive" width="200" height="200">
                </div>
                <div class="col-md-5 text-left">
                  <label class="control-label input-lg">이름</label>
                  <input class="form-control input-lg " type="text" placeholder="이름">
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </body>

</html>