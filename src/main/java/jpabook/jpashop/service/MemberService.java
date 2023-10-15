package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // 필수 인자 생성자 final이 붙은 필드 생성자를 자동 생성
@Transactional(readOnly = true) // 영속성컨텍스트(읽기전용) - 성능향상도모
// jpa의 모든 데이터 변경 로직은 가급적 트랜잭션에서 실행되어야 한다.
// 클래스 레벨에서 어노테이션을 걸면 모든 public 메서드가 트랜잭션이 걸린다.
// 스프링 어노테이션을 이용(javax는 안됨)
// readonly : 조회시에만 성능 최적화
public class MemberService {
    // @Autowired : 필드 인젝션
    // 생성자 주입 (접근제한)
    private final MemberRepository memberRepository;

    // setter 인젝션 예시 (사용 지양)
    // @Autowired
    // public void setMemberRepository(MemberRepository memberRepository) {
    //         this.memberRepository = memberRepository;
    // }

    // Autowired 생성자 주입 <- 그나마 이게 가장 적절
    // lombok 어노테이션으로 인해서 생성자 생략
    // public MemberService(MemberRepository memberRepository) {
    //        this.memberRepository = memberRepository;
    // }



    //회원가입
    @Transactional  // readonly가 되면 안되므로 따로 트랜잭션
    public Long join(Member member) {
        validateDuplicateMember(member); // 유효성 검사를 통한 중복회원 검증
        memberRepository.save(member); // save 메서드는 jpa에서 제공하는 메서드
        return member.getId();
    }

    // 중복 회원 검증 메서드
    // 멀티쓰레드 상황을 고려해서 DB에 name을 유니크 제약조건을 지정하는 것이 좋다 (성능향상)
    // 두 회원이 동일한 이름으로 동시에 가입 하는 경우, validate를 통과할 수도 있다.
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    // 회원 정보 수정
    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name); // 변경감지
    }
}
