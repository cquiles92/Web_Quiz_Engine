package engine.model;

import java.util.List;

public class QuizAnswer {
    private List<Integer> answer;

    public QuizAnswer() {
    }

    public QuizAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public int[] getAnswerAsArray() {
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
