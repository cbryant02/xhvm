package com.github.cbryant02.xhvm.commands;

public enum CommandType {
    MATH,
    PUSH,
    POP,
    LABEL,
    GOTO,
    IF,
    FUNCTION,
    RETURN,
    CALL;

    private String source;

    public static CommandType fromString(String s) {
        if (s.startsWith("if")) {
            return makeInstance(IF, s);
        }

        for (CommandType value : CommandType.values()) {
            if (value.name().equalsIgnoreCase(s)) {
                value.source = s;
                return value;
            }
        }

        return makeInstance(MATH, s);
    }

    private static CommandType makeInstance(CommandType type, String source) {
        type.source = source;
        return type;
    }

    public String getSource() {
        return source;
    }
}
