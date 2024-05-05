package by.davlar.spring.service.dto;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class CompanyDto {

    private Integer id;
    private String name;
    @Builder.Default
    @ToString.Exclude
    private Map<String, String> locales = new HashMap<>();
}
