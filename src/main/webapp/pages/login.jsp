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
<style>

  /* Tổng thể */
  body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }

  .login-container {
    background-color: #ffffff;
    padding: 30px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 400px;
  }

  h2 {
    text-align: center;
    color: #333;
  }

  label {
    font-size: 14px;
    color: #555;
    margin-bottom: 5px;
    display: block;
  }

  input[type="text"], input[type="password"] {
    width: 100%;
    padding: 12px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 14px;
    box-sizing: border-box;
  }

  button {
    width: 100%;
    padding: 12px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    cursor: pointer;
  }

  button:hover {
    background-color: #45a049;
  }

  .error-message {
    color: red;
    font-size: 14px;
    text-align: center;
    margin-top: 10px;
  }
</style>
<body>


<form action="login" method="post">
  <p><%= request.getAttribute("hello") %></p>
  <p>Đăng Nhập </p>
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

