package ex03.src;

public class CommandInvoker {
    private CommandHistory history = new CommandHistory();
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
        history.push(command);
    }

    public void undoCommand() {
        Command command = history.pop();
        command.undo();
    }
}
