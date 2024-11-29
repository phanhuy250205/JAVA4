package until;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebListener
public class VisitorCounterListener implements ServletContextListener , HttpSessionListener {
        private int visitorCount = 0;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Đọc số đếm từ file trong thư mục "until"
        visitorCount = loadVisitorCountFromStorage();
        sce.getServletContext().setAttribute("visitors", visitorCount);
    }
    public  void sessionCreated(HttpSessionEvent se) {
        visitorCount++;
        se.getSession().setAttribute("visitors", visitorCount);
    }
    public  void contextDestroyed(ServletContextEvent sce) {
        saveVisitorCountToStorage(visitorCount);
    }
    private int loadVisitorCountFromStorage() {
        try {
            Path path = Paths.get("until/visitor_count.txt");
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.writeString(path , "0");
            }
            return Integer.parseInt(Files.readString(path).trim());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void saveVisitorCountToStorage(int count) {
        try {
            Path path = Paths.get("until/visitor_count.txt");
            Files.writeString(path , String.valueOf(count));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
