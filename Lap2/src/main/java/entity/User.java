package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")  // Đổi tên bảng để khớp với thiết kế cơ sở dữ liệu
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động sinh ID
    private int id;

    private String password;

    private String fullname;  // Đổi tên biến để phù hợp với cách bạn dùng trong JSP

    private String email;

    @Column(name = "Admin", nullable = false)  // Khớp với tên cột và không cho phép NULL
    private Boolean admin;  // Dùng kiểu Boolean để tương thích với BIT trong SQL Server

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", admin=" + admin +
                '}';
    }
}

