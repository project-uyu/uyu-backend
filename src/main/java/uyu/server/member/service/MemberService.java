package uyu.server.member.service;

import uyu.server.member.data.entity.Member;

public interface MemberService {
    void signUp(String email, String name, String password);

    Member authenticate(String email, String password) throws Exception;

    Member findMemberByEmail(String email) throws Exception;

    void delete(Long id);
}
