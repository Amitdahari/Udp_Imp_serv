public class packet {
    private int size;
    private int client;
    private String packet_name;
    private float finish_time;

    public packet(int client, String packet_name, int size) {
        this.size = size;
        this.client = client;
        this.packet_name = packet_name;
        finish_time=0;
    }

    public float getFinish_time() {
        return finish_time;
    }


    public String getPacket_name() {
        return packet_name;
    }

    public void setFinish_time(float finish_time) {
        this.finish_time = finish_time;
    }

    public int getSize() {
        return size;
    }

    public int getClient() {
        return client;
    }


    public void setSize(int size) {
        this.size = size;
    }

}
