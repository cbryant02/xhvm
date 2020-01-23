package com.github.cbryant02.xhvm.assembly;

public enum StdSymbol {
    // VM pointers
    SP,
    LCL,
    ARG,
    THIS,
    THAT,
    TEMP,
    // Virtual registers
    R0,
    R1,
    R2,
    R3,
    R4,
    R5,
    R6,
    R7,
    R8,
    R9,
    R10,
    R11,
    R12,
    R13,
    R14,
    R15;

    public static StdSymbol fromArg(String arg1) {
        switch (arg1) {
            case "local":
                return LCL;
            case "argument":
                return ARG;
            case "this":
                return THIS;
            case "that":
                return THAT;
            case "temp":
                return TEMP;
        }
        throw new IllegalArgumentException("Invalid VM pointer argument passed to StdSymbol#fromArg");
    }
}
