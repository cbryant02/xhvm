package com.github.cbryant02.xhvm.commands.impl.push;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

import static com.github.cbryant02.xhvm.assembly.StdSymbol.SP;
import static com.github.cbryant02.xhvm.assembly.StdRegister.*;

@MatchRules(type = CommandType.PUSH, argRegex = "constant")
public class PushConstantCommand extends Command {
    @Override
    public void translate(int id, String arg1, int arg2, AssemblyBuilder builder) {
        builder.address(arg2)      // Save constant to D register
                .set(D, A)
                .address(SP)      // Jump to top of stack
                .set(A, M)
                .set(M, D)        // Push constant to top of stack
                .address(SP)
                .increment(M);    // Advance stack pointer
    }
}