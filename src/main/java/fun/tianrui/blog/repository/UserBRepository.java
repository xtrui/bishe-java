package fun.tianrui.blog.repository;

import fun.tianrui.blog.entity.UserB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserBRepository extends JpaRepository<UserB, Long>, JpaSpecificationExecutor<UserB> {
}
