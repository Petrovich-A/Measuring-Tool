package by.petrovich.tool.util;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.hibernate.engine.jdbc.internal.Formatter;

public class SpyCustomFormatter implements MessageFormattingStrategy {
    private static final Formatter HIBERNATE_SQL_FORMATTER = new BasicFormatterImpl();

    private static final String FORMAT = "Category: %s\n ElapsedTime: %s\n Hibernate: %s\n";

    @Override
    public String formatMessage(int connectionID, String now, long elapsed, String category, String prepared,
                                String sql, String url) {
        if (sql.isEmpty())
            return "";
        return String.format(FORMAT, category, elapsed, HIBERNATE_SQL_FORMATTER.format(sql));
    }

}
