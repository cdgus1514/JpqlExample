package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em  = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            /**
             * 기본문법
             */
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            em.persist(member);

            // typeQuery
//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//            List<Member> resultList = query.getResultList();
//
//            // 결과가 여러개인 경우 (없으면 깡통 반환)
//            for (Member m : resultList) {
//                System.out.println("m = " + m);
//            }

//            TypedQuery<Member> query2 = em.createQuery("select m from Member m", Member.class);
//            // 결과가 1개인 경우 (없으면 NoResultException, 1개 이상이면 NonUniqueResultException)
//            Member result = query2.getSingleResult();
//            System.out.println("result = " + result);

            // Query
//            Query query3 = em.createQuery("select m.username, m.age from Member m");


            // 파라미터 바인딩(채널링 형식으로 코드작성)
//            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
//                .setParameter("username", "member1")
//                .getSingleResult();
//
//            System.out.println("result = " + result.getUsername());
//            System.out.println("result = " + result.getAge());


            /**
             * 프로젝션
             * SELECT 절에 조회할 대상
             */
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            em.persist(member);
//
//            em.flush();
//            em.clear();

            // 엔티티 프로젝션
//            List<Member> result = em.createQuery("select m from Member m", Member.class)
//                    .getResultList();


            // 업데이트 됨 > 영속성 컨텍스트에서 관리
//            Member findMember = result.get(0);
//            findMember.setAge(20);

            System.out.println("================================================================================");

            // 엔티티 프로젝션 사용 시 해당 엔티티에 맞춰줘야 함
            // Team 하고 inner join
//            List<Team> result2 = em.createQuery("select m.team from Member m", Team.class)
//                    .getResultList();

            
            // 임베디드 타입 프로젝션
            // Order에 들어있는 값 타입으로 Address에 관련된 값만 정상적으로 불러옴
//            em.createQuery("select o.address from Order o", Address.class)
//                    .getResultList();


            // 스칼라 타입 프로젝션 > 여러 값(여러 타입) 조회 시

            // 1) Query 타입
//            List resultList = em.createQuery("select distinct m.username, m.age from Member m")
//                    .getResultList();
//
//            Object o = resultList.get(0);
//            Object[] resulto = (Object[]) o;
//
//            System.out.println("================================================================================");
//            System.out.println(resulto[0]);
//            System.out.println(resulto[1]);


            // 2) Object[] 타입
//            List<Object[]> resultList2 = em.createQuery("select distinct m.username, m.age from Member m")
//                    .getResultList();
//
//            System.out.println("================================================================================");
//            Object[] resulto2 = resultList2.get(0);
//            System.out.println(resulto2[0]);
//            System.out.println(resulto2[1]);


            // 3) new 명령어 사용 > 생성자 필요!
//            List<MemberDTO> resultList3 = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
//                    .getResultList();
//
//            MemberDTO memberDTO = resultList3.get(0);
//            System.out.println("memberDTO = " + memberDTO.getUsername());
//            System.out.println("memberDTO = " + memberDTO.getAge());


            /**
             * 페이징 처리
             */
//            for(int i=0; i<100; i++) {
//                Member member = new Member();
//                member.setUsername("memeber"+i);
//                member.setAge(i);
//                em.persist(member);
//            }
//
//            em.flush();
//            em.clear();
//
//            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            System.out.println("result = " + result.size());
//            for (Member member1 : result) {
//                System.out.println("member1 = " + member1);
//            }


            /**
             * 조인
             */
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
//            member.setUsername("memeber1");
            member.setUsername("teamA");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);


            em.flush();
            em.clear();

            // ManyToOne 에서 LAZY 타입으로 설정 안하면 쿼리 2번 날림
//            String query = "select m from Member m join m.team t";        // 내부조인
//            String query = "select m from Member m left join m.team t";   // 외부
//            String query = "select m from Member m, Team t where m.username = t.name";  // 세타조인

//            String query = "select m from Member m left join m.team t on t.name = 'teamA'";
            String query = "select m from Member m join Team t on m.username = t.name";

            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();


            System.out.println("result = " + result.toString());

            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
