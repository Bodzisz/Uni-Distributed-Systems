public class ProcService {

    public String add(final int x, final int y) {
        System.out.println("Message received: " + x + " " + y);
        return String.valueOf(x + y);
    }
}
