package until;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebFilter("/*")
public class Filter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter nếu cần
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        // Kiểm tra nếu người dùng chưa đăng nhập (session không chứa thông tin đăng nhập)
        if (session.getAttribute("userId") == null) {
            // Nếu chưa đăng nhập, đặt thuộc tính cho filter 1
            req.setAttribute("hello", "Tôi là filter 1");
        }

        // Tiếp tục chuỗi filter
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // Dọn dẹp tài nguyên nếu cần
    }
}
