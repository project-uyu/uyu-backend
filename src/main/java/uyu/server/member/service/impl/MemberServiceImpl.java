package uyu.server.member.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uyu.server.member.data.entity.Member;
import uyu.server.member.data.repository.MemberRepository;
import uyu.server.member.service.MemberService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void signUp(String email, String name, String password) {
        memberRepository.save(Member.builder()
                        .email(email)
                        .name(name)
                        .password(hashPassword(password))
                        .build());
    }

    @Override
    public Member authenticate(String email, String password) throws Exception {
        Member member = memberRepository.findByEmail(email).orElseThrow(()->new Exception("user 없음"));
        if(!member.getPassword().equals(hashPassword(password))) throw new Exception("비번 틀림");
        return member;
    }

    @Override
    public Member findMemberByEmail(String email) throws Exception {
        return memberRepository.findByEmail(email).orElseThrow(()->new Exception("user 없음"));

    }

    private String hashPassword(String password) {
        try{
            //해당 알고리즘을 이용하는 해시 함수 객체 생성
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b)); // 16진수 문자열로 변환하여 StringBuilder에 추가
            }
            return sb.toString(); // 암호화된 비밀번호 반환
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
