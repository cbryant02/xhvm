package com.github.cbryant02.xhvm.commands.impl.math;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.assembly.MathOp;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

import static com.github.cbryant02.xhvm.assembly.StdJump.JLT;
import static com.github.cbryant02.xhvm.assembly.StdRegister.*;
import static com.github.cbryant02.xhvm.assembly.StdSymbol.SP;

@MatchRules(type = CommandType.MATH, argRegex = "lt")
public class LessCommand extends Command {
    @Override
    public void translate(String filename, String arg1, int arg2, int id, AssemblyBuilder builder) {
        final String escape = "lt_i" + id;
        builder.address(SP)
                .set(MathOp.compoundRegister(A, M), MathOp.decrement(M))
                .set(D, M)
                .decrement(A)
                .set(D, MathOp.subtract(M, D))
                .set(M, MathOp.TRUE)
                .address(escape)
                .jump(D, JLT)
                .address(SP)
                .set(A, MathOp.decrement(M))
                .set(M, MathOp.FALSE)
                .label(escape);
    }
}
