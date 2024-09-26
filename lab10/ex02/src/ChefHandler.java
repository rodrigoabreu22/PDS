package ex02.src;

public interface ChefHandler {
    public void handleRequest(String request);
    public ChefHandler setNextChef(ChefHandler nextChef);
}