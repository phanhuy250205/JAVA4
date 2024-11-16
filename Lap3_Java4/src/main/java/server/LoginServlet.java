package server;

import Dao.UserDao;
import Dao.UserDaoImpl;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Kiểm tra nếu người dùng đã đăng nhập rồi, chuyển hướng đến trang video nếu đã có session
        if (request.getSession().getAttribute("userId") != null) {
            response.sendRedirect("videos?action=list");
        } else {
            // Nếu chưa đăng nhập, hiển thị form đăng nhập
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kiểm tra thông tin đăng nhập
        User user = userDao.findByUsernameAndPassword(username, password);

        if (user != null) {
            // Nếu đăng nhập thành công, lưu userId và fullname vào session
            request.getSession().setAttribute("userId", user.getId());
            request.getSession().setAttribute("fullname", user.getFullname());  // Lưu fullname vào session

            // Chuyển hướng đến trang video sau khi đăng nhập thành công
            response.sendRedirect("videos?action=list");
        } else {
            // Nếu đăng nhập không thành công, chuyển hướng và hiển thị thông báo lỗi
            String errorMessage = "Invalid username or password.";
            response.sendRedirect("login.jsp?error=" + errorMessage);
        }
    }
}
