package fun.tianrui.blog.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @author TIANRUI
 */
@Entity
public class Test implements Serializable {
    public static final long SERIAL_VERSIONUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String blog;

    @Override
    public String toString() {
        return "Test{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", blog='" + blog + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public Test() {
    }

    public Test(Long id, String name, String blog) {
        this.id = id;
        this.name = name;
        this.blog = blog;
    }
}
