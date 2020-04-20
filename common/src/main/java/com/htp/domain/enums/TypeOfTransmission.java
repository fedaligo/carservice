package com.htp.domain.enums;

public enum TypeOfTransmission {
    NOT_SELECTED,AT,MT
    /*AT("At"), MT("Mt"), NOT_SELECTED("Not Selected");

    public static final Set<TypeOfTransmission> typeOfTransmission = Collections.unmodifiableSet(Set.of(TypeOfTransmission.values()));

    private String typeOfTransmissionName;

    TypeOfTransmission(String typeOfTransmissionName) {
        this.typeOfTransmissionName = typeOfTransmissionName;
    }

    public String getTypeOfTransmissionName() {
        return typeOfTransmissionName;
    }

    public static TypeOfTransmission findByName(String typeOfTransmissionName) {
        for (TypeOfTransmission type : typeOfTransmission) {
            if (type.getTypeOfTransmissionName().equalsIgnoreCase(typeOfTransmissionName)) {
                return type;
            }
        }
        return NOT_SELECTED;
    }*/
}
