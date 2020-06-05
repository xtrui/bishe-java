package fun.tianrui.blog.vo;

public class UserIdAndArticleIdVO {
    Long articleId;
    Long userId;

    @Override
    public String toString() {
        return "UserIdAndArticleIdVO{" +
                "articleId=" + articleId +
                ", userId=" + userId +
                '}';
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
