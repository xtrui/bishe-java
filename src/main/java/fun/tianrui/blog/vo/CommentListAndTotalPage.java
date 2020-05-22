package fun.tianrui.blog.vo;

import fun.tianrui.blog.entity.Comment;

import java.util.List;

public class CommentListAndTotalPage {
    List<Comment> comments;
    Integer totalPage;

    @Override
    public String toString() {
        return "CommentListAndTotalPage{" +
                "comments=" + comments +
                ", totalPage=" + totalPage +
                '}';
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
