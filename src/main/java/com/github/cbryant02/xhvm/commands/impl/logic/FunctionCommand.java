package com.github.cbryant02.xhvm.commands.impl.logic;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.assembly.MathOp;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

import static com.github.cbryant02.xhvm.assembly.StdRegister.A;
import static com.github.cbryant02.xhvm.assembly.StdRegister.M;
import static com.github.cbryant02.xhvm.assembly.StdSymbol.SP;

@MatchRules(type = CommandType.FUNCTION)
public class FunctionCommand extends Command {
    @Override
    public void translate(String filename, String arg1, int arg2, int id, AssemblyBuilder builder) {
        builder.label(arg1);
        for (int i = 0; i < arg2; i++) {
            builder.address(SP)
                    .set(A, M)
                    .set(M, MathOp.FALSE)
                    .address(SP)
                    .increment(M);
        }
    }
}