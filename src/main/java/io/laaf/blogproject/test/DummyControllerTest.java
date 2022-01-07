package io.laaf.blogproject.test;

import io.laaf.blogproject.model.RoleType;
import io.laaf.blogproject.model.User;
import io.laaf.blogproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;


    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패했습니다. 해당 아이디가 없어요";
        }

        return "삭제됨 " + id;
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User requestUser) {

        User user = userRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
//        userRepository.save(user);
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<User> pageUser = userRepository.findAll(pageable);

        List<User> users = pageUser.getContent();

        return users;
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 사용자는 없습니다.");
        });

        // user 객체 = 자바 오브젝트
        // 변환 (웹브라우저가 이해할 수 있는 데이터) -> json(Gson 라이브러리)
        // 스프링부트 = MessageConverter 응답시 자동 작동
        // 자바 오브젝트를 리턴하면 MessageConverter가 Jackson 라이브러리가 작동해서 json을 반환
        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user) { // key=value
        System.out.println("username:" + user.getUsername());
        System.out.println("password:" + user.getPassword());
        System.out.println("email:" + user.getEmail());
        System.out.println("role:" + user.getRole());
        System.out.println("date:" + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }
}
