package fun.tianrui.blog.vo;

public class CategoryNameAndArticlesSize {
    String categoryName;
    Integer articleNum;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(Integer articleNum) {
        this.articleNum = articleNum;
    }

    @Override
    public String toString() {
        return "CategoryNameAndArticlesSize{" +
                "categoryName='" + categoryName + '\'' +
                ", articleNum=" + articleNum +
                '}';
    }
}
