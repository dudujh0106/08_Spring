<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 25. 6. 10.
  Time: 오후 3:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>파라미터 입력 페이지</title>
</head>
<body>
  <h3>DTO를 이용한 파라미터 수집</h3>
  <%--
      input의 name 속성이 제출되는 파라미터의 "Key"
   --%>
  <form action="/sample/ex01" method="POST">
    name : <input type="text" name="name" placeholder="이름 입력">
    <br>
    age : <input type="number" name="age" placeholder="나이 입력">
    <br>
    <button>제출하기</button>
  </form>

  <hr>

  <h3>@RequestParam을 이용한 개별 파라미터 수집</h3>
  <%--
      input의 name 속성이 제출되는 파라미터의 "Key"
   --%>
  <form action="/sample/ex02-advanced" method="GET">
    <button>제출하기</button>
  </form>
</body>
</html>
