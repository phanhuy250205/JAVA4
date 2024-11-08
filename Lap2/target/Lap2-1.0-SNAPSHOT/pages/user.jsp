<%-- Được tạo bởi IntelliJ IDEA. Người dùng: admin Ngày: 3/11/2024 Thời gian: 6:45 tối
     Để thay đổi mẫu này hãy sử dụng Tệp | Cài đặt | Tệp Mẫu. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Tài liệu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/user.css" />
</head>
<body>
<i>${message}</i>

<div class="session-one">
    <div class="container mt-3">
        <div class="row">
            <form class="form" method="post">
                <label for="id">Mã số</label>
                <input type="text" id="id" name="id" value="${user.id}" readonly />

                <label for="password">Mật khẩu</label>
                <input type="password" id="password" name="password" value="${user.password}" />

                <label for="fullname">Họ tên</label>
                <input type="text" id="fullname" name="fullname" value="${user.fullname}" />

                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="${user.email}" />

                <label>Vai trò</label>
                <div>
                    <input id="admin" name="admin" type="radio" value="true" ${user.admin != null && user.admin ? 'checked' : ''}>
                    <label for="admin">Quản trị viên</label>
                    <input id="not_admin" name="admin" type="radio" value="false" ${user.admin != null && !user.admin ? 'checked' : ''}>
                    <label for="not_admin">Nhân viên</label>
                </div>

                <div class="button-group">
                    <button type="submit" class="btn btn-primary" formaction="${pageContext.request.contextPath}/user/crud/create">Tạo mới</button>
                    <button type="submit" class="btn btn-primary" formaction="${pageContext.request.contextPath}/user/crud/update">Cập nhật</button>
                    <button type="submit" class="btn btn-secondary" formaction="${pageContext.request.contextPath}/user/crud/delete">Xóa</button>
                    <button type="submit" class="btn btn-success" formaction="${pageContext.request.contextPath}/user/crud/reset">Đặt lại</button>
                </div>
            </form>

            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Mã số</th>
                    <th>Mật khẩu</th>
                    <th>Họ tên</th>
                    <th>Email</th>
                    <th>Vai trò</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="u" items="${users}">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.password}</td>
                        <td>${u.fullname}</td>
                        <td>${u.email}</td>
                        <td>${u.admin ? 'Quản trị viên' : 'Nhân viên'}</td>
                        <td><a href="${pageContext.request.contextPath}/user/crud/edit?id=${u.id}">Chỉnh sửa</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
<style>
    body {
        background-image: url('https://thietbiketnoi.com/wp-content/uploads/2020/01/tong-hop-hinh-nen-background-vector-designer-dep-do-phan-giai-fhd-2k-4k-moi-nhat-4-1024x512.jpg');
        background-repeat: no-repeat;
        background-size: cover;
        background-position: center;
        background-attachment: fixed;
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 0;
        padding: 0;
        height: 100vh;
        font-family: Arial, sans-serif;
    }

    .session-one {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 100vh;
        backdrop-filter: blur(10px);
    }

    .container {
        max-width: 750px;
        width: 100%;
        padding: 25px;
        background-color: rgba(255, 255, 255, 0.8);
        border-radius: 12px;
        box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3);
    }

    .form label {
        font-size: 15px;
        color: #333;
        margin-bottom: 10px;
        font-weight: bold;
    }

    .form input,
    .form select {
        width: 100%;
        padding: 12px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 15px;
        transition: border-color 0.3s, box-shadow 0.3s;
    }

    .form input:focus,
    .form select:focus {
        outline: none;
        border-color: #007bff;
        box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
    }

    .button-group .btn {
        margin-top: 25px;
        padding: 10px 20px;
        font-size: 15px;
    }

    .table {
        margin-top: 30px;
        background-color: white;
        border-radius: 8px;
        overflow: hidden;
    }

    .table th,
    .table td {
        text-align: center;
        vertical-align: middle;
    }

    .table-hover tbody tr:hover {
        background-color: rgba(0, 123, 255, 0.1);
    }
</style>
</html>
