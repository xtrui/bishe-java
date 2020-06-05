package fun.tianrui.blog.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Setting implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    String blogName;
    @Column
    String blogIntroduce;
    @Column
    String authorIntroduce;
    @Column
    String beianInfo;

    @Override
    public String toString() {
        return "Setting{" +
                "id=" + id +
                ", blogName='" + blogName + '\'' +
                ", blogIntroduce='" + blogIntroduce + '\'' +
                ", authorIntroduce='" + authorIntroduce + '\'' +
                ", beianInfo='" + beianInfo + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogIntroduce() {
        return blogIntroduce;
    }

    public void setBlogIntroduce(String blogIntroduce) {
        this.blogIntroduce = blogIntroduce;
    }

    public String getAuthorIntroduce() {
        return authorIntroduce;
    }

    public void setAuthorIntroduce(String authorIntroduce) {
        this.authorIntroduce = authorIntroduce;
    }

    public String getBeianInfo() {
        return beianInfo;
    }

    public void setBeianInfo(String beianInfo) {
        this.beianInfo = beianInfo;
    }
}
