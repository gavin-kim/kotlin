package timePeriod;

import java.io.Serializable;
import java.util.Date;

public interface TimePeriod extends Serializable {
    Date getStartDate();
    Date getEndDate();
}
