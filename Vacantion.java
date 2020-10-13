public class Vacantion {

    private static String text;
    private static String urla;
    private static String stroka_text;

    public Vacantion(String text, String urla, String stroka_text) {
        if (text == null || urla == null){
            throw new IllegalArgumentException();
        }
        this.text = text;
        this.urla = urla;
        this.stroka_text = stroka_text;

    }


    public static String getText () {
        return text;
    }

    public static String getUrla () {
        return urla;
    }

    public static String getStroka_text () {
        return stroka_text;
    }

    @Override
    public String toString () {
        String ls = System.lineSeparator();
        final StringBuilder sb = new StringBuilder();
        sb.append(ls).append("Вакансия, значит, такая:").append(String.format("\"%s\"",text));
        sb.append(ls).append("Находится, значит, по адресу:").append(String.format("\"%s\"",urla));
        sb.append(ls).append("Звучит, значит, так:").append(String.format("\"%s\"",stroka_text));
        return sb.toString();
    }

    @Override
    public boolean equals (Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode () {
        return super.hashCode();
    }
}