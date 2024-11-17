package entity;

import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = "Share")
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "VideoId", nullable = false)
    private Videos video;

    @Column(name = "Emails", columnDefinition = "NVARCHAR(MAX)")
    private String emails;

    @Temporal(TemporalType.DATE)
    @Column(name = "ShareDate")
    private Date shareDate;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Videos getVideo() {
        return video;
    }

    public void setVideo(Videos video) {
        this.video = video;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Date getShareDate() {
        return shareDate;
    }

    public void setShareDate(Date shareDate) {
        this.shareDate = shareDate;
    }
}
