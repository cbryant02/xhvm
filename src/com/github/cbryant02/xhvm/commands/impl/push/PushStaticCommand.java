package com.github.cbryant02.xhvm.commands.impl.push;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

import static com.github.cbryant02.xhvm.assembly.StdRegister.*;
import static com.github.cbryant02.xhvm.assembly.StdSymbol.SP;

@MatchRules(type = CommandType.PUSH, argRegex = "static")
public class PushStaticCommand extends Command {
    @Override
    public void translate(String filename, String arg1, int arg2, int id, AssemblyBuilder builder) {
        String symbol = filename.substring(0, filename.lastIndexOf(".")) + "." + arg2;
        builder.address(arg2)
                .set(D, A)
                .address(symbol)
                .set(D, M)
                .address(SP)
                .set(A, M)
                .set(M, D)
                .address(SP)
                .increment(M);
    }
}