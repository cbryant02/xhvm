package com.github.cbryant02.xhvm.commands;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;

public abstract class Command {
    private static int nextId = 0;

    public String translate(int arg) {
        AssemblyBuilder builder = new AssemblyBuilder();
        translate(nextId++, arg, builder);
        return builder.toString();
    }

    protected abstract void translate(int id, int arg, AssemblyBuilder builder);
}
