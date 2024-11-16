package entity;

import entity.Favorites;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Video")
public class Videos {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", length = 255)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "Title", nullable = false, length = 255)
    private String title;

    @Column(name = "Poster", length = 255)
    private String poster;

    @Column(name = "Views")
    private int views;

    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "Active")
    private boolean active;

    @OneToMany(mappedBy = "video")
    private List<Favorites> favorites;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Favorites> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorites> favorites) {
        this.favorites = favorites;
    }
}
