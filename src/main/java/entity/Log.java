package entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class Log {
   @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "Url")
    private String url;


    @Column(name = "Time")
    private LocalDateTime time;


    @Column(name = "Username")
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
