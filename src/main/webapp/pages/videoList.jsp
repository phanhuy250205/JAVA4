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
            color: black;
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
        }

        .welcom {
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

    <script>
        // AJAX function to handle search and update video list
        function searchVideos() {
            var keyword = document.getElementById("keyword").value;

            // Create a new XMLHttpRequest object
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "videos?action=search&keyword=" + encodeURIComponent(keyword), true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            xhr.onload = function() {
                if (xhr.status === 200) {
                    document.getElementById("video-list").innerHTML = xhr.responseText;
                }
            };

            xhr.send();
        }
    </script>
</head>
<body>

<form id="search-form" onsubmit="event.preventDefault(); searchVideos();">
    <input type="text" id="keyword" name="keyword" placeholder="Nhập từ khóa tìm kiếm..." value="${param.keyword}">
    <button type="submit">Tìm kiếm</button>
</form>

<p class="welcom">Xin chào, <c:out value="${sessionScope.fullname}"/>! Đây là Danh Sách Video</p>
<div class="container">
    <h1>Danh sách Video</h1>

    <a href="videos?action=create" class="btn btn-create">Thêm Video Mới</a>

    <!-- Danh sách video sẽ được hiển thị ở đây -->
    <div class="video-list" id="video-list">
        <c:forEach var="video" items="${videos}">
            <div class="video-card">
                <iframe width="550px" height="550px" src="https://www.youtube.com/embed/${video.poster}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                <div class="video-details">
                    <div class="video-title">${video.title}</div>
                    <div class="video-info">
                        <span>${video.views} lượt xem</span>
                        <span>${video.description}</span>
                    </div>
                    <div class="video-actions">
                        <c:if test="${not empty sessionScope.userId}">
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
