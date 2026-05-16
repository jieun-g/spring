package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service//스프링 컨테이너에 멤버 서비스를 등록해 줌
public class MemberService {
    //컨트롤 쉬프트 t누르면 자동으로 테스트 케이스 만듦..!!!
    //alt+insert
    private final MemberRepository memberRepository;

    //@Autowired//이게 있을 경우,  스프링 컨테이너에 멤버 리포리지토리를 넣음
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public long join(Member member) {
        validateDuplicateMember(member);//중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //같은 이름이 있는 중복 회원x
        /*Optional<Member> result =*/
        memberRepository.findByName(member.getName())
        /*result*/.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
