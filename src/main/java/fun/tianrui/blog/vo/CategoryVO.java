package fun.tianrui.blog.vo;

public class CategoryVO {
    Long id;
    String categoryName;

    public CategoryVO(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CategoryVO{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
