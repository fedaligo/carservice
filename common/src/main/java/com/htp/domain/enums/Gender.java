package com.htp.domain.enums;

public enum Gender {
    MALE,FEMALE,NOT_SELECTED
    /*MALE("Male"),
    FEMALE("Female"),
    NOT_SELECTED("Not Selected");

    public static final Set<Gender> genders = Collections.unmodifiableSet(Set.of(Gender.values()));

    private String genderName;

    Gender(String genderName) {
        this.genderName = genderName;
    }

    public String getGenderName() {
        return genderName;
    }

    public static Gender findByName(String genderName) {
        for (Gender gender : genders) {
            if (gender.getGenderName().equalsIgnoreCase(genderName)) {
                return gender;
            }
        }
        return NOT_SELECTED;
    }*/
}