package common;

import org.apache.kafka.common.TopicPartition;

/**
 * 消费点位
 */
public class Checkpoint {

    public static final Checkpoint INVALID_STREAM_CHECKPOINT = new Checkpoint(null, -1, -1, "-1");

    /**
     * kafka消息分区
     */
    private final TopicPartition topicPartition;

    /**
     * 时间戳
     */
    private final long timeStamp;

    /**
     * 偏移量
     */
    private final long offset;

    /**
     * 备注
     */
    private final String info;

    public Checkpoint(TopicPartition topicPartition, long timeStamp, long offset, String info) {
        this.topicPartition = topicPartition;
        this.timeStamp = timeStamp;
        this.offset = offset;
        this.info = info;
    }

    public long getOffset() {
        return offset;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getInfo() {
        return info;
    }

    public TopicPartition getTopicPartition() {
        return topicPartition;
    }

    public String toString() {
        return "Checkpoint[ topicPartition: " + topicPartition + "timestamp: " + timeStamp + ", offset: " + offset + ", info: "  + info + "]";
    }
}
