import com.util.HttpUtils;
import org.apache.log4j.Logger;


public class GetDataTest2 {
    private static final Logger logger = Logger.getLogger(GetDataTest2.class);

    public static void main(String[] args){
        String url = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=sh600570&scale=5&ma=5&datalen=1023";
        try {
            String result = HttpUtils.get(url);
            logger.info(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
