import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Crawler {
    public static void main(String[] args) {
        String url = "https://fa.wikipedia.org/wiki/";
        crawl(1, url, new ArrayList<String>());

    }

            ArrayList<String>list=new ArrayList<>();


            public static void crawl(int level, String url, ArrayList<String> visited){
                if(level<5){
                    Document doc=requst(url,visited);
                    if (doc != null) {
                        for (Element link : doc.select("a[href]")) {

                            String nextLink = link.absUrl("href");
                            // Corrected from "herf" to "href"
                            if (!visited.contains(nextLink)) {  // More concise way to check
                                visited.add(nextLink);  // Mark the link as visited
                                crawl(level + 1, nextLink, visited);  // Increment the level correctly
                            }
                        }
                    }
                }
            }
            private static Document requst(String url, ArrayList<String>v) {
                try {
                    Connection con = Jsoup.connect(url);
                    Document doc = (Document) con.get();
                    if (con.response().statusCode() == 200) {

                        System.out.println("Link " + url);
                        System.out.println(doc.title());
                        v.add(url);
                        return doc;
                    }
                    return null;
                } catch (IOException e) {
                    return null;
                }

            }
}

