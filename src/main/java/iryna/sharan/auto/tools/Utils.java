package iryna.sharan.auto.tools;

import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Utils {
    public static LocalDateTime dateToLocalDate(@Nullable Date date) {
        return date == null ? null : new java.sql.Timestamp(date.getTime()).toLocalDateTime();
    }
}
