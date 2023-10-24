package toy1.upload_toy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy1.upload_toy.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final MemberRepository memberRepository;
}
