import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class ScanThread implements Runnable{
    private final static Logger logger = LoggerFactory.getLogger(ScanThread.class);
    private InetSocketAddress address;
    private  int timeOut;
    CopyOnWriteArrayList<ScanResult> results;

    public ScanThread(InetSocketAddress address, int timeOut, CopyOnWriteArrayList<ScanResult> results) {
        this.address = address;
        this.timeOut = timeOut;
        this.results = results;
    }

    @Override
    public void run() {
        String currentThread = Thread.currentThread().getName();
        logger.info("Thread {} run", currentThread);
        boolean status = false;
        try (Socket socket = new Socket()) {
            socket.connect(address, timeOut);
            status = true;
        } catch (IOException e) {
            status = false;
        } finally {
//            StringBuilder sb = new StringBuilder(address.toString() + " " + status);
//            System.out.println(sb.toString());
            this.results.add(new ScanResult(address.getHostName(), address.getPort(), status));
            logger.info("Thread {} end", currentThread);
        }
    }
}
