package alex.valker91.model;

import java.util.Random;

public enum Type {

    INTEGER("INTEGER"),
    VARCHAR("VARCHAR(255)"),
    BOOLEAN("BOOLEAN"),
    TIMESTAMP("TIMESTAMP");

    public String getName() {
        return name;
    }

    private String name;

    private Type(String name) {
        this.name = name;
    }

    public static Type getRandomType() {
        Type[] values = Type.values();
        Random random = new Random();
        return values[random.nextInt(values.length)];
    }
}
