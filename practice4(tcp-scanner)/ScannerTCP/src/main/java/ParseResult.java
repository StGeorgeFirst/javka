import java.net.InetSocketAddress;
import java.util.List;

public class ParseResult {
    private final List<InetSocketAddress> addrPair;
    private final int numOfThreads;

    public ParseResult(List<InetSocketAddress> addrPair, int numOfThreads) {
        this.addrPair = addrPair;
        this.numOfThreads = numOfThreads;
    }

    public List<InetSocketAddress> getAddrPair() {
        return addrPair;
    }

    public int getNumOfThreads() {
        return numOfThreads;
    }
}
