package fun.tianrui.blog.repository;

import fun.tianrui.blog.entity.EmailAndCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmailAndCodeRepository extends JpaRepository<EmailAndCode, Long>, JpaSpecificationExecutor<EmailAndCode> {
}
