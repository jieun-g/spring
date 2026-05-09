package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);//회원 저장하면 저장된 회원 반환
    Optional<Member> findById(Long id);//find로 저장된거 찾음. 없으면null반환?
    Optional<Member> findByName(String name);
    List<Member> findAll();//모두 list로 반환


}
