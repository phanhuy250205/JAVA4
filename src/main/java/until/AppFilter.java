package until;

import Dao.Logdaolmpl;
import Dao.Logsdao;
import entity.Log;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.time.LocalDateTime;

@WebFilter("/*")
public class AppFilter implements Filter {
    private Logsdao logsdao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logsdao = new Logdaolmpl(); // Khởi tạo DAO
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        // Thiết lập UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Kiểm tra đăng nhập
        String username = httpRequest.getRemoteUser();
        if (username != null) {
            Log log = new Log();
            log.setUrl(httpRequest.getRequestURI());
            log.setTime(LocalDateTime.now());
            log.setUsername(username);

            logsdao.create(log); // Ghi log
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Giải phóng tài nguyên nếu cần
    }
}