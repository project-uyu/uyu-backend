package uyu.server.member.data.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import uyu.server.member.data.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
