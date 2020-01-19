package com.github.cbryant02.xhvm.commands;

import com.github.cbryant02.xhvm.commands.impl.math.AddCommand;
import com.github.cbryant02.xhvm.commands.impl.math.AndCommand;
import com.github.cbryant02.xhvm.commands.impl.math.EqualCommand;
import com.github.cbryant02.xhvm.commands.impl.math.GreaterCommand;
import com.github.cbryant02.xhvm.commands.impl.math.LessCommand;
import com.github.cbryant02.xhvm.commands.impl.math.NegateCommand;
import com.github.cbryant02.xhvm.commands.impl.math.NotCommand;
import com.github.cbryant02.xhvm.commands.impl.math.OrCommand;
import com.github.cbryant02.xhvm.commands.impl.math.SubtractCommand;
import com.github.cbryant02.xhvm.commands.impl.push.PushConstantCommand;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class CommandRegistry {
    private static final List<Class<? extends Command>> commandMap = List.of(
            AddCommand.class,
            AndCommand.class,
            EqualCommand.class,
            GreaterCommand.class,
            LessCommand.class,
            NegateCommand.class,
            NotCommand.class,
            OrCommand.class,
            SubtractCommand.class,
            PushConstantCommand.class
    );

    private static final List<Class<? extends Command>> warned = new ArrayList<>();

    public static Command getImpl(CommandInfo info) {
        Class<? extends Command> target = commandMap.parallelStream()
                .filter(candidate -> {
                    MatchRules rules = candidate.getDeclaredAnnotation(MatchRules.class);
                    if (rules == null) {
                        if (!warned.contains(candidate)) {
                            System.out.println("[WARNING] No match rules declared for " + candidate.getName() + "!");
                            warned.add(candidate);
                        }
                        return false;
                    }
                    return info.type.equals(rules.type()) && info.arg1.equals(rules.arg1());
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
