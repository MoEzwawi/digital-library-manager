package MoEzwawi.app;


import MoEzwawi.domain.*;
import MoEzwawi.error.ShieldedException;
import MoEzwawi.factory.DefaultBibliographicFactory;
import MoEzwawi.factory.EntryType;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryService service = new LibraryService(DefaultBibliographicFactory.getFactory());
        Scanner scanner = new Scanner(System.in);

        System.out.println("📚 Welcome to the Digital Library CLI");

        System.out.print("→ Title: ");
        String title = scanner.nextLine();

        System.out.print("→ Author: ");
        String author = scanner.nextLine();

        System.out.print("→ Year: ");
        String year = scanner.nextLine();

        try {
            BibliographicItem item = service.addItem(
                    EntryType.BOOK,
                    Map.of("title", title, "author", author, "year", year)
            );
            System.out.println("✅ Successfully added: " + item.summary());
        } catch (ShieldedException ex) {
            System.out.println("❌ Error: " + ex.getUserMessage());
        }
    }
}
