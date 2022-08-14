package engine.service;

import engine.dto.QuizDTO;
import engine.dto.UserRecordDTO;
import engine.exception.ForbiddenAccessException;
import engine.exception.QuizNotFoundException;
import engine.model.QuizResult;
import engine.entity.Quiz;
import engine.entity.User;
import engine.entity.UserRecord;
import engine.repository.QuizRepository;
import engine.repository.UserRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private UserRecordRepository userRecordRepository;

    public QuizDTO createNewQuizService(Quiz quiz, User user) {
        quiz.setAuthorID(user.getId());
        quizRepository.save(quiz);
        return new QuizDTO(quiz);
    }

    public QuizDTO getQuizDTOByIdService(int id) {
        Optional<Quiz> request = quizRepository.findById(id);
        if (request.isEmpty()) {
            throw new QuizNotFoundException(id);
        }
        return new QuizDTO(request.get());
    }

    public QuizResult getQuizAnswerResponseService(int id, int[] answer, User user) {
        Optional<Quiz> quiz = quizRepository.findById(id);

        if (quiz.isEmpty()) {
            throw new QuizNotFoundException(id);
        }

        Arrays.sort(answer);
        boolean solutionResults = Arrays.equals(answer, quiz.get().getAnswer());
        String feedback = solutionResults ? "Congratulations, you're right!" : "Wrong answer! Please, try again.";

        //create a record and timestamp for solving the quiz
        if (solutionResults) {
            userRecordRepository.save(new UserRecord(user.getId(), quiz.get().getId()));
        }
        return new QuizResult(solutionResults, feedback);
    }

    public Page<QuizDTO> getQuizDTOPageService(Integer page, Integer size) {
        return quizRepository.findAll(PageRequest.of(page, size)).map(QuizDTO::new);
    }

    public void deleteQuizService(int id, User user) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isPresent()) {
            if (quiz.get().getAuthorID() == user.getId()) {
                quizRepository.delete(quiz.get());
                return;
            }
            //invalid credentials
            throw new ForbiddenAccessException(id, user.getUsername());
        }
        //quiz does not exist in repository
        throw new QuizNotFoundException(id);
    }

    public Page<UserRecordDTO> getCompletedQuizzesService(Integer page, Integer size, User user) {
        Pageable requestParameters = PageRequest.
                of(page, size, Sort.by(Sort.Direction.DESC, "localDateTime"));

        return userRecordRepository.findAllByUserID(user.getId(), requestParameters).map(UserRecordDTO::new);
    }
}
