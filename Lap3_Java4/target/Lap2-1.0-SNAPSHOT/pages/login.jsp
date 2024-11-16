<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 15/11/2024
  Time: 2:35 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Đăng Nhập</title>
</head>
<body>
<h2>Đăng Nhập</h2>

<form action="login" method="post">
  <label for="username">Tên đăng nhập:</label>
  <input type="text" id="username" name="username" required><br>

  <label for="password">Mật khẩu:</label>
  <input type="password" id="password" name="password" required><br>

  <button type="submit">Đăng Nhập</button>
</form>

<!-- Hiển thị thông báo lỗi nếu có -->
<c:if test="${not empty error}">
  <p style="color: red;">${error}</p>
</c:if>
</body>
</html>

