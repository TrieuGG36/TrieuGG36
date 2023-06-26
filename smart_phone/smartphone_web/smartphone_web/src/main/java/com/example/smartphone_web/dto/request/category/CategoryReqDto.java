package com.example.smartphone_web.dto.request.category;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CategoryReqDto {
    private Integer id;
    private String name;
}
