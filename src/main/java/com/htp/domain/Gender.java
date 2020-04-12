package com.htp.domain;

public enum Gender {
    MALE,FEMALE,NOT_SELECTED
    /*M("Male"),
    F("Female"),
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
