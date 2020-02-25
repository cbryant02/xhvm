package com.github.cbryant02.xhvm.commands.impl.logic;

import com.github.cbryant02.xhvm.assembly.AssemblyBuilder;
import com.github.cbryant02.xhvm.assembly.MathOp;
import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandType;
import com.github.cbryant02.xhvm.commands.MatchRules;

import static com.github.cbryant02.xhvm.assembly.StdRegister.*;
import static com.github.cbryant02.xhvm.assembly.StdSymbol.*;

@MatchRules(type = CommandType.RETURN)
public class ReturnCommand extends Command {
    @Override
    public void translate(String filename, String arg1, int arg2, int id, AssemblyBuilder builder) {
        builder.address(LCL)
                .set(D, M)
                .address(5)
                .set(A, MathOp.subtract(D, A))
                .set(D, M)
                .address(R13)
                .set(M, D)
                .address(SP)
                .set(A, MathOp.decrement(M))
                .set(D, M)
                .address(ARG)
                .set(A, M)
                .set(M, D)
                .set(D, MathOp.increment(A))
                .address(SP)
                .set(M, D)
                .address(LCL)
                .set(MathOp.compoundRegister(A, M), MathOp.decrement(M))
                .set(D, M)
                .address(THAT)
                .set(M, D)
                .address(LCL)
                .set(MathOp.compoundRegister(A, M), MathOp.decrement(M))
                .set(D, M)
                .address(THIS)
                .set(M, D)
                .address(LCL)
                .set(MathOp.compoundRegister(A, M), MathOp.decrement(M))
                .set(D, M)
                .address(ARG)
                .set(M, D)
                .address(LCL)
                .set(A, MathOp.decrement(M))
                .set(D, M)
                .address(LCL)
                .set(M, D)
                .address(R13)
                .set(A, M)
                .jump();
    }
}
