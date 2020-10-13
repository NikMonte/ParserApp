import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
//import static jdk.nashorn.internal.runtime.ScriptObject.isArray;
//import static jdk.nashorn.internal.runtime.ScriptObject.setGlobalObjectProto;


public class Main extends ParserSqlRu {

    private static final String URL_SQL_RU = "https://www.sql.ru/forum/job-offers/";

    private int stroka;

    public int getStroka () {
        return stroka;
    }

    public void setStroka (int stroka) {

        this.stroka = stroka;
    }


    public static <charset> void main (String[] args) throws IOException {

        Document doc = Jsoup.connect(URL_SQL_RU).get(); // получение html-страницы от сервера
        Charset charset = Charset.defaultCharset();
        doc.charset(charset); // конвертация в дефолтный charset
        Elements elements = doc.getElementsByTag("a"); // получение элементов с тэгом
        String vc = vac(); //получаем название вакансии
        Set<String> vacantionSet = new TreeSet<>(String::compareTo); //создаем базу для вакансий
        for (int i = 0; i < elements.size(); i++) { //пробегаем по объявлениям
            String vacantion = elements.get(i).text();
            if (vacantion.contains(vc)) { //если в объявлении есть вакансия
               vacantionSet.add(i + vacantion); //добавляем в базу
            } else {
                //System.out.println("в строке " + i + " слово не найдено " + elements.get(i));
         }
        }
        for (String temp : vacantionSet) {
            System.out.println(temp); //печатаем базу
        }


        // второй способ извлечь данные
        String url = URL_SQL_RU;
        print("Fetching %s...", url);

        Document doc2 = Jsoup.connect(url).get();
        Elements links = doc2.select("a[href]"); //собираем элементы страницы по тегу
        Elements dates = doc2.select("[altCol]");//попытка извлечь дату


        print("\nDate: (%d)", dates.size());//этот блок еще не работает
        for (Element link : dates) {
            print("* %s <%s> (%s)", link.className(),link.attr("class"));
        }



        print("\nLinks: (%d)", links.size());
        String vc2 = vac(); //повторно запрашиваем название вакансии
        for (Element link : links) { //пробегаем по элементам базы
            charset trvac = (charset) trim(link.text(), 135); //собираем текст в массив
            String vc3 = trvac.toString(); //превращаем массив в строку
            if (vc3.contains(vc2)) { //проверяем наличие вакансии в строке
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 135));
            //выводим строку с вакансией
        } else {

        }

        }}



    private static void print(String msg, Object... args) { //метод для печати
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) { //метод для сбора текста
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }

    private static String vac () { //метод для ввода названия вакансии
        System.out.println("Введите название вакансии: ");
       Scanner scanner = new Scanner(System.in);
        String vac = scanner.next();
        return vac;

}}
//туплю на этапе получения даты объявления
//не понимаю, зачем так сложно сделали шаблон для парсера, я реально не могу к нему подступиться,
//не понимаю многих действий, для которых он сделан - топики, как их выводить? как влепить поиск по дате?
//как парсить дату???
//в общем, завис... 