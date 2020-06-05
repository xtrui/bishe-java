package fun.tianrui.blog.repository;

import fun.tianrui.blog.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
    @Query(value = "select * from comment c where c.user_id = ?1 ORDER BY ?#{#pageable}", nativeQuery = true)
    Page<Comment> findByUserId(Long userId, Pageable pageable);
}
