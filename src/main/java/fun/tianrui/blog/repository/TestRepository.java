package fun.tianrui.blog.repository;

import fun.tianrui.blog.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author TIANRUI
 */
public interface TestRepository extends JpaRepository<Test, Long>, JpaSpecificationExecutor<Test> {
}
