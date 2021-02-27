import static java.lang.Thread.sleep;

public class run_wfq implements Runnable{

    @Override
    public void run() {
        next_packet();
    }

    //calculate who is the next packet to send
    public void next_packet(){
        float min_result1=0;
        float min_result2=0;
        float min_result_final=0;
        float p1_size=0;
        float p2_size=0;
        float p3_size=0;
        float p4_size=0;
        boolean find=false;

        while (!find){
            if(Common.getQue1().size()>0){
                p1_size=Common.getQue1().get(0).getFinish_time();
            }
            else p1_size=0;

            if(Common.getQue2().size()>0){
                p2_size=Common.getQue2().get(0).getFinish_time();
            }
            else p2_size=0;

            if(Common.getQue3().size()>0){
                p3_size=Common.getQue3().get(0).getFinish_time();
            }
            else p3_size=0;

            if(Common.getQue4().size()>0){
                p4_size=Common.getQue4().get(0).getFinish_time();
            }
            else p4_size=0;

            if(p1_size>0 && p2_size>0){
                min_result1=Math.min(p1_size,p2_size);
            }
            else if(p1_size==0 && p2_size>0){
                min_result1=p2_size;
            }
            else if(p1_size>0 && p2_size==0){
                min_result1=p1_size;
            }

            if(p3_size>0 && p4_size>0){
                min_result1=Math.min(p3_size,p4_size);
            }
            else if(p3_size==0 && p4_size>0){
                min_result1=p4_size;
            }
            else if(p3_size>0 && p4_size==0){
                min_result1=p3_size;
            }

            if(min_result1>0 && min_result2>0){
                min_result_final=Math.min(min_result1,min_result2);
            }
            else if(min_result1>0 && min_result2==0){
                min_result_final=min_result1;
            }
            else if(min_result1==0 && min_result2>0){
                min_result_final=min_result2;
            }

            if(min_result_final==p1_size && Common.getQue1().size() > 0){
                Common.getHead_queue().add(Common.getQue1().get(0));
                Common.setLast_vr_finish(p1_size);
                Common.getQue1().remove(0);
                find=true;
            }
            else if(min_result_final==p2_size && Common.getQue2().size() > 0){
                Common.getHead_queue().add(Common.getQue2().get(0));
                Common.setLast_vr_finish(p2_size);
                Common.getQue2().remove(0);
                find=true;
            }
            else if(min_result_final==p3_size && Common.getQue3().size() > 0){
                Common.getHead_queue().add(Common.getQue3().get(0));
                Common.setLast_vr_finish(p3_size);
                Common.getQue3().remove(0);
                find=true;
            }
            else if(min_result_final==p4_size && Common.getQue4().size() > 0) {
                Common.getHead_queue().add(Common.getQue4().get(0));
                Common.setLast_vr_finish(p4_size);
                Common.getQue4().remove(0);
                find=true;
            }
            else {
                try {
                    sleep(100);
                    next_packet();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        p1_size=0;
        p2_size=0;
        p3_size=0;
        p4_size=0;
        if (Common.getHead_queue().size() > 0) {
            run_packet(Common.getHead_queue().get(0));
        }
    }

    //run packet and update clock (each i step is byte send)
    public void run_packet(packet p){
        System.out.println("now send packet from client: "+p.getClient()+"\n"+
                "packet number: "+p.getPacket_name()+"\n"+
                "current ticker: "+Common.getTicker()+"\n");
        int count_flows=0;
        for (int i=0;i<p.getSize();i++){
            if(Common.getQue1().size()>0 || Common.getHead_queue().get(0).getClient()==1)count_flows+=1;
            if(Common.getQue2().size()>0 || Common.getHead_queue().get(0).getClient()==2)count_flows+=1;
            if(Common.getQue3().size()>0 || Common.getHead_queue().get(0).getClient()==3)count_flows+=1;
            if(Common.getQue4().size()>0 || Common.getHead_queue().get(0).getClient()==4)count_flows+=1;
            if(count_flows>0) {
                Common.setTicker(Common.getTicker() + 1 / count_flows);
            }
            count_flows=0;
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n"+"finish with client: "+p.getClient()+"\n"+
                "packet number: "+p.getPacket_name()+"\n"+
                "now ticker is: "+Common.getTicker()+"\n");
        Common.getHead_queue().remove(0);

        next_packet();
    }
}
