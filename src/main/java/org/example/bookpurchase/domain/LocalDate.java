package org.example.bookpurchase.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.sound.sampled.AudioFileFormat;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass//상속할 경우 필드를 칼럼으로 인식하도록한다.
@EntityListeners(AuditingEntityListener.class)//auditing기능을 포함시킨다.
public class LocalDate {

    @CreatedDate//엔티티가 생성되어 저장될때 시간이 자종 저장
    private LocalDateTime createdDate;

    @LastModifiedDate// 조희한 엔티티의 값을 변경할 때 시간이 자동 저장
    private LocalDateTime modifiedDate;


}
