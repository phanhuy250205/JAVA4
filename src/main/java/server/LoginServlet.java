package server;

import Dao.Logdaolmpl;
import Dao.Logsdao;
import Dao.UserDao;
import Dao.UserDaoImpl;
import entity.Log;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

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
            // Lưu userId và fullname vào session
            request.getSession().setAttribute("userId", user.getId());
            request.getSession().setAttribute("fullname", user.getFullname());

            // Ghi log đăng nhập
            logAccess(request, user.getEmail());

            // Chuyển hướng đến trang video
            response.sendRedirect("videos?action=list");
        } else {
            // Hiển thị thông báo lỗi
            String errorMessage = "Invalid username or password.";
            response.sendRedirect("login.jsp?error=" + errorMessage);
        }
    }

    private void logAccess(HttpServletRequest request, String username) {
        // Sử dụng LogsDAO để lưu thông tin đăng nhập vào CSDL
        Logsdao logsDao = new Logdaolmpl();
        Log log = new Log();
        log.setUrl(request.getRequestURI());
        log.setTime(LocalDateTime.now());
        log.setUsername(username);
        logsDao.create(log);

    }
}
