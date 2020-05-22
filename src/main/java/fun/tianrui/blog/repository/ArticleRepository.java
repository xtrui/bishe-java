package fun.tianrui.blog.repository;

import fun.tianrui.blog.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author TIANRUI
 */
public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
    List<Article> findAllByTitle(String title);

    Page<Article> findByTitleLike(String key, Pageable page);

    Void deleteByIdIn(List<Long> ids);
}
