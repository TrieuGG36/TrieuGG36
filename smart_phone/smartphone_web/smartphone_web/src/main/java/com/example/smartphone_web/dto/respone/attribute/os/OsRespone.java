package com.example.smartphone_web.dto.respone.attribute.os;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OsRespone {
    private Long id;
    private String name;
    private Long loai;
}
