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
import java.util.List;

@WebServlet(urlPatterns = {"/favorites"})
public class FavoriteServlet extends HttpServlet {
    private FavoriteDaoImpl favoriteDao = new FavoriteDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private VideoDaoImpl videoDao = new VideoDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Nếu yêu cầu là xem danh sách yêu thích
        if ("list".equals(action)) {
            listFavorites(request, response);
        }
        // Nếu yêu cầu là xóa video khỏi yêu thích
        else if ("delete".equals(action)) {
            deleteFavorite(request, response);
        }
        // Mặc định chuyển hướng đến danh sách yêu thích
        else {
            response.sendRedirect("favorites?action=list");
        }
    }

    private void listFavorites(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy userId từ session
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        if (userId != null) {
            // Truy vấn video yêu thích của người dùng từ cơ sở dữ liệu
            User user = userDao.findById(Integer.parseInt((String.valueOf(userId))));
            if (user != null) {
                List<Favorites> favorites = favoriteDao.findFavoritesByUser(user);  // Tìm tất cả video yêu thích của người dùng
                request.setAttribute("favorites", favorites);
                request.getRequestDispatcher("/pages/favoriteList.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found.");
            }
        } else {
            response.sendRedirect("login.jsp");  // Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
        }
    }


    // Xóa video khỏi danh sách yêu thích
    private void deleteFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String favoriteId = request.getParameter("id");

        // Kiểm tra xem ID video yêu thích có hợp lệ không
        if (favoriteId != null && !favoriteId.isEmpty()) {
            favoriteDao.deleteById(Integer.parseInt(favoriteId)); // Xóa video yêu thích bằng ID
        }

        // Sau khi xóa, quay lại danh sách yêu thích
        response.sendRedirect("favorites?action=list");
    }
}
