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
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

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
            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

            System.out.println("result = " + result.getUsername());
            System.out.println("result = " + result.getAge());

            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
