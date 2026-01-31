package game.service.loggers;

public class ConsoleLogger implements GameLogger {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}