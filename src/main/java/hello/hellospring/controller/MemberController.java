package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //처음에 스프링 컨테이너 생김, @Controller는 멤버 컨트롤로 객체 생성 해서 스프링에 넣음, 스프링이 이걸 관리.=스프링 빈이 관리된다.
public class MemberController {
    //=new MemberService를 하면 멤버 컨트롤러 외 여러 컨트롤러들이 MemberService를 쓸 수 있음. but 이건 여러개 인스턴스 생성할 필요가 없음
    private final MemberService memberService;//컨테이너에 등록


    @Autowired //스프링 컨테이너에 있는 멤버 서비스를 (컨트롤러와 서비스를)연결 시켜줌
    //즉, 생성자에 일케 쓰면 멤버 컨트롤러가 생성이 될 때 스프링 빈에 등록돼 있는 멤버 서비스 객체를 가져다가 넣어줌.=dependency injection(의존관계 주입)
    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMember();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
