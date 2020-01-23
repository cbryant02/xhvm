package com.github.cbryant02.xhvm.assembly;

public class MathOp {
    public static final MathOp TRUE = new MathOp("-1");
    public static final MathOp FALSE = new MathOp("0");

    private final String raw;

    private MathOp(StdRegister a, StdRegister b) {
        this.raw = a.name() + b.name();
    }

    private MathOp(StdRegister a, StdRegister b, StdOperator operator) {
        this.raw = a.name() + operator.ch + b.name();
    }

    private MathOp(StdRegister r, StdOperator operator) {
        this.raw = r.name() + operator.ch;
    }

    private MathOp(StdOperator operator, StdRegister r) {
        this.raw = operator.ch + r.name();
    }

    private MathOp(String raw) {
        this.raw = raw;
    }

    public static MathOp add(StdRegister a, StdRegister b) {
        return new MathOp(a, b, StdOperator.ADD);
    }

    public static MathOp subtract(StdRegister a, StdRegister b) {
        return new MathOp(a, b, StdOperator.SUB);
    }

    public static MathOp and(StdRegister a, StdRegister b) {
        return new MathOp(a, b, StdOperator.AND);
    }

    public static MathOp or(StdRegister a, StdRegister b) {
        return new MathOp(a, b, StdOperator.OR);
    }

    public static MathOp negate(StdRegister r) {
        return new MathOp(StdOperator.SUB, r);
    }

    public static MathOp not(StdRegister r) {
        return new MathOp(StdOperator.NOT, r);
    }

    public static MathOp increment(StdRegister r) {
        return new MathOp(r, StdOperator.INC);
    }

    public static MathOp decrement(StdRegister r) {
        return new MathOp(r, StdOperator.DEC);
    }

    public static MathOp compoundRegister(StdRegister a, StdRegister b) {
        return new MathOp(a, b);
    }

    @Override
    public String toString() {
        return raw;
    }
}
