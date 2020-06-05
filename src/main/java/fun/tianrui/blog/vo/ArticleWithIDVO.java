package fun.tianrui.blog.vo;

import java.util.Set;

public class ArticleWithIDVO {
    Long id;
    String title;
    String content;
    Set<String> categories;
    String videoSrc;
    String postSrc;

    @Override
    public String toString() {
        return "ArticleWithIDVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", categories=" + categories +
                ", videoSrc='" + videoSrc + '\'' +
                ", postSrc='" + postSrc + '\'' +
                '}';
    }

    public String getPostSrc() {
        return postSrc;
    }

    public void setPostSrc(String postSrc) {
        this.postSrc = postSrc;
    }

    public String getVideoSrc() {
        return videoSrc;
    }

    public void setVideoSrc(String videoSrc) {
        this.videoSrc = videoSrc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }
}
