package com.mycompany.dentalclinic.ui;

public enum MainMenuOption {

    EXIT(0, "Exit"),
    APPOINTMENTS_OPTIONS(1, "Appointment Options"),
    CUSTOMER_OPTIONS(2, "Customer Options!");

    private final int value;
    private final String name;

    private MainMenuOption(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static MainMenuOption fromValue(int value) {
        for (MainMenuOption mmo : MainMenuOption.values()) {
            if (mmo.getValue() == value) {
                return mmo;
            }
        }
        return EXIT;
    }
}
