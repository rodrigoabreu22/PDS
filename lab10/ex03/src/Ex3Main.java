package ex03.src;

import java.util.ArrayList;
import java.util.List;

public class Ex3Main {
    public static void main(String[] args) {
        CommandInvoker invoker = new CommandInvoker();
        List<String> list = new ArrayList<>();

        System.out.println("------ AddCommand ------");
        
        Command add1 = new AddCommand<>(list, "Hello World");
        Command add2 = new AddCommand<>(list, "Goodbye World");
        Command add3 = new AddCommand<>(list, "The End");

        invoker.setCommand(add1);
        invoker.executeCommand();
        invoker.setCommand(add2);
        invoker.executeCommand();
        invoker.setCommand(add3);
        invoker.executeCommand();

        System.out.println("Adding three elements to list");
        System.out.println(list);

        invoker.undoCommand();
        System.out.println("\nUndoing last command");
        System.out.println(list);

        System.out.println("\n------ RemoveCommand ------");

        Command remove1 = new RemoveCommand<>(list, "Goodbye World");
        Command remove2 = new RemoveCommand<>(list, "Hello World");

        invoker.setCommand(remove1);
        invoker.executeCommand();
        invoker.setCommand(remove2);
        invoker.executeCommand();

        System.out.println("Removing two elements from list");
        System.out.println(list);

        invoker.undoCommand();

        System.out.println("\nUndoing last command");
        System.out.println(list);
    }
}
