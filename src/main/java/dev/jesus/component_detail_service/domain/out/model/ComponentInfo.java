package dev.jesus.component_detail_service.domain.out.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ComponentInfo {
    private String id;
    private Map<String, String> details;
    private Boolean status;
}
