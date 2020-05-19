package fun.tianrui.blog.vo;

import java.util.List;

public class CategoryVOListAndTotalPage {
    List<CategoryVO> categories;
    Integer totalPage;

    @Override
    public String toString() {
        return "CategoryVOListAndTotalPage{" +
                "categories=" + categories +
                ", totalPage=" + totalPage +
                '}';
    }

    public List<CategoryVO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryVO> categories) {
        this.categories = categories;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
