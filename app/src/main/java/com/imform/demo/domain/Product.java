//TODO
//1. Product 생성
//2. Product 테스트코드 작성
//3. Product 기능 추가
//4. Product 기능 테스트 코드 작성
package com.imform.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String userId;

  private String content;

  private String description;

  public void change(Product source) {
    this.userId = source.getUserId();
    this.content = source.getContent();
    this.description = source.getDescription();
  }
}
