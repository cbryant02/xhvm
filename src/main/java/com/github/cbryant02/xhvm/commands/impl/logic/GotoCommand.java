package com.github.cbryant02.xhvm.commands.impl.logic;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

@MatchRules(type = CommandType.GOTO)
public class GotoCommand extends Command {
    @Override
    public void translate(String filename, String arg1, int arg2, int id, AssemblyBuilder builder) {
        builder.address(arg1)
                .jump();
    }
}
