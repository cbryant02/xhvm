package com.github.cbryant02.xhvm.commands.impl.math;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.assembly.MathOp;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

import static com.github.cbryant02.xhvm.assembly.StdRegister.*;
import static com.github.cbryant02.xhvm.assembly.StdSymbol.SP;

@MatchRules(type = CommandType.MATH, argRegex = "sub")
public class SubtractCommand extends Command {
    @Override
    public void translate(int id, String arg1, int arg2, AssemblyBuilder builder) {
        builder.address(SP)
                .set(MathOp.compoundRegister(A, M), MathOp.decrement(M))   // Pop top of stack into D
                .set(D, M)
                .decrement(A)   // Push M-D to top of stack
                .set(M, MathOp.subtract(M, D));
    }
}
