public class ScanResult {
    private String host;
    private int port;
    private boolean status;

    public ScanResult(String host, int port, boolean status) {
        this.host = host;
        this.port = port;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ScanResult{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", status=" + status +
                '}';
    }
}

//"host" : [ip:port]
//"res" : [true/false]
