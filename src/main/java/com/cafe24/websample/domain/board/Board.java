package com.cafe24.websample.domain.board;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private Long userId;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date regDateTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)  // java.util.Date이므로 @Temporal을 붙여준다.
    @Column(name = "last_modified_at", updatable = true)
    private Date lastModifiedDateTime;

    @Builder
    public Board(String title, String content, Long userId, Date regDateTime, Date lastModifiedDateTime) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.regDateTime = regDateTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }
}
