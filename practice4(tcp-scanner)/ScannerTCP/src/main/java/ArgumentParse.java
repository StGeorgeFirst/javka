import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArgumentParse {
    private static final Logger logger = LoggerFactory.getLogger(ArgumentParse.class);
    private final String[] arguments;

    public ArgumentParse(String[] arguments) {
        try {
            checkArgument(arguments);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
        }
        this.arguments = arguments;
    }

    public ParseResult parse() {
        int keyThreadIndex = search("-t");
        int numOfThreads;
        if (keyThreadIndex == -1) {
            numOfThreads = 4; // МОЖНО И БОЛЬШЕ
        } else {
            numOfThreads = Integer.parseInt(arguments[keyThreadIndex + 1]);
        }

        List<String>host = new LinkedList<>();
        List<Integer> ports = new LinkedList<>();
        parseIP(host);
        parsePorts(ports);
        List<InetSocketAddress> addrPair = new ArrayList<>();
        for (String ip : host) {
            for (Integer port : ports) {
                addrPair.add(new InetSocketAddress(ip, port));
            }
        }
        return new ParseResult(addrPair, numOfThreads);
    }

    private void parseIP(List<String> ipAddr) {
        int keyPortIndex = search("-p");
        for (int i = 2; i < keyPortIndex; i++) {
            String[] ip = arguments[i].split(",");
            if (!ip[0].contains("-")) {
                ipAddr.add(ip[0]);
                continue;
            }
            parseRangeIp(ip[0], ipAddr);
        }
    }

    private void parseRangeIp(String s, List<String> ipAddr) {
        String[] octets = s.split("\\.");
        String[] range = octets[3].split("-");
        int start = Integer.parseInt(range[0]);
        int stop = Integer.parseInt(range[1]);
        for (int i = start; i < stop + 1; i++) {
            StringBuilder sb = new StringBuilder(octets[0] + "." + octets[1] + "." + octets[2] + ".");
            ipAddr.add(sb.append(i).toString());
        }
    }

    private void parsePorts(List<Integer> ports) {
        int start = 1 + search("-p");
        int keyThreadIndex = search("-t");
        int finish = arguments.length;
        if (keyThreadIndex != -1) {
            finish = keyThreadIndex;
        }
        for (int i = start; i < finish; i++) {
            String[] port = arguments[i].split(",");
            if (!port[0].contains("-")) {
                ports.add(Integer.parseInt(port[0]));
                continue;
            }
            parseRangePorts(port[0], ports);
        }
    }

    private void parseRangePorts(String s, List<Integer> ports) {
        String[] range = s.split("-");
        int start = Integer.parseInt(range[0]);
        int stop = Integer.parseInt(range[1]);
        for (int i = start; i < stop + 1; i++) {
            ports.add(i);
        }
    }

    // TODO: ПЕРЕДЕЛАТЬ (не выкидывает нужные исключения)
    private void checkArgument(String[] arguments) {
        if (arguments.length < 6) {
            throw new IllegalArgumentException("Incorrect format argument");
        }
        if (!arguments[0].equals("scan") | !arguments[1].equals("-h")) {
            throw new IllegalArgumentException("Incorrect format argument");
        }
        for (int i = 0; i < 3; i++) {
            if (arguments[i].equals("-p")) {
                throw new IllegalArgumentException("Incorrect format argument");
            }
        }
        for (int i = 0; i < 5; i++) {
            if (arguments[i].equals("-t")) {
                throw new IllegalArgumentException("Incorrect format argument");
            }
        }
    }

    private int search(String item) {
        for (int i = 3; i < arguments.length; i++) {
            if (arguments[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }
}
