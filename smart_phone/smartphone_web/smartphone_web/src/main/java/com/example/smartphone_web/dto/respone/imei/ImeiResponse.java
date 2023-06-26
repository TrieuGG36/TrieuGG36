package com.example.smartphone_web.dto.respone.imei;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImeiResponse {
    private String id;
    private String value;
}
