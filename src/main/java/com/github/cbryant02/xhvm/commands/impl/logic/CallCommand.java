package com.github.cbryant02.xhvm.commands.impl.logic;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.assembly.MathOp;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

import static com.github.cbryant02.xhvm.assembly.StdRegister.*;
import static com.github.cbryant02.xhvm.assembly.StdSymbol.*;

@MatchRules(type = CommandType.CALL)
public class CallCommand extends Command {
    @Override
    public void translate(String filename, String arg1, int arg2, int id, AssemblyBuilder builder) {
        final String returnLabel = arg1 + ".return." + id;
        builder.address(returnLabel) // Push return address
                .set(D, A)
                .address(SP)
                .set(A, M)
                .set(M, D)
                .address(SP)
                .increment(M)
                .address(LCL) // Push LCL pointer
                .set(D, M)
                .address(SP)
                .set(A, M)
                .set(M, D)
                .address(SP)
                .increment(M)
                .address(ARG) // Push ARG pointer
                .set(D, M)
                .address(SP)
                .set(A, M)
                .set(M, D)
                .address(SP)
                .increment(M)
                .address(THIS) // Push THIS pointer
                .set(D, M)
                .address(SP)
                .set(A, M)
                .set(M, D)
                .address(SP)
                .increment(M)
                .address(THAT) // Push THAT pointer
                .set(D, M)
                .address(SP)
                .set(A, M)
                .set(M, D)
                .address(SP)
                .increment(M)
                .address(5) // Setup ARG for function
                .set(D, A)
                .address(arg2)
                .set(D, MathOp.add(D, A))
                .address(SP)
                .set(D, MathOp.subtract(M, D))
                .address(ARG)
                .set(M, D)
                .address(SP) // Setup LCL for function
                .set(D, M)
                .address(LCL)
                .set(M, D)
                .address(arg1) // Jump to function
                .jump()
                .label(returnLabel); // Declare return label
    }
}
