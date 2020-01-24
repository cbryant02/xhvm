package com.github.cbryant02.xhvm.commands;

import com.github.cbryant02.xhvm.VMTranslator;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CommandRegistry {
    private static final Set<Class<? extends Command>> commandClasses;

    static {
        Reflections flex = new Reflections(VMTranslator.class.getPackageName());
        commandClasses = flex.getSubTypesOf(Command.class)
                .stream()
                .filter(cl -> {
                    Object a = cl.getAnnotation(MatchRules.class);
                    if (a == null) {
                        System.err.println("[WARNING] No match rules declared for " + cl.getName() + "!");
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.toCollection(HashSet::new));
    }

    public static Command getImpl(CommandInfo info) {
        Class<? extends Command> target = commandClasses.parallelStream()
                .filter(candidate -> {
                    MatchRules rules = candidate.getDeclaredAnnotation(MatchRules.class);
                    Pattern argPattern = Pattern.compile(rules.argRegex());
                    Matcher argMatcher = argPattern.matcher(info.arg1);
                    return info.type.equals(rules.type()) && argMatcher.matches();
                })
                .findFirst()
                .orElse(null);

        if (target == null) {
            throw new UnsupportedOperationException("Could not find an implementation for \"" + info.source + "\"!");
        }

        try {
            Constructor<? extends Command> constructor = target.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    }

    private CommandRegistry() {
    }
}
