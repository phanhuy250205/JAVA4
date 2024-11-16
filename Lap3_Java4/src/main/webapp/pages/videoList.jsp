<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách Video</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #333;
            margin: 20px 0;
        }

        .container {
            width: 100%;
            text-align: center;
            margin: 0 auto;
            padding: 20px;
        }

        .btn {
            padding: 8px 15px;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .btn-create {
            background-color: #ff5722;
            margin-bottom: 20px;
            font-size: 16px;
        }

        .video-card {
            display: inline-block;
            width: calc(33.33% - 20px);
            margin: 10px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            /*transition: transform 0.3s ease-in-out;*/
        }



        .video-thumbnail {
            width: 100%;
            height: 180px;
            object-fit: cover;
        }

        .video-details {
            padding: 15px;
        }

        .video-title {
            font-size: 18px;
            color: #333;
            margin-bottom: 10px;
            font-weight: bold;
        }

        .video-info {
            color: #888;
            font-size: 14px;
            margin-bottom: 15px;
        }

        .video-actions {
            display: flex;
            justify-content: space-between;
        }

        .video-actions a {
            text-decoration: none;
            color: #007bff;
            font-size: 14px;
            padding: 5px 10px;
            border-radius: 5px;
            background-color: #f1f1f1;
        }

        .video-actions a:hover {
            background-color: #007bff;
            color: white;
        }

        .btn-delete {
            background-color: #dc3545;
        }
        .welcom{
            text-align: center;
            font-size: 1.3rem;
        }
        /* Responsive Layout */
        @media (max-width: 768px) {
            .video-card {
                width: calc(50% - 20px);
            }
        }

        @media (max-width: 480px) {
            .video-card {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<p class="welcom"  >Xin chào, <c:out value="${sessionScope.fullname}"/>! Đây là Danh Sách Video</p>
<div class="container">
    <h1>Danh sách Video</h1>

    <!-- Nút thêm video mới -->
    <a href="videos?action=create" class="btn btn-create">Thêm Video Mới</a>

    <!-- Danh sách video -->
    <div class="video-list">
        <c:forEach var="video" items="${videos}">
            <div class="video-card">
                <!-- Nhúng iframe để hiển thị video YouTube -->
                <iframe width="550px" height="550px" src="https://www.youtube.com/embed/${video.poster}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                <div class="video-details">
                    <div class="video-title">${video.title}</div>
                    <div class="video-info">
                        <span>${video.views} lượt xem</span>
                        <span>${video.description}</span>
                    </div>
                    <div class="video-actions">

                      <c:if test="${not empty sessionScope.userId}">
                            <!-- Form yêu thích -->
                            <form action="videos?action=addFavorite" method="post">
                                <input type="hidden" name="videoId" value="${video.id}">
                                <button type="submit" class="btn btn-like">Yêu thích</button>
                            </form>
                        </c:if>

                        <a href="videos?action=edit&id=${video.id}" class="btn btn-edit">Sửa</a>

                        <form action="videos?action=delete" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="${video.id}">
                            <button type="submit" class="btn btn-delete" onclick="return confirm('Bạn có chắc chắn muốn xóa video này?');">Xóa</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>


    </div>
</div>

</body>
</html>
