package recordgenerator;

/**
 * DTS中的一些配置常量
 */
public class Names {
    // detail control
    public static final String TRY_TIME = "stream.tryTime";
    public static final String TRY_BACK_TIME_MS = "stream.tryBackTimeMS";
    public static final String RETRY_TIME_OUT = "stream.errorRetryTimeOut";
    public static final String POLL_TIME_OUT = "stream.pool.timeout";
    // general name

    /**
     * Kafka订阅Topic，数据订阅通道的订阅topic
     * topic to consume, partition is 0
     */
    public static final String KAFKA_TOPIC = "kafkaTopic";

    /**
     * Kafka Broker Url，数据通道的网络地址及端口号信息
     */
    public static final String KAFKA_BROKER_URL_NAME = "broker";

    /**
     * 消费组名称，与消费组ID一致
     * kafka consumer group general same with sid
     */
    public static final String GROUP_NAME = "group";

    /**
     * 使用指定的数据时间点来消费数据
     * if force use config checkpoint when start. for checkpoint reset
     */
    public static final String USE_CONFIG_CHECKPOINT_NAME = "useConfigCheckpoint";

    /**
     * 是否是订阅
     * use consumer assign or subscribe interface。when use subscribe mode, group config is required. kafka consumer group is enabled
     */
    public static final String SUBSCRIBE_MODE_NAME = "subscribeMode";

    /**
     * 消费的数据时间点，时间戳。
     * initial checkpoint for first seek(a timestamp to set, eg 1566180200 if you want (Mon Aug 19 10:03:21 CST 2019))
     */
    public static final String INITIAL_CHECKPOINT_NAME = "checkpoint";

    /**
     * 消费组的账号
     * user password and sid for auth
     */
    public static final String  USER_NAME = "user";

    /**
     * 消费组的密码
     */
    public static final String  PASSWORD_NAME = "password";

    /**
     * 消费组ID
     */
    public static final String  SID_NAME = "sid";

    public static final long MAX_TIMESTAMP_SECOND = 99999999999L;
}
