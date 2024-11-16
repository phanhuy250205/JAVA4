package entity;

import entity.User;
import entity.Videos;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Favorite", uniqueConstraints = {@UniqueConstraint(columnNames = {"UserId", "VideoId"})})
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "VideoId", nullable = false)
    private Videos video;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @Temporal(TemporalType.DATE)
    @Column(name = "LikeDate")
    private Date likeDate;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Videos getVideo() {
        return video;
    }

    public void setVideo(Videos video) {
        this.video = video;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }
}
