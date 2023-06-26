package com.example.smartphone_web.dto.request.attribute.pin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PinRequest {
    private Long id;
    private Integer name;
}
