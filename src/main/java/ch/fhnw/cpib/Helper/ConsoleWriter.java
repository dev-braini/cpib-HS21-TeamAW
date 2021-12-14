package ch.fhnw.cpib.Helper;

public class ConsoleWriter {
    public void write(String title) {
        this.write(title, "");
    }

    public void write(String title, String content) {
        // title padding with spaces
        while (title.length() < 55) title += " ";

        System.out.println("\n\n+----------------------------------------------------------+");
        System.out.println("|  " + title + " |");
        System.out.println("+----------------------------------------------------------+");
        if(content != "") System.out.println(content.trim());
    }
}
