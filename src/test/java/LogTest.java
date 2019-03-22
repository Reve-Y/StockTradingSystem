import org.apache.log4j.Logger;

public class LogTest {
    private static Logger log = Logger.getLogger(LogTest.class);

    public static void main(String[] args) {
        log.info("乱码？？？");
        log.info("this is info message");
        log.debug("this is debug message");
        log.warn("this is warn message");
        log.error("this is error message");
    }
}
