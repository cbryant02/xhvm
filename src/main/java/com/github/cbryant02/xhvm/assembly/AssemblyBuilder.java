package com.github.cbryant02.xhvm.assembly;

import java.util.StringJoiner;

public class AssemblyBuilder {
    private StringJoiner joiner = new StringJoiner(System.lineSeparator(), "", System.lineSeparator());

    public AssemblyBuilder address(String symbolName) {
        joiner.add("@" + symbolName);
        return this;
    }

    public AssemblyBuilder address(StdSymbol symbol) {
        return address(symbol.name());
    }

    public AssemblyBuilder address(int address) {
        return address(String.valueOf(address));
    }

    public AssemblyBuilder set(StdRegister dest, StdRegister comp) {
        joiner.add(dest.name() + "=" + comp.name());
        return this;
    }

    public AssemblyBuilder set(StdRegister dest, MathOp comp) {
        joiner.add(dest.name() + "=" + comp.toString());
        return this;
    }

    public AssemblyBuilder set(MathOp dest, MathOp comp) {
        joiner.add(dest.toString() + "=" + comp.toString());
        return this;
    }

    public AssemblyBuilder jump(StdRegister dest, MathOp comp, StdJump jump) {
        joiner.add(dest.name() + "=" + comp.toString() + ";" + jump.name());
        return this;
    }

    public AssemblyBuilder jump(MathOp comp, StdJump jump) {
        joiner.add(comp.toString() + ";" + jump.name());
        return this;
    }

    public AssemblyBuilder jump(StdRegister comp, StdJump jump) {
        joiner.add(comp.name() + ";" + jump.name());
        return this;
    }

    public AssemblyBuilder jump() {
        joiner.add("0;JMP");
        return this;
    }

    public AssemblyBuilder label(String name) {
        joiner.add("(" + name + ")");
        return this;
    }

    public AssemblyBuilder increment(StdRegister r) {
        joiner.add(r.name() + "=" + r.name() + "+1");
        return this;
    }

    public AssemblyBuilder increment(StdRegister r, int count) {
        for (int i = 0; i < count; i++) {
            increment(r);
        }
        return this;
    }

    public AssemblyBuilder decrement(StdRegister r) {
        joiner.add(r.name() + "=" + r.name() + "-1");
        return this;
    }

    public AssemblyBuilder decrement(StdRegister r, int count) {
        for (int i = 0; i < count; i++) {
            decrement(r);
        }
        return this;
    }

    @Override
    public String toString() {
        return joiner.toString();
    }
}
