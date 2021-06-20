import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class  PortScanner {
    private static final Logger logger = LoggerFactory.getLogger(PortScanner.class);
    private int timeOut = 100;
    private int poolSize = 1;
    CopyOnWriteArrayList<ScanResult> scanResults;

    public CopyOnWriteArrayList<ScanResult> start(ParseResult pr) throws InterruptedException {

//        System.out.println("Start scanning ...");
        logger.info("Start scanning ...");

        poolSize = pr.getNumOfThreads();
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        scanResults = new CopyOnWriteArrayList<>();

        List<InetSocketAddress> addrPair = pr.getAddrPair();
        Collections.shuffle(addrPair);
//        for (InetSocketAddress address : addrPair) {
//            logger.info(address.toString());
//        }

        for (InetSocketAddress address : addrPair) {
            queue.put(new ScanThread(address, timeOut, scanResults));
        }

        ThreadPoolExecutor executor = new ThreadPoolExecutor(poolSize, poolSize, timeOut, TimeUnit.MILLISECONDS, queue);
        executor.prestartAllCoreThreads();
        while (executor.getTaskCount() != executor.getCompletedTaskCount() ) {

        }

//        System.out.println("Completed task count: " + executor.getCompletedTaskCount());
        logger.info("Completed task count: {}", executor.getCompletedTaskCount());

        executor.shutdown();

//        System.out.println("Scanning successful");
        logger.info("Scanning successful");

        return scanResults;
    }
}
