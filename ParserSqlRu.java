import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ParserSqlRu {
    private static final String URL_SQL_RU = "https://www.sql.ru/forum/job-offers/";
    private final static String[] MONTHS =
            {"янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"};

    /**
     * Получаем список топиков из таблицы.
     *
     * @param forum можно получить через parser.urlToDoc(URL_SQL_RU).
     */
    public void parseTable(Document forum) {
        Elements table = forum.getElementsByClass("forumTable");
        for (Element tr : table.select("tr")) {
            String title = "";
            String url = "";
            String date = "";
            int count = 1;
            for (Element td : tr.select("td")) {
                if (count == 2) {
                    title = td.select("a[href]").first().text();
                    url = td.select("a[href]").first().attr("href");
                } else if (count == 6) {
                    date = td.text().split(",")[0];
                }
                count++;
            }
            //List<parseTable> result = new ArrayList<>();
            // Здесь из title + url + date Создавайте объект топика
        }
    }

    /**
     * В топике получаем сообщение.
     *
     * @param topic У топика есть url, мы можем parser.urlToDoc(url)
     */
    public void parseOnMassage(Document topic) {
        Elements msgBody = topic.getElementsByClass("msgBody");
        // Получаете нужную td и из неё TextNodes, из них делаете строку.
    }

    /**
     * Метод необязательный, может быть полезен для итерации по страницам форума.
     *
     * @param index номер страницы.
     * @return индекс 0 или 1 вернёт url на первую страницу, а в остальных случаях просто
     *          добавляет в конце url цифру что даёт валидный url если страница с таким
     *          номером и вправду есть.
     */
    public String getUrlPage(int index) {
        return index == 1 || index == 0 ?
                ParserSqlRu.URL_SQL_RU : ParserSqlRu.URL_SQL_RU + Math.abs(index);
    }

    /**
     * Процесс получения Document объекта Jsoup из url.
     */
    public Document urlToDoc(String url) {
        Document forum = null;
        try {
            forum = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return forum;
    }

    /**
     * Процесс парсинга даты полученного с форума в дату.
     */
    private Date toDate(String date) {
        Date result = null;
        if (date.contains("сегодня")) {
            // Создаём сегодняшнюю дату
        } else if (date.contains("вчера")) {
            // Создаём вчерашнюю дату
        } else {
            // из строки парсим дату
        }
        return result;
    }
}
