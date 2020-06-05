package fun.tianrui.blog.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "user")
public class UserB implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    String username;
    @Column
    String password;
    @Column
    String email;
    @Column
    Boolean role;

//    @JsonIgnoreProperties(value = {"articles"})
//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<Category> categories;

    @OneToMany(targetEntity = Comment.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("time DESC ")
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }
}
