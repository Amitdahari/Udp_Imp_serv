import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class router implements Runnable {



    @Override
    public void run() {
        start_listen();
    }

    //start the router
    private void start_listen() {
        try {
            ServerSocket serverSocket=new ServerSocket(8888);
            Socket socket;

            while (true){
                socket=serverSocket.accept();
                BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String res=reader.readLine();
                String split_res[]=res.split(":");
                packet p=new packet(Integer.valueOf(split_res[0]),split_res[1],Integer.valueOf(split_res[2]));
                input_packet(p);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //calculate the "new" size of packet and change it and add it to the queue flow by user id
    public void input_packet(packet p){
        switch (p.getClient()){
            case 1:
            if(Common.getLast_vr_finish()!=0) {
                p.setFinish_time(Math.max(Common.getLast_vr_finish()+p.getSize()/Common.getWieght_queue1(), Common.getTicker()) +p.getSize()/Common.getWieght_queue1());
            }
            else{
                p.setFinish_time(p.getSize());
            }
                Common.getQue1().add(p);
                break;

            case 2:
                if(Common.getLast_vr_finish()!=0) {
                    p.setFinish_time(Math.max(Common.getLast_vr_finish()+p.getSize()/Common.getWieght_queue2(), Common.getTicker()) +p.getSize()/Common.getWieght_queue2());
                    Common.getQue2().add(p);
                }
                else{
                    p.setFinish_time(p.getSize());
                }
                break;

            case 3:
                if(Common.getLast_vr_finish()!=0) {
                    p.setFinish_time(Math.max(Common.getLast_vr_finish()+p.getSize()/Common.getWieght_queue3(), Common.getTicker()) +p.getSize()/Common.getWieght_queue3());
                }
                else{
                    p.setFinish_time(p.getSize());
                }
                Common.getQue3().add(p);
                break;

            case 4:
                if(Common.getLast_vr_finish()!=0) {
                    p.setFinish_time(Math.max(Common.getLast_vr_finish()+p.getSize()/Common.getWieght_queue4(), Common.getTicker()) +p.getSize()/Common.getWieght_queue4());
                }
                else {
                    p.setFinish_time(p.getSize());
                }
                Common.getQue4().add(p);
                break;
        }
        System.out.println("got new packet from client: "+p.getClient()+"\n"+
                "packet number: "+p.getPacket_name()+"\n"+
                "packet finish time: "+p.getFinish_time()+"\n");
    }
}
