package engine.controller;

import engine.dto.UserRecordDTO;
import engine.model.QuizAnswer;
import engine.model.QuizResult;
import engine.entity.User;
import engine.entity.Quiz;
import engine.dto.QuizDTO;
import engine.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Validated
@RequestMapping("/api/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;

    /*
    Get Mappings
     */

    @GetMapping()
    public ResponseEntity<Page<QuizDTO>> getQuizzes(@RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "10") Integer size) {
        Page<QuizDTO> quizDTOPage = quizService.getQuizDTOPageService(page, size);
        return new ResponseEntity<>(quizDTOPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuizById(@PathVariable int id) {
        QuizDTO quizDTO = quizService.getQuizDTOByIdService(id);
        return new ResponseEntity<>(quizDTO, HttpStatus.OK);
    }

    @GetMapping("/completed")
    public ResponseEntity<Page<UserRecordDTO>> getCompletedQuizPage(@RequestParam(defaultValue = "0") Integer page,
                                                                    @RequestParam(defaultValue = "10") Integer size,
                                                                    @AuthenticationPrincipal User user) {
        Page<UserRecordDTO> dtoPage = quizService.getCompletedQuizzesService(page, size, user);
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }

    /*
    Post Mappings
     */


    @PostMapping()
    public ResponseEntity<QuizDTO> makeNewQuiz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal User user) {
        QuizDTO quizDTO = quizService.createNewQuizService(quiz, user);
        return new ResponseEntity<>(quizDTO, HttpStatus.OK);
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<?> solveQuiz(@PathVariable int id,
                                       @RequestBody(required = false) QuizAnswer answer,
                                       @AuthenticationPrincipal User user) {

        QuizResult response = quizService.getQuizAnswerResponseService(id, answer.getAnswerAsArray(), user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    Delete mappings
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable int id, @AuthenticationPrincipal User user) {
        quizService.deleteQuizService(id, user);
        return new ResponseEntity<>(String.format("Quiz #%d has been deleted ", id), HttpStatus.NO_CONTENT);
    }
}