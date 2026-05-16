package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean//spring 빈을 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());//스프링 빈에 등록돼 잇는 멤버 리포리지토리를 멤버 서비스에 넣음.
    }//로직을 호출해서 멤버서비스를 스프링 빈에 등록 해줌

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
