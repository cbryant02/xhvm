package com.github.cbryant02.xhvm.commands.impl.push;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

import static com.github.cbryant02.xhvm.assembly.StdRegister.*;
import static com.github.cbryant02.xhvm.assembly.StdSymbol.SP;

@MatchRules(type = CommandType.PUSH, arg1 = "constant")
public class PushConstantCommand extends Command {
    @Override
    public void translate(int id, int value, AssemblyBuilder builder) {
        builder.address(value)    // Save constant to D register
                .set(D, A)
                .address(SP)      // Jump to top of stack
                .set(A, M)
                .set(M, D)        // Push constant to top of stack
                .address(SP)
                .increment(M);    // Advance stack pointer
    }
}