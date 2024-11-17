<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 17/11/2024
  Time: 4:23 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    body{
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        margin: 0;
        padding: 0;
    }
    h1{
        text-align: center;
        color: #333;
        margin: 20px 0;
    }
    .container{
        width: 80%;
        margin: 0 auto;
        padding: 20px;
    }
    table{
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
        text-align: center;
        align-items: center;
    }
    th , td{
        padding: 10px;
        text-align: left;
        border: 1px solid #ddd;


    }
    th{
        background-color: #f2f2f2;
    }
    .btn{
        padding: 8px 15px;
        color: white;
        background-color: rgba(44, 86, 134, 0.55);
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
</style>
<body>
        <h1>Thông tin chia sẻ của các video </h1>
        <div class="container">
                <table>
                    <thead>
                    <tr>
                        <th>Tiêu Đề Video </th>
                        <th>Số Lượt Chia sẽ</th>
                        <th>Ngày Chia sẽ đầu tiên</th>
                        <th>Ngày Chia Sẽ cuối cùng</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach   var="share" items="${shareSummary}">
                        <tr>
                            <td>${share[0]}</td>
                            <td>${share[1]}</td>
                            <td>${share[2]}</td>
                            <td>${share[3]}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            <a href="videos?action=list" class="btn">Quay lại danh sách video</a>
        </div>
</body>
</html>
