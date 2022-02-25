//TODO
//1. Product 생성
//2. Product 테스트코드 작성
//3. Product 기능 추가
//4. Product 기능 테스트 코드 작성
package com.imform.demo.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Override
    public String toString() {
        String id = "id";
        String content = "content";

        return String.format(
            "Product -> Id: " + "%s, content: %s", id, content
        );
    }
}
