package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
특이점 : setter 메소드가 없다.
자바빈 규약을 생각하면서 getter/setter를 무작정 생성하는 경우가 있다. 이렇게 되면 해당 클래스의 인스턴스 값들이
언제 어디서 변해야 하는지 코드상으로 명확하게 구분할 수가 없어, 차후 기능 변경 시 정말 복잡해진다.
그래서 Entity클래스에서는 절대 Setter 메소드를 만들지 않는다. 대신, 해당 필드의 값 변경이 필요하면
명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야만 한다.
책 93page
 */


@Getter
@NoArgsConstructor
//기본 생성자 자동 추가
//public Posts() {}
@Entity //주요 어노테이션인 Entity를 클래스에 가깝게 위치.
//테이블과 링크될 클래스임을 나타냄
//기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이ㅣ름을 매칭한다.
//ex) SalesManager.java -> sales_manager table
public class Posts extends BaseTimeEntity { //실제 DB의 테이블과 매칭될 클래스이며 보통 Entity클래스라고도 한다.
    //JPA를 사용하면 DB 데이터에 작업할 경우 실제 쿼리를 날리기보다는, 이 Entity 클래스의 수정을 통해 작업한다.
    @Id
    //해당 테이블의 PK를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //PK의 생성 규칙을 나타냄
    //스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야먄 auto_increment가 된다.
    private Long id; //-> 왠만하면 이런 방법으로 사용해라

    @Column(length = 500, nullable = false)
    private String title;
    //테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다.
    //사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다
    //문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나, 타입을 TEXT로 변경하고 싶거나 등의
    //경우에 사용

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    //해당 클래스의 빌더 패턴 클래스를 생성
    //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
