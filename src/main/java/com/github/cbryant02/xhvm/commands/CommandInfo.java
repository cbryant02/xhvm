package com.github.cbryant02.xhvm.commands;

import java.util.List;
import java.util.Objects;

public class CommandInfo {
    private static final List<CommandType> takesTwoArguments = List.of(
            CommandType.PUSH,
            CommandType.POP,
            CommandType.FUNCTION,
            CommandType.CALL
    );

    public final String source;
    public final CommandType type;
    public final String arg1;
    public final int arg2;
    public final boolean hasTwoArguments;

    public CommandInfo(String source) {
        this.source = source;

        String[] components = source.split(" ");
        type = CommandType.fromString(components[0]);

        if (type == CommandType.RETURN) arg1 = null;
        else if (type == CommandType.MATH) arg1 = type.getSource();
        else arg1 = components[1].trim();

        if (takesTwoArguments.contains(type)) {
            arg2 = Integer.parseInt(components[2].trim());
            hasTwoArguments = true;
        } else {
            arg2 = Integer.MIN_VALUE;
            hasTwoArguments = false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandInfo that = (CommandInfo) o;
        return type == that.type &&
                Objects.equals(arg1, that.arg1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, arg1);
    }

    @Override
    public String toString() {
        return "CommandMeta{" +
                "source='" + source + '\'' +
                ", type=" + type +
                ", arg1='" + arg1 + '\'' +
                ", arg2=" + arg2 +
                ", hasTwoArguments=" + hasTwoArguments +
                '}';
    }
}