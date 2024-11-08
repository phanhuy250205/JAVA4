package Servlet;

import Dao.UserDAOImpl;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;


@WebServlet(urlPatterns = {"/user/crud/*"})
public class UserServlet extends HttpServlet {

    private final UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Service method called.");
        User form = new User();

        // Populate form with request parameters
        try {
            BeanUtils.populate(form, req.getParameterMap());
            System.out.println("Populated form data: " + form);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error populating form data.");
            e.printStackTrace();
        }

        String message = "";
        String path = req.getPathInfo(); // Get the URL path after /user/crud/
        System.out.println("Requested path: " + path);

        if (path == null) {
            message = "Path not specified!";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/pages/user.jsp").forward(req, resp);

            return;
        }

        // Handle various cases based on the path
        switch (path) {
            case "/edit":
                System.out.println("Handling edit operation.");
                handleEdit(req, form);
                message = "Edit user with ID: " + form.getId();
                break;

            case "/create":
                System.out.println("Handling create operation.");
                userDAO.create(form);
                message = "User created with ID: " + form.getId();
                form = new User(); // Reset form after creation
                break;

            case "/update":
                System.out.println("Handling update operation.");
                userDAO.update(form);
                message = "User updated with ID: " + form.getId();
                break;

            case "/delete":
                System.out.println("Handling delete operation.");
                handleDelete(req);
                message = "User deleted successfully";
                form = new User(); // Reset form after deletion
                break;

            case "/reset":
                System.out.println("Handling reset operation.");
                form = new User(); // Reset form to blank
                message = "Form reset";
                break;

            default:
                System.out.println("Default case: Enter user information.");
                message = "Enter user information";
                break;
        }

        // Get list of all users and forward to JSP
        List<User> list = userDAO.findAll();
        System.out.println("Retrieved user list: " + list);
        req.setAttribute("message", message);
        req.setAttribute("user", form);
        req.setAttribute("users", list);

        req.getRequestDispatcher("/pages/user.jsp").forward(req, resp);
        System.out.println("Forwarded to /pages/user.jsp with message: " + message);
    }

    private void handleEdit(HttpServletRequest req, User form) {
        String idStr = req.getParameter("id");
        System.out.println("Edit operation - ID from parameter: " + idStr);

        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                User user = userDAO.findById(id);
                if (user != null) {
                    BeanUtils.copyProperties(form, user);
                    System.out.println("User found and properties copied: " + user);
                } else {
                    System.out.println("User not found with ID: " + id);
                    req.setAttribute("message", "User not found with ID: " + id);
                }
            } catch (NumberFormatException | IllegalAccessException | InvocationTargetException e) {
                System.out.println("Invalid ID format or error occurred.");
                req.setAttribute("message", "Invalid ID format or error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No ID specified for editing.");
            req.setAttribute("message", "No ID specified for editing.");
        }
    }

    private void handleDelete(HttpServletRequest req) {
        String idStr = req.getParameter("id");
        System.out.println("Delete operation - ID from parameter: " + idStr);

        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                userDAO.deleteById(id);
                System.out.println("User deleted successfully.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format for deletion.");
                req.setAttribute("message", "Invalid ID format for deletion.");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error occurred while deleting user.");
                req.setAttribute("message", "Error occurred while deleting user.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No ID specified for deletion.");
            req.setAttribute("message", "No ID specified for deletion.");
        }
    }
}
