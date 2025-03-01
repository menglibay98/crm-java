package repository;

import jakarta.transaction.Transactional;
import models.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationRequest,Long> {
    List<ApplicationRequest> findAllByHandledIsFalse();
    List<ApplicationRequest> findAllByHandledIsTrue();

}
