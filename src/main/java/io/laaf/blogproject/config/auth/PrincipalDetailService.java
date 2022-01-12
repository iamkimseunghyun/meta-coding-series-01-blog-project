package io.laaf.blogproject.config.auth;

import io.laaf.blogproject.model.User;
import io.laaf.blogproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
    // password 부분 처리는 알아서 함
    // username이 DB에 있는지만 확인해 주면 됨

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. :" + username)
                );
        return new PrincipalDetail(principal);
        // 시큐리티 세션에 유저 정보가 저장 됨. User 객체 없으면 디폴트 시큐리티 계정으로 로그인!

    }
}
