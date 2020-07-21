import boot.MysqlRecordPrinter;
import common.RecordListener;
import common.UserRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordgenerator.Names;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import static boot.Boot.boot;
import static recordgenerator.Names.*;

public class NotifyDemoTest {
    private static final Logger log = LoggerFactory.getLogger(NotifyDemoTest.class);

    public static Map<String, RecordListener> buildRecordListener() {
        // user can impl their own listener
        RecordListener mysqlRecordPrintListener = new RecordListener() {
            @Override
            public void consume(UserRecord record) {
                // consume record
                // MysqlRecordPrinter show how to go through record fields and get general attributes
                String ret = MysqlRecordPrinter.recordToString(record.getRecord());
                log.info(ret);
                record.commit(String.valueOf(record.getRecord().getSourceTimestamp()));
            }
        };
        return Collections.singletonMap("mysqlRecordPrinter", mysqlRecordPrintListener);
    }

    /**
     * This demo use  hard coded config. User can modify variable value for test
     * The detailed describe for var in resources/demoConfig
     */
    public static Properties getConfigs() {
        Properties properties = new Properties();
        // user password and sid for auth
        properties.setProperty(USER_NAME, "yanmen");
        properties.setProperty(PASSWORD_NAME, "yanmen123");
        properties.setProperty(SID_NAME, "dtshyye52491877bfk");
        // kafka consumer group general same with sid
        properties.setProperty(GROUP_NAME, "dtshyye52491877bfk");
        // topic to consume, partition is 0
        properties.setProperty(KAFKA_TOPIC, "cn_hangzhou_rm_bp11tv2923n87081s_rdsdt_dtsacct");
        // kafka broker url
        properties.setProperty(KAFKA_BROKER_URL_NAME, "dts-cn-hangzhou.aliyuncs.com:18001");
        //properties.setProperty(KAFKA_BROKER_URL_NAME, "120.26.116.93:17011");
        // initial checkpoint for first seek(a timestamp to set, eg 1566180200 if you want (Mon Aug 19 10:03:21 CST 2019))
        properties.setProperty(INITIAL_CHECKPOINT_NAME, "1592272817");
        // if force use config checkpoint when start. for checkpoint reset
        properties.setProperty(USE_CONFIG_CHECKPOINT_NAME, "true");
        // use consumer assign or subscribe interface
        // when use subscribe mode, group config is required. kafka consumer group is enabled
        properties.setProperty(SUBSCRIBE_MODE_NAME, "subscribe");

        properties.setProperty(TRY_TIME, "20");
        properties.setProperty(TRY_BACK_TIME_MS, "10000");

        properties.putIfAbsent("kafka.request.timeout.ms", "20000");

        properties.putIfAbsent("kafka.metadata.max.age.ms", "10000");

        return properties;
    }

    public static void main(String[] args) throws InterruptedException {

        try{
            boot(getConfigs(), buildRecordListener());
        }catch(Throwable e){
            log.error("NotifyDemo: failed cause " + e.getMessage(), e);
            throw e;
        } finally {
            System.exit(0);
        }
    }
}
