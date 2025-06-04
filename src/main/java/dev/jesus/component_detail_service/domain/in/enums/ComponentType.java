package dev.jesus.component_detail_service.domain.in.enums;

public enum ComponentType {
    ACCESSORY,
    BOARD,
    CPU_GPU,
    ENERGY,
    PC_CASE,
    SCREEN,
    STORAGE;

    public String toUriParam() {
        return name().toLowerCase();
    }
}
