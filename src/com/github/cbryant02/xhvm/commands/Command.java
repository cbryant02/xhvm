package com.github.cbryant02.xhvm.commands;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;

public abstract class Command {
    private static int nextId = 0;

    public String translate(String filename, String arg1, int arg2) {
        AssemblyBuilder builder = new AssemblyBuilder();
        translate(filename, arg1, arg2, nextId++, builder);
        return builder.toString();
    }

    protected abstract void translate(String filename, String arg1, int arg2, int id, AssemblyBuilder builder);
}
