package com.github.cbryant02.xhvm.commands.impl.pop;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.assembly.MathOp;
import com.github.cbryant02.xhvm.assembly.StdSymbol;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

import static com.github.cbryant02.xhvm.assembly.StdRegister.*;
import static com.github.cbryant02.xhvm.assembly.StdSymbol.R15;
import static com.github.cbryant02.xhvm.assembly.StdSymbol.SP;

@MatchRules(type = CommandType.POP, argRegex = "local|argument|this|that")
public class PopCommand extends Command {
    @Override
    public void translate(String filename, String arg1, int arg2, int id, AssemblyBuilder builder) {
        StdSymbol segment = StdSymbol.fromArg(arg1);
        builder.address(arg2)
                .set(D, A)
                .address(segment)
                .set(D, MathOp.add(M, D))
                .address(R15)
                .set(M, D)
                .address(SP)
                .set(MathOp.compoundRegister(A, M), MathOp.decrement(M))
                .set(D, M)
                .address(R15)
                .set(A, M)
                .set(M, D);
    }
}
