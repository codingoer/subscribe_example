package common;

import boot.RecordPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultRecordListener implements RecordListener {
    private static final Logger log = LoggerFactory.getLogger(DefaultRecordListener.class);

    private String dbType;

    private RecordPrinter recordPrinter;

    public DefaultRecordListener(String dbType) {
        this.dbType = dbType;

        recordPrinter = new RecordPrinter(dbType);
    }

    @Override
    public void consume(UserRecord record) {
        // consume record
        String ret = recordPrinter.recordToString(record.getRecord());
        log.info(ret);
        record.commit(String.valueOf(record.getRecord().getSourceTimestamp()));
    }
}
