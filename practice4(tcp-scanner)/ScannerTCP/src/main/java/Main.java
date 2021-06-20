import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class Main {

    public static void main(String[] args) throws InterruptedException {

//        scan -h 192.168.88.1-255, 192.168.1.1, 192.169.1.1 -p 80, 443, 8080, 3000-5000
        ArgumentParse ap = new ArgumentParse(args);
        ParseResult pr = ap.parse();
        PortScanner ps = new PortScanner();
        List<ScanResult> scanResults = ps.start(pr);
        SaveTo saveTo = new SaveTo();
        saveTo.saveToJSON(scanResults, "scanResult.json");



    }
}
