package com.imform.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductData {

  @NotNull
  @Size(min = 6)
  private String userId;

  @NotBlank
  private String content;

  @NotBlank
  private String description;

}
