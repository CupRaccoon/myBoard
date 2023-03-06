package cupraccoon.myboard.service;

import cupraccoon.myboard.domain.Member;
import cupraccoon.myboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if(optionalMember.isPresent()){
            return optionalMember.get();
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByLoginId(member.getLoginId());
        if(findMember.isPresent()){
            throw new IllegalStateException("이미 존재하는 회원 ID입니다.");
        }
    }
}
