package com.github.cbryant02.xhvm.commands.impl.pop;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.assembly.MathOp;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

import static com.github.cbryant02.xhvm.assembly.StdRegister.*;
import static com.github.cbryant02.xhvm.assembly.StdSymbol.SP;

@MatchRules(type = CommandType.POP, argRegex = "static")
public class PopStaticCommand extends Command {
    @Override
    public void translate(String filename, String arg1, int arg2, int id, AssemblyBuilder builder) {
        String symbol = filename.substring(0, filename.lastIndexOf(".")) + "." + arg2;
        builder.address(SP)
                .set(MathOp.compoundRegister(A, M), MathOp.decrement(M))
                .set(D, M)
                .address(symbol)
                .set(M, D);
    }
}
