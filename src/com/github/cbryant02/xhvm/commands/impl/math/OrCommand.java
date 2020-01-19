package com.github.cbryant02.xhvm.commands.impl.math;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.assembly.MathOp;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

import static com.github.cbryant02.xhvm.assembly.StdRegister.*;
import static com.github.cbryant02.xhvm.assembly.StdSymbol.SP;

@MatchRules(type = CommandType.MATH, arg1 = "or")
public class OrCommand extends Command {
    @Override
    protected void translate(int id, int arg, AssemblyBuilder builder) {
        builder.address(SP)
                .set(MathOp.compoundRegister(A, M), MathOp.decrement(M))   // Pop top of stack into D
                .set(D, M)
                .decrement(A)   // Push M|D to top of stack
                .set(M, MathOp.or(M, D));
    }
}