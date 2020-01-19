package com.github.cbryant02.xhvm.commands.impl.math;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.assembly.MathOp;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

import static com.github.cbryant02.xhvm.assembly.StdRegister.A;
import static com.github.cbryant02.xhvm.assembly.StdRegister.M;
import static com.github.cbryant02.xhvm.assembly.StdSymbol.SP;

@MatchRules(type = CommandType.MATH, arg1 = "not")
public class NotCommand extends Command {
    @Override
    protected void translate(int id, int arg, AssemblyBuilder builder) {
        builder.address(SP)
                .set(A, MathOp.decrement(M))
                .set(M, MathOp.not(M));
    }
}
