package engine.dto;

import engine.entity.UserRecord;

import java.time.LocalDateTime;

public class UserRecordDTO {
    private long id;
    private LocalDateTime completedAt;

    public UserRecordDTO(UserRecord userRecord) {
        this.id = userRecord.getQuizID();
        this.completedAt = userRecord.getLocalDateTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
