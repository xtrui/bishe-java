package fun.tianrui.blog.repository;

import fun.tianrui.blog.entity.Category;
import fun.tianrui.blog.vo.CategoryVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author TIANRUI
 */
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    @Query(value = "select new fun.tianrui.blog.vo.CategoryVO(c.id,c.categoryName) from Category c order by c.id desc")
    List<CategoryVO> findAllWithoutArticle();

    @Query(value = "select new fun.tianrui.blog.vo.CategoryVO(c.id,c.categoryName) from Category c order by c.id desc")
    List<CategoryVO> findNewAllWithoutArticle(Pageable page);


}
