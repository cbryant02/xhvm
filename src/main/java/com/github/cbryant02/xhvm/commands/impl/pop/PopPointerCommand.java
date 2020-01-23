package com.github.cbryant02.xhvm.commands.impl.pop;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.assembly.MathOp;
import com.github.cbryant02.xhvm.assembly.StdSymbol;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

import static com.github.cbryant02.xhvm.assembly.StdRegister.*;
import static com.github.cbryant02.xhvm.assembly.StdSymbol.*;

@MatchRules(type = CommandType.POP, argRegex = "pointer")
public class PopPointerCommand extends Command {
    @Override
    public void translate(String filename, String arg1, int arg2, int id, AssemblyBuilder builder) {
        if (arg2 != 0 && arg2 != 1)
            throw new IllegalArgumentException("Argument for \"pop pointer #\" must be either 0 or 1");
        StdSymbol segment = (arg2 == 0) ? THIS : THAT;
        builder.address(SP)
                .set(MathOp.compoundRegister(A, M), MathOp.decrement(M))
                .set(D, M)
                .address(segment)
                .set(M, D);
    }
}
