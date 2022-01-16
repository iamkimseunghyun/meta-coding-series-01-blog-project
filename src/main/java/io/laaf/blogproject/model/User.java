package io.laaf.blogproject.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "이름을 입력하세요.")
    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Column(nullable = false, length = 100)
    private String password;

    @Email(message = "이메일 주소를 정확히 입력하세요.")
    @NotBlank(message = "이메일 주소를 입력하세요.")
    @Column(nullable = false, length = 50)
    private String email;

//    ColumnDefault("'user'")
//    DB는 RoleType 없다
    @Enumerated(EnumType.STRING)
    private RoleType role;

    private String oauth; // kakao, google

    @CreationTimestamp
    private LocalDateTime createDate;
}
