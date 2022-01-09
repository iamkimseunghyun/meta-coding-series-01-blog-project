package io.laaf.blogproject.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리

    private Long count; // 조회수

    @ManyToOne(fetch = FetchType.LAZY) // Many = Board, User = One
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY) // mappedBy 연관관계의 주인이 아니다(난 FK가 아니다) DB에 칼럼을 만들지 마세요.
    private List<Reply> reply;

    @CreationTimestamp
    private LocalDateTime createDate;
}
