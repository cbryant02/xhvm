package com.github.cbryant02.xhvm;

import com.github.cbryant02.xhvm.commands.Command;
import com.github.cbryant02.xhvm.commands.CommandInfo;
import com.github.cbryant02.xhvm.commands.CommandRegistry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

public class Translator {
    private File outputFile;
    private Writer out;

    public Translator(File outputFile) throws IOException {
        updateOutputFile(outputFile);
    }

    public void updateOutputFile(File outputFile) throws IOException {
        this.outputFile = outputFile;
        this.out = new FileWriter(outputFile);
    }

    public void write(CommandInfo info) throws IOException {
        Command impl = Objects.requireNonNull(CommandRegistry.getImpl(info));
        out.write(impl.translate(outputFile.getName(), info.arg1, info.arg2));
    }

    public void close() throws IOException {
        out.close();
    }
}