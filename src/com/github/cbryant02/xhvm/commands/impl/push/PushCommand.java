package com.github.cbryant02.xhvm.commands.impl.push;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.assembly.MathOp;
import com.github.cbryant02.xhvm.assembly.StdSymbol;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

import static com.github.cbryant02.xhvm.assembly.StdRegister.*;
import static com.github.cbryant02.xhvm.assembly.StdSymbol.SP;

@MatchRules(type = CommandType.PUSH, argRegex = "local|argument|this|that")
public class PushCommand extends Command {
    @Override
    public void translate(int id, String arg1, int arg2, AssemblyBuilder builder) {
        StdSymbol segment = StdSymbol.fromArg(arg1);
        builder.address(arg2)
                .set(D, A)
                .address(segment)
                .set(A, MathOp.add(M, D))
                .set(D, M)
                .address(SP)
                .set(A, M)
                .set(M, D)
                .address(SP)
                .increment(M);
    }
}