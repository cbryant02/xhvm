package com.github.cbryant02.xhvm;

import com.github.cbryant02.xhvm.commands.CommandInfo;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class Parser {
    private final Scanner in;
    private CommandInfo current;

    public Parser(final byte[] input) {
        in = new Scanner(new ByteArrayInputStream(input));
    }

    public boolean hasNext() {
        return in.hasNextLine();
    }

    public void advance() {
        current = new CommandInfo(in.nextLine());
    }

    public CommandInfo getCurrent() {
        return current;
    }
}
