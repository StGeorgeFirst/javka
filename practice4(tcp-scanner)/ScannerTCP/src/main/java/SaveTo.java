import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;

public class SaveTo {

    private static final Logger logger = LoggerFactory.getLogger(SaveTo.class);

    public void saveToJSON(List<ScanResult> scanResults, String fileName) {
        StringBuilder sb = new StringBuilder("target/" + fileName);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(scanResults);
        try (FileWriter writer = new FileWriter(sb.toString(), false)) {
            writer.write(json);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
