package io.laaf.blogproject.test;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/http")
public class HttpControllerTest {


    @GetMapping("/get")
    public String getTest(Member m) {
        return "get 요청 " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    @PostMapping("/post")
    public String postTest(@RequestBody Member m) { // MessageConverter (Spring Boot)
        return "post 요청 " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    @PutMapping("/put")
    public String putTest(@RequestBody Member m) {
        return "put 요청 " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    @DeleteMapping("delete")
    public String deleteTest() {
        return "delete 요청";
    }
}
