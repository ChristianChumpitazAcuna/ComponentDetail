package dev.jesus.component_detail_service.domain.out.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "componentType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AccessoryProperties.class, name = "ACCESSORY"),
        @JsonSubTypes.Type(value = BoardProperties.class, name = "BOARD")
})
public interface ComponentProperties {
    String getBrand();

    String getModel();

    String getImageUri();

    Boolean getStatus();
}
