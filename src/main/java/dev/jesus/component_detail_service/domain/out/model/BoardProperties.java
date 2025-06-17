package dev.jesus.component_detail_service.domain.out.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardProperties implements ComponentProperties {
    private String brand;
    private String model;
    private String socketType;
    private String socketModel;
    private int slots;
    private String imageUri;
    private Boolean status;
}
