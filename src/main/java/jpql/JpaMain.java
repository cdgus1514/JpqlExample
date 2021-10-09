package jpql;

import javax.persistence.*;
import java.util.Collection;
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
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
////            member.setUsername("memeber1");
////            member.setUsername("teamA");
////            member.setUsername(null);
//            member.setUsername("관리자");
//            member.setAge(10);
//            member.setType(MemberType.ADMIN);
//            member.setTeam(team);
//            em.persist(member);
//
//
//            em.flush();
//            em.clear();

            // ManyToOne 에서 LAZY 타입으로 설정 안하면 쿼리 2번 날림
//            String query = "select m from Member m join m.team t";        // 내부조인
//            String query = "select m from Member m left join m.team t";   // 외부
//            String query = "select m from Member m, Team t where m.username = t.name";  // 세타조인

//            String query = "select m from Member m left join m.team t on t.name = 'teamA'";
//            String query = "select m from Member m join Team t on m.username = t.name";
//
//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();
//
//
//            System.out.println("result = " + result.toString());


            /**
             * 타입표현
             */
//            String query = "select m.username, 'HELLO', TRUE from Member m";
//            List<Object[]> result = em.createQuery(query).getResultList();
//
//            for (Object[] o : result) {
//                System.out.println("o.[0] = " + o[0]);
//                System.out.println("o.[0] = " + o[1]);
//                System.out.println("o.[0] = " + o[2]);
//            }


            // enum 타입은 '패키지명'까지 넣어줘야 함
            //String query = "select m.username, 'HELLO', TRUE from Member m where m.type = jpql.MemberType.USER";
//            String query = "select m.username, 'HELLO', TRUE from Member m where m.type = :userType";
//            List<Object[]> result = em.createQuery(query)
//                    .setParameter("userType", MemberType.USER)
//                    .getResultList();
//
//            for (Object[] o : result) {
//                System.out.println("o.[0] = " + o[0]);
//                System.out.println("o.[0] = " + o[1]);
//                System.out.println("o.[0] = " + o[2]);
//            }


            /**
             * 조건식
             */
            // CASE식
            String query = "select " +
                                "case when m.age <= 10 then '학생요금' " +
                                "     when m.age >= 60 then '경로요금' " +
                                "     else '일반욕금' " +
                           "end " +
                           "from Member m";
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }


            // COALESCE (하나씩 조회해서 null이 아니면 반환)
            String query2 = "select coalesce(m.username, '이름없는 회원') from Member m";
//            List<String> result = em.createQuery(query2, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }


            // NULLIF (두 값이 같으면 null, 다르면  첫 번째 값 반환)
            String query3 = "select nullif(m.username, '관리자') from Member m";
//            List<String> result = em.createQuery(query3, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }



            /**
             * 기본함수
             */
//            Member member1 = new Member();
//            member1.setUsername("관리자1");
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("관리자2");
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//
//            // 기본분법
//
//            // 1) size() > 양방향 관계 시 해당 엔티티의 컬렉션 크기 반환
//            String query4 = "select size(t.members) from Team t";
////            List<Integer> result = em.createQuery(query4, Integer.class)
////                    .getResultList();
////
////            for (Integer i : result) {
////                System.out.println("i = " + i);
////            }
//
//
//            // 사용자 정의함수 (DB에 정의된 함수 사용하기)
//
//            // ex) function group_concat 사용
//            // 1) dialect에 생성자로 해당함수 등록
//            // 2) persistence.xml 등록 (org.hibernate.dialect.H2Dialect > MyH2Dialect)
//
//            String query5 = "select function('group_concat', m.username) from Member m";
//            List<String> result = em.createQuery(query5, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }


            /**
             * 경로 표현식
             */
//            Team team = new Team();
//            team.setName("team1");
//            em.persist(team);
//
//            Member member1 = new Member();
//            member1.setUsername("관리자1");
//            member1.setTeam(team);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("관리자2");
//            member2.setTeam(team);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//            // 1. 상태필드 > 경로 탐색 끝, 탐색 X
//            String query6 = "select m.username from Member m";
//
//
//            // 2. 단일 값 연관경로 > 묵시적 내부조인 발생(조인 쿼리를 안써도 알아서 조인해서 가져옴), 탐색 O
//            // 묵시적 내부조인이 발생하도록 작성하면 안됨
//            String query7 = "select m.team from Member m";
//
//            // team.name, team.members 다른 경로로 갈 수 있음
//            //String query7 = "select m.team.name from Member m";
//
//
//
//            // 3. 컬렉션 값 > 묵시적 내부조인 발생, 탐색 X
//            // 컬렉션 값 연관 관계(1:N) 컬렉션에서 어떤 값을 꺼내야 할지 어려움
//            //String query8 = "select t.members from Team t";
//
//            // t.members 에서 다른경로 못찾음 (탐색 불가)
//            //String query8 = "select t.members.size from Team t";
//
//            // t.memebers에서 탐색이 필요한 경우 > 명시적 조인 사용
//            String query8 = "select m.username from Team t join t.members m";
//
//
//            List<Collection> result = em.createQuery(query8, Collection.class)
//                    .getResultList();
////                    .getSingleResult();
//
//            System.out.println("result = " + result);

//            for (Object s : result) {
//                System.out.println("s = " + s);
//            }


            /**
             * ★패치조인
             * 즉시 로딩처럼 보이나 묵시적 조인이 아닌 명시적 조인으로 동적쿼리 작성가능
             * 실무에서 정말 중요!
             */
            Team team1 = new Team();
            Team team2 = new Team();
            team1.setName("팀A");
            team2.setName("팀B");
            em.persist(team1);
            em.persist(team2);

            Member member1  = new Member();
            Member member2  = new Member();
            Member member3  = new Member();
            member1.setUsername("회원1");
            member1.setTeam(team1);
            member2.setUsername("회원2");
            member2.setTeam(team1);
            member3.setUsername("회원3");
            member3.setTeam(team2);
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            em.flush();
            em.clear();


            // 양방향 연관관계(LAZY) > 지연로딩으로 Member.username 호출 후 Team.name 호출할 때마다 프록시(Team 조회)
            //String jpql = "select m from Member m";

            // 명시적 조인으로 쿼리 한방에 조회가능 (엔티티로 결과를 가져와서 프록시 필요 없음)
            String jpql = "select m from Member m join fetch m.team";

//            List<Member> result = em.createQuery(jpql, Member.class)
//                    .getResultList();
//
//            for(Member m : result) {
//                System.out.println(" result : " + m.getUsername() + ", " + m.getTeam().getName());
//                // 회원1, 팀A(SQL)
//                // 회원2, 팀A(1차 캐시)
//                // 회원3, 팀B(SQL)
//            }


            /* 컬렉션 패치조인 */
            // 데이터 뻥튀기됨 (team은 member 얼마나 있는지 모름)
            //String jpql2 = "select t from Team t join fetch t.members";

            // 중복제거해도 결과가 달라서 중복제거 안됨 > 같은 식별자 Team 엔티티 제거시도 → 컬렉션 중복제거
            // 컬렉션 패치조인 후 페이징 사용 시 페이징으로 가져오는게 아닌 전체를 가져오고 메모리에서 페이징(사용X)
            //String jpql2 = "select distinct t from Team t join fetch t.members";

            // 페이징이 필요한 경우
            // 1) N:1로 바꿔서 조회
            //String jpql2 = "select m from Member m join fetch m.team t";

            // 2) BatchSize 사용
            String jpql2 = "select t from Team t";

            List<Team> result = em.createQuery(jpql2, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();
            
            for (Team t : result) {
                System.out.println("team : " + t.getName() + ", members : " + t.getMembers().size());

                for (Member m : t.getMembers()) {
                    System.out.println("> member : " + m);
                }
            }
            
            /* 패치조인 vs 일반조인 */
            
            // "일반조인"은 연관된 엔티티를 함께 조회하지 않음 > 연관된 엔티티 데이터 호출 시 추가로 조회 실행
            // "패치조인"은 다가져옴


            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
