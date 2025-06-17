package dev.jesus.component_detail_service.domain.out.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccessoryProperties implements ComponentProperties {
    private String accessoryType;
    private List<String> connectivityType;
    private String brand;
    private String model;
    private String color;
    private String imageUri;
    private String printerType;
    private List<String> details;
    private Boolean status;
}
