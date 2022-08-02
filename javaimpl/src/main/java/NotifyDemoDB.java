import static boot.Boot.boot;

import common.DefaultRecordListener;
import common.RecordListener;
import config.DtsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;

public class NotifyDemoDB {

    private static final Logger log = LoggerFactory.getLogger(NotifyDemoDB.class);

    public static Map<String, RecordListener> buildRecordListener() {
        // user can impl their own listener,value can be mysql, oracle, postgresql
        RecordListener oracleRecordPrintListener = new DefaultRecordListener("mysql");
        return Collections.singletonMap("RecordPrinter", oracleRecordPrintListener);
    }

    public static void main(String[] args) {
        try{
            boot(DtsConfig.getConfig(), buildRecordListener());
        }catch(Throwable e){
            log.error("NotifyDemo: failed cause " + e.getMessage(), e);
            throw e;
        } finally {
            System.exit(0);
        }
    }
}
