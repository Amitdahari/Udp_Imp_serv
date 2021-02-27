import java.util.ArrayList;
import java.util.List;

public class Common {
    private static double ticker=1;
    private static double last_vr_finish=0;
    private static List<packet> que1=new ArrayList<>();
    private static List<packet> que2=new ArrayList<>();
    private static List<packet> que3=new ArrayList<>();
    private static List<packet> que4=new ArrayList<>();
    private static List<packet> head_queue=new ArrayList<>();
    private static final double wieght_queue1=1;
    private static final double wieght_queue2=3;
    private static final double wieght_queue3=2;
    private static final double wieght_queue4=5;


    public static double getWieght_queue1() {
        return wieght_queue1;
    }

    public static double getWieght_queue2() {
        return wieght_queue2;
    }

    public static double getWieght_queue3() {
        return wieght_queue3;
    }

    public static double getWieght_queue4() {
        return wieght_queue4;
    }

    public static double getTicker() {
        return ticker;
    }

    public static double getLast_vr_finish() {
        return last_vr_finish;
    }

    public static List<packet> getQue1() {
        return que1;
    }

    public static List<packet> getQue2() {
        return que2;
    }

    public static List<packet> getQue3() {
        return que3;
    }

    public static List<packet> getQue4() {
        return que4;
    }

    public static List<packet> getHead_queue() {
        return head_queue;
    }

    public static void setLast_vr_finish(double last_vr_finish) {
        Common.last_vr_finish = last_vr_finish;
    }

    public static void setTicker(double ticker) {
        Common.ticker = ticker;
    }
}
