<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Danh sách Video Yêu Thích</title>
  <style>
    /* Các kiểu CSS cho danh sách video yêu thích */
    body {
      font-family: Arial, sans-serif;
      background-color: #f9f9f9;
      margin: 0;
      padding: 0;
    }
    table {
      width: 80%;
      margin: 0 auto;
      border-collapse: collapse;
    }
    th, td {
      padding: 10px;
      text-align: left;
      border: 1px solid #ddd;
      color: black;
    }
    th {
      background-color: #f2f2f2;
      color: white;
    }
    .btn-delete {
      color: red;
      text-decoration: none;
    }
    .btn-delete:hover {
      color: darkred;
    }
    .content{
      text-align: center;
      background-color: #888888;
    }
  </style>
</head>
<body>

<div class="content">
  <h1>Danh sách Video Yêu Thích</h1>

  <p>Xin chào, <c:out value="${sessionScope.fullname}"/>! Đây là danh sách video yêu thích của bạn.</p>
</div>

<!-- Kiểm tra nếu không có video yêu thích -->
<c:if test="${empty favorites}">
  <p>Chưa có video yêu thích nào.</p>
</c:if>

<!-- Bảng danh sách video yêu thích -->
<table>
  <thead>
  <tr>
    <th>Video</th>
    <th>Ngày Yêu Thích</th>
    <th>Hành Động</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="favorite" items="${favorites}">
    <tr>
      <td>${favorite.video.title}</td>  <!-- Hiển thị tiêu đề video yêu thích -->
      <td>${favorite.likeDate}</td>

      <td>
        <a href="favorites?action=delete&id=${favorite.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa video này khỏi danh sách yêu thích?');" class="btn-delete">Xóa</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>
