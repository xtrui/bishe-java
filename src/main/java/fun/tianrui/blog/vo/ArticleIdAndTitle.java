package fun.tianrui.blog.vo;

import java.time.LocalDateTime;

public class ArticleIdAndTitle {
    String title;
    Long id;
    LocalDateTime time;
    String postSrc;

    @Override
    public String toString() {
        return "ArticleIdAndTitle{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", time=" + time +
                ", postSrc='" + postSrc + '\'' +
                '}';
    }

    public String getPostSrc() {
        return postSrc;
    }

    public void setPostSrc(String postSrc) {
        this.postSrc = postSrc;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public ArticleIdAndTitle() {
    }

    public ArticleIdAndTitle(String title, Long id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
