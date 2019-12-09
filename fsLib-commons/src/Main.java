
import com.gootrip.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException {
        //方法 一
     // System.out.println(System.currentTimeMillis());

//方法 三
        //System.out.println(new Date().getTime());

//        String html = HttpUtil.sendGet("http://www.neeu.com/news/fashion/","gbk");
//        Document doc = Jsoup.parse(html);
//
//        Elements links = doc.select("div.anewsnotitle");
//        for (Element e: links){
//            Elements elements = e.select("h3").select("a[href]");
//            String se = elements.attr("href");
//            int i = 0;
        System.out.println("args = [" + args + "]");
        jsonParam.put("pay_ver","100");
        jsonParam.put("pay_type","020");
        jsonParam.put("service_id","012");
        jsonParam.put("merchant_no",merchant_no);
        jsonParam.put("terminal_id",terminal_id);
        jsonParam.put("terminal_time",terminal_time);
        jsonParam.put("total_fee",total_fee);
        jsonParam.put("order_body","");
        jsonParam.put("open_id",open_id);
        jsonParam.put("terminal_trace",times);
        jsonParam.put("sub_appid","");

        }
    }

