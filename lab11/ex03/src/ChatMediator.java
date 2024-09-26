package ex03.src;
import java.util.ArrayList;
import java.util.List;

public class ChatMediator implements Mediator {
    private List<Colleague> colleagues = new ArrayList<Colleague>();

    public void addColleague(Colleague colleague) {
        colleagues.add(colleague);
    }

    @Override
    public void notify(Colleague sender, String message) {
        for (Colleague colleague : colleagues) {
            if (colleague != sender) {
                colleague.receiveMessage(message);
            }
        }
    }
}
