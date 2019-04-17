
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
      System.out.println(System.currentTimeMillis());

//方法 三
        System.out.println(new Date().getTime());

//        String html = HttpUtil.sendGet("http://www.neeu.com/news/fashion/","gbk");
//        Document doc = Jsoup.parse(html);
//
//        Elements links = doc.select("div.anewsnotitle");
//        for (Element e: links){
//            Elements elements = e.select("h3").select("a[href]");
//            String se = elements.attr("href");
//            int i = 0;
        }
    }

