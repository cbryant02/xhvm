package com.github.cbryant02.xhvm.commands.impl.math;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.assembly.MathOp;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

import static com.github.cbryant02.xhvm.assembly.StdJump.JEQ;
import static com.github.cbryant02.xhvm.assembly.StdRegister.*;
import static com.github.cbryant02.xhvm.assembly.StdSymbol.SP;

@MatchRules(type = CommandType.MATH, argRegex = "eq")
public class EqualCommand extends Command {
    @Override
    public void translate(int id, String arg1, int arg2, AssemblyBuilder builder) {
        final String escape = "eq_i" + id;
        builder.address(SP)                     // Pop top of stack into D
                .decrement(M)
                .set(A, M)
                .set(D, M)
                .decrement(A)                   // Subtract D from next stack value and store result in D
                .set(D, MathOp.subtract(M, D))
                .set(M, MathOp.TRUE)            // Set top of stack to TRUE
                .address(escape)                // Jump to escape if D=0
                .jump(D, JEQ)
                .address(SP)                    // Set top of stack to FALSE if D!=0
                .set(A, MathOp.decrement(M))
                .set(M, MathOp.FALSE)
                .label(escape);                 // escape label: (eq_esc_#)
    }
}
