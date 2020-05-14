package fun.tianrui.blog.vo;

import java.util.List;

public class ArticleIdAndTitleListAndTotalPage {
    List<ArticleIdAndTitle> articleIdAndTitleList;
    Integer totalPage;

    @Override
    public String toString() {
        return "ArticleIdAndTitleListAndTotalPage{" +
                "articleIdAndTitleList=" + articleIdAndTitleList +
                ", totalPage=" + totalPage +
                '}';
    }

    public List<ArticleIdAndTitle> getArticleIdAndTitleList() {
        return articleIdAndTitleList;
    }

    public void setArticleIdAndTitleList(List<ArticleIdAndTitle> articleIdAndTitleList) {
        this.articleIdAndTitleList = articleIdAndTitleList;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
