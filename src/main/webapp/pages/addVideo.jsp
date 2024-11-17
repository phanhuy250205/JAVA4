<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Thêm Video Mới</title>
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
</head>
<body>

<form action="videos?action=create" method="post"> <!-- Đảm bảo action trỏ đến servlet -->
  <label for="id">ID Video YouTube:</label>
  <input type="text" name="id" id="id"  placeholder="Nhập ID của video trên YouTube">

  <label for="title">Tiêu đề:</label>
  <input type="text" name="title" id="title" required placeholder="Nhập tiêu đề video">

  <label for="poster">Poster URL:</label>
  <input type="text" name="poster" id="poster" placeholder="Nhập URL của poster video (tùy chọn)">

  <label for="views">Lượt xem:</label>
  <input type="number" name="views" id="views" value="0" min="0" placeholder="Số lượt xem (mặc định là 0)">

  <label for="description">Mô tả:</label>
  <textarea name="description" id="description" placeholder="Nhập mô tả video (tùy chọn)"></textarea>

  <label for="active">Trạng thái:</label>
  <select name="active" id="active">
    <option value="true">Hoạt động</option>
    <option value="false">Không hoạt động</option>
  </select>

  <button type="submit">Lưu Video</button>
  <a href="videos?action=list">Quay lại danh sách</a>
</form>

</body>
</html>
