package com.github.cbryant02.xhvm.commands.impl.math;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.assembly.MathOp;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

import static com.github.cbryant02.xhvm.assembly.StdRegister.A;
import static com.github.cbryant02.xhvm.assembly.StdRegister.M;
import static com.github.cbryant02.xhvm.assembly.StdSymbol.SP;

@MatchRules(type = CommandType.MATH, argRegex = "neg")
public class NegateCommand extends Command {
    @Override
    public void translate(int id, String arg1, int arg2, AssemblyBuilder builder) {
        builder.address(SP)
                .set(A, MathOp.decrement(M))
                .set(M, MathOp.negate(M));
    }
}
