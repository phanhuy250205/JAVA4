<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Chỉnh Sửa Video</title>
</head>
<body>

<form action="videos?action=update" method="post">
  <input type="hidden" name="id" value="${video.id}">

  <label for="title">Tiêu đề:</label>
  <input type="text" name="title" id="title" value="${video.title}" required><br><br>

  <label for="poster">ID Video YouTube:</label>
  <input type="text" name="poster" id="poster" value="${video.poster}" required><br><br>

  <label for="views">Lượt xem:</label>
  <input type="number" name="views" id="views" value="${video.views}"><br><br>

  <label for="description">Mô tả:</label>
  <textarea name="description" id="description">${video.description}</textarea><br><br>

  <label for="active">Trạng thái:</label>
  <select name="active" id="active">
    <option value="true" ${video.active ? 'selected' : ''}>Hoạt động</option>
    <option value="false" ${!video.active ? 'selected' : ''}>Không hoạt động</option>
  </select><br><br>

  <button type="submit">Cập Nhật Video</button>
  <a href="videos?action=list">Quay lại danh sách</a>
</form>

<!-- Hiển thị video nhúng -->
<h3>Video Preview:</h3>
<iframe width="560" height="315" src="https://www.youtube.com/embed/${video.poster}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

</body>
<style>
  body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f9;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }

  h1 {
    text-align: center;
    color: #333;
    margin-bottom: 20px;
  }

  form {
    width: 100%;
    max-width: 600px;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }

  label {
    display: block;
    font-weight: bold;
    margin-top: 10px;
    color: #333;
  }

  input[type="text"],
  input[type="number"],
  textarea,
  select {
    width: 100%;
    padding: 10px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
  }

  textarea {
    resize: vertical;
    height: 120px;
  }

  button {
    width: 100%;
    padding: 12px;
    background-color: #007bff;
    color: white;
    font-size: 1.1em;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    margin-top: 15px;
    transition: background-color 0.3s ease;
  }

  button:hover {
    background-color: #0056b3;
  }

  a {
    display: inline-block;
    margin-top: 15px;
    text-align: center;
    color: #007bff;
    text-decoration: none;
    font-size: 0.9em;
  }

  a:hover {
    text-decoration: underline;
  }
</style>
</html>
