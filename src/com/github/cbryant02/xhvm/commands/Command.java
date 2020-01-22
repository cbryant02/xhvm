package com.github.cbryant02.xhvm.commands;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;

public abstract class Command {
    private static int nextId = 0;

    public String translate(String arg1, int arg2) {
        AssemblyBuilder builder = new AssemblyBuilder();
        translate(nextId++, arg1, arg2, builder);
        return builder.toString();
    }

    protected abstract void translate(int id, String arg1, int arg2, AssemblyBuilder builder);
}
