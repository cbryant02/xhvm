package com.github.cbryant02.xhvm;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VMTranslator {
    public static void main(String[] args) throws IOException, InterruptedException {
        File workingDir = new File(System.getProperty("user.dir"));
        System.out.println("Current working directory is " + workingDir.getCanonicalPath());

        File dir;
        if (args.length > 0) {
            dir = new File(workingDir, args[0]);
            if (!dir.exists() | !dir.isDirectory()) {
                System.err.println("[ERROR] " + dir.getCanonicalPath() + " is not a valid directory. Exiting.");
                return;
            }
        } else dir = promptDirectory(workingDir);

        // Prompt for directory and scan for .vm files
        List<File> targets = locateVmFiles(dir);
        if (targets == null || targets.isEmpty()) {
            System.err.println("No files to process. Exiting.");
            return;
        }

        System.out.println(targets.stream()
                .map(File::getName)
                .collect(Collectors.joining(", ", "Found files: [", "]")));

        // Read and strip assembly from the .vm files
        byte[] strippedAsm = read(targets);

        // Parse files
        System.out.print("Writing assembly...");
        File outputFile = new File(dir.getAbsolutePath() + "/" + dir.getName() + ".asm");
        Parser parser = new Parser(strippedAsm);
        Translator translator = new Translator(outputFile);
        while (parser.hasNext()) {
            parser.advance();
            translator.write(parser.getCurrent());
        }
        translator.close();
        System.out.println(" Done.");
        System.out.println("Results written to " + outputFile.getCanonicalPath());
    }

    private static File promptDirectory(File workingDir) throws IOException {
        System.out.print("Enter a directory: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String filename = in.readLine();

        File dir = new File(workingDir, filename);
        if (!dir.exists() || !dir.isDirectory()) {
            System.err.println("[ERROR] " + dir.getCanonicalPath() + " is not a valid directory. Please try again.");
            System.err.flush();
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return promptDirectory(workingDir);
        }
        return dir;
    }

    private static List<File> locateVmFiles(File dir) throws IOException {
        File[] files = dir.listFiles();
        if (files == null) {
            System.err.println("[ERROR] An unexpected I/O error has occurred. Please try again.");
            return null;
        }
        if (files.length < 1) System.err.println("[WARNING] " + dir.getName() + " is an empty directory.");
        List<File> ret = Arrays.stream(files)
                .filter(file -> file.getName().endsWith(".vm"))
                .collect(Collectors.toList());
        if (ret.isEmpty()) System.err.println("[WARNING] " + dir.getName() + " contains no .vm files.");
        return ret;
    }

    private static byte[] read(List<File> targets) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (Writer out = new OutputStreamWriter(baos)) {
            int n = 0;
            for (File target : targets) {
                System.out.print("Stripping " + target.getName() + " (" + (++n) + " of " + targets.size() + ")...");
                try (BufferedReader in = new BufferedReader(new FileReader(target))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        if (line.startsWith("//") || line.isBlank()) continue;
                        line = line.replaceAll("\\p{javaSpaceChar}+//.+", "");
                        out.write(line + System.lineSeparator());
                    }
                }
                System.out.println(" Done.");
            }
        }
        return baos.toByteArray();
    }
}