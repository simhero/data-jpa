package study.datajpa.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testEntity() {
        Team teamA = new Team("Team A");
        Team teamB = new Team("Team B");

        entityManager.persist(teamA);
        entityManager.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        entityManager.persist(member1);
        entityManager.persist(member2);
        entityManager.persist(member3);
        entityManager.persist(member4);

        // 초기화
        entityManager.flush();
        entityManager.clear();

        List<Member> members = entityManager.createQuery(" select m from Member m", Member.class).getResultList();
        for (Member member : members) {
            System.out.println("member=" + member);
            System.out.println("member.getTeam()" + member.getTeam());
        }

    }
}
