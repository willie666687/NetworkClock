package willie.util;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

public class TimeUtils {
    public static Date getTime() {
        String TIME_SERVER = "clock.stdtime.gov.tw";
        NTPUDPClient timeClient = new NTPUDPClient();
        long returnTime;
        try {
            InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
            TimeInfo timeInfo = timeClient.getTime(inetAddress);
            returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Date(returnTime);
    }
}
