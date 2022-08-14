package engine.repository;

import engine.entity.UserRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecordRepository extends PagingAndSortingRepository<UserRecord, Long> {
    Page<UserRecord> findAllByUserID(Long id, Pageable pageable);
}