package com.github.cbryant02.xhvm.assembly;

public enum StdOperator {
    ADD("+"),
    SUB("-"),
    AND("&"),
    OR("|"),
    NOT("!"),
    INC("+1"),
    DEC("-1");

    public final String ch;

    StdOperator(String ch) {
        this.ch = ch;
    }
}
