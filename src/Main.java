public class Main {
    public static void main(String[] args) {

       Thread router = new Thread(new router());
       Thread wfq = new Thread(new run_wfq());
       router.start();
       wfq.start();

    }
}
