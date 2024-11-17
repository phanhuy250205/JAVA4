package server;

import Dao.ShareDaoImpl;
import Dao.Videosdao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/shareSummary")
public class ShareSummaryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ShareDaoImpl shareDao = new ShareDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Object[]> shareSummary = shareDao.getshareSummmary();

        req.setAttribute("shareSummary", shareSummary);

        req.getRequestDispatcher("/pages/shareSummary.jsp").forward(req, resp);

    }
}
