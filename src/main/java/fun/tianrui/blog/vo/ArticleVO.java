package fun.tianrui.blog.vo;

import java.util.Set;

public class ArticleVO {
    String title;
    String content;
    Set<String> categories;

    @Override
    public String toString() {
        return "articleVO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", categories=" + categories +
                '}';
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
