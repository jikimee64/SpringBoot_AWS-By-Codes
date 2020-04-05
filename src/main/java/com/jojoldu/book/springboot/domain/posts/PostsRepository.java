package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p from Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

    /*
    SpringDataJpa에서 제공하지 않는 메소드는 위처럼 쿼리로 작성해도 됨
    (앞의 코드는 Jpa에서 제공하는 기본 메소드로 해결할 수 있으나 @Query가 훨씬 가독성이 좋으니 선택사항)

    규모가 있는 프로젝트에서는 이런 Entity 클래스만으로 처리하기 어려워 조회용 프레임워크를 추가로 사용
    (Ex. Querydsl, jooq, MyBNatis 등)
    등록, 수정, 삭제는 SpringDataJpa 진행, 작가는 Querydsl을 추천

    1.타입 안정성 보장
     - 단순한 문자열로 쿼리를 생성하는 것이 아니라, 메소드를 기반으로 쿼리를 생성하기 때문에 오타나 존재하지
       않는 컬럼명을 명시할 경우 IDE에서 자동으로 검출

    2.국내 많은 회사에서 사용
     - Jpa + QueryDsl 많이 사용

    3. 래퍼런스가 많음
     */





}
