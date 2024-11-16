package server;

import Dao.FavoriteDaoImpl;
import Dao.UserDao;
import Dao.UserDaoImpl;
import Dao.VideoDaoImpl;
import entity.Favorites;
import entity.User;
import entity.Videos;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = {"/videos"})
public class VideoServlet extends HttpServlet {
    private VideoDaoImpl videoDao = new VideoDaoImpl();
    private FavoriteDaoImpl favoriteDao = new FavoriteDaoImpl();
    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            request.getRequestDispatcher("/pages/addVideo.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            editVideo(request, response);
        } else {
            listVideos(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createVideo(request, response);
        } else if ("update".equals(action)) {
            updateVideo(request, response);
        } else if ("delete".equals(action)) {
            deleteVideo(request, response);

        }else if ("addFavorite".equals(action)) {
            addFavorite(request, response);  // Xử lý yêu thích video
        }
    }

    // Tạo video mới
    private void createVideo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String poster = request.getParameter("poster");
        String description = request.getParameter("description");
        boolean active = Boolean.parseBoolean(request.getParameter("active"));

        // Xử lý views
        int views = 0;
        try {
            String viewsParam = request.getParameter("views");
            if (viewsParam != null && !viewsParam.isEmpty()) {
                views = Integer.parseInt(viewsParam);
            }
        } catch (NumberFormatException e) {
            views = 0;  // Đặt giá trị mặc định nếu không có giá trị hợp lệ
        }

        // Kiểm tra ID có hợp lệ không
        if (id == null || id.isEmpty()) {
            // Nếu id không hợp lệ, trả về lỗi hoặc thiết lập giá trị mặc định
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing video ID");
            return;
        }

        // Tạo video và lưu vào cơ sở dữ liệu
        Videos video = new Videos();
        video.setId(id);  // Sử dụng id từ request, không cần chuyển đổi sang int nữa
        video.setTitle(title);
        video.setPoster(poster);
        video.setViews(views);
        video.setDescription(description);
        video.setActive(active);

        // Lưu vào cơ sở dữ liệu
        videoDao.create(video);

        // Chuyển hướng đến danh sách video
        response.sendRedirect("videos?action=list");
    }


    // Cập nhật video
    private void updateVideo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String poster = request.getParameter("poster");
        String description = request.getParameter("description");
        boolean active = Boolean.parseBoolean(request.getParameter("active"));

        // Xử lý ngoại lệ cho trường views
        int views = 0;
        try {
            views = Integer.parseInt(request.getParameter("views"));
        } catch (NumberFormatException e) {
            views = 0;  // Đặt giá trị mặc định nếu không có giá trị hợp lệ
        }

        // Tìm video cần cập nhật và thực hiện cập nhật
        Videos video = videoDao.findById(id);
        if (video != null) {
            video.setTitle(title);
            video.setPoster(poster);
            video.setViews(views);
            video.setDescription(description);
            video.setActive(active);

            videoDao.update(video);
        }

        response.sendRedirect("videos?action=list"); // Sau khi cập nhật, chuyển hướng về danh sách video
    }

    // Chỉnh sửa video (hiển thị form chỉnh sửa)
    private void editVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        // Lấy thông tin video từ cơ sở dữ liệu
        Videos video = videoDao.findById(id);
        if (video != null) {
            request.setAttribute("video", video);
            request.getRequestDispatcher("/pages/editVideo.jsp").forward(request, response);
        } else {
            response.sendRedirect("videos?action=list");  // Nếu không tìm thấy video, quay lại danh sách
        }
    }
    private void deleteVideo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        if (id != null && !id.isEmpty()) {
            // Giả sử ID video là một chuỗi, và bạn có thể truyền nó trực tiếp vào phương thức deleteById
            videoDao.deleteById(Integer.parseInt(id));  // Dùng String cho ID

            // Trả về mã trạng thái thành công
            response.sendRedirect("videos?action=list");  // Chuyển hướng lại danh sách video sau khi xóa
        } else {
            // Nếu không có ID, trả về lỗi
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Video ID is missing");
        }
    }

    private void addFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Lấy userId từ session, cần kiểm tra lại kiểu dữ liệu của userId
        Integer userId = (Integer) request.getSession().getAttribute("userId");  // Lấy Integer, không phải String

        if (userId == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in.");
            return;
        }

        // Lấy videoId từ request
        String videoId = request.getParameter("videoId");

        // Kiểm tra xem video có tồn tại trong cơ sở dữ liệu không
        Videos video = videoDao.findById(videoId);
        if (video != null) {
            // Truy vấn thông tin người dùng từ session hoặc database
            User user = userDao.findById(userId); // Sử dụng Integer cho userId

            if (user != null) {
                // Lấy fullname từ đối tượng user
                String fullname = user.getFullname();

                // Tạo đối tượng Favorites và lưu vào cơ sở dữ liệu
                Favorites favorite = new Favorites();
                favorite.setUser(user);
                favorite.setVideo(video);
                favorite.setLikeDate(new Date());  // Thêm ngày yêu thích

                // Lưu vào cơ sở dữ liệu
                favoriteDao.create(favorite);

                // Sau khi lưu, lưu fullname vào session
                request.getSession().setAttribute("fullname", fullname);  // Đưa fullname vào session

                // Chuyển tiếp yêu cầu đến trang danh sách video yêu thích
                response.sendRedirect("favorites?action=list");
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Video not found.");
        }
    }




    // Hiển thị danh sách video
    private void listVideos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("videos", videoDao.findAll());
        request.getRequestDispatcher("/pages/videoList.jsp").forward(request, response);
    }
}
