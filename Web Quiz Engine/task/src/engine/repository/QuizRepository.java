package engine.repository;

import engine.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface QuizRepository extends PagingAndSortingRepository<Quiz, Long> {

    Optional<Quiz> findById(long id);

    @Override
    Page<Quiz> findAll();
}
