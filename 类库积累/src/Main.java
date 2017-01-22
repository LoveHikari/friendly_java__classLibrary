
import com.gootrip.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String html = HttpUtil.sendGet("http://www.neeu.com/news/fashion/","gbk");
        Document doc = Jsoup.parse(html);

        Elements links = doc.select("div.anewsnotitle");
        for (Element e: links){
            Elements elements = e.select("h3").select("a[href]");
            String se = elements.attr("href");
            int i = 0;
        }
    }
}
