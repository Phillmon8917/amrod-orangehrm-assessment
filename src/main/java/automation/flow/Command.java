package automation.flow;

/*
 * This uses the Command design pattern.
 * It performs operations without exposing complex implementation details.
 * Allows doing and undoing operations easily, extendable to redoing.
 */
public interface Command
{
    void execute(String username, String password);

    void undo();
}

