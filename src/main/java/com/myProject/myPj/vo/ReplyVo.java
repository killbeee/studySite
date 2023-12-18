package com.myProject.myPj.vo;



import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class ReplyVo {

    private String replyKeyId;
    private String postReferKeyId;
    private String replyContent;
    private String regId;
    private Timestamp regDate;
    private String replyDepth;
    private String password;
    private String betweenTime;

        

    public String getBetweenTime() {
    	calculateBetweenTime();
        return betweenTime;
    }

    private void calculateBetweenTime() {
        LocalDateTime dateTime = this.regDate.toLocalDateTime();
        LocalDateTime currentDateTime = LocalDateTime.now();

        Duration duration = Duration.between(dateTime, currentDateTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes();

        if (hours > 1) {
            this.betweenTime = hours + " 시간 전";
        } else if (minutes > 24 * 60) {
            this.betweenTime = minutes/(24*60) + "일 전";
        } else {
            this.betweenTime = minutes + "분 전";
        }
    }
}
