package cupraccoon.myboard.controller.member;

import cupraccoon.myboard.controller.board.BoardDto;
import cupraccoon.myboard.domain.Member;
import cupraccoon.myboard.domain.board.Board;
import cupraccoon.myboard.domain.board.Category;
import cupraccoon.myboard.service.MemberService;
import cupraccoon.myboard.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/new")
    public String newMember(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "/member/createMemberForm";
    }

    @PostMapping("/new")
    public String newMember(MemberDto memberDto, Model model) throws Exception {
        Member member = Member.createMember(memberDto.getLoginId(),
                memberDto.getNickname(), memberDto.getPassword());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginDto loginDto) {
        return "member/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto, HttpServletRequest request) {

//        if (bindingResult.hasErrors()) {
//            return "login/loginForm";
//        }

        Member loginMember = memberService.login(loginDto.getLoginId(), loginDto.getPassword());

        if (loginMember == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "/member/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }




}
