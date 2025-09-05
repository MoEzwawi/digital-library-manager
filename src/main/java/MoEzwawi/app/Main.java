package MoEzwawi.app;


import MoEzwawi.domain.*;
import MoEzwawi.error.ShieldedException;
import MoEzwawi.factory.DefaultBibliographicFactory;
import MoEzwawi.factory.EntryType;
import MoEzwawi.iterator.BibliographicAggregate;
import MoEzwawi.iterator.BibliographicIterator;
import MoEzwawi.iterator.DepthFirstBibliographicIterator;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LibraryService service = new LibraryService(DefaultBibliographicFactory.getFactory());

        System.out.println("\n📚 Welcome to the Digital Library CLI!");
        System.out.println("✨ Powered by Factory, Composite, Iterator and Exception Shielding.\n");

        try {
            // Create some example entries
            BibliographicItem book1 = service.newItem(EntryType.BOOK, Map.of(
                    "title", "Effective Java",
                    "author", "Joshua Bloch",
                    "year", "2018",
                    "isbn", "9780134685991",
                    "pages", "416"
            ));

            BibliographicItem paper1 = service.newItem(EntryType.PAPER, Map.of(
                    "title", "Generics Revisited",
                    "author", "Martin Odersky",
                    "year", "2006",
                    "conference", "OOPSLA"
            ));

            BibliographicItem journal1 = service.newItem(EntryType.JOURNAL, Map.of(
                    "title", "Java Monthly",
                    "author", "Editorial Board",
                    "year", "2023",
                    "issue", "Vol. 19, Issue 4"
            ));

            BibliographicItem collection = service.newItem(EntryType.COLLECTION, Map.of(
                    "title", "Java Archives",
                    "author", "Admin",
                    "year", "2024"
            ));

            service.addToCollection(collection, book1);
            service.addToCollection(collection, paper1);
            service.addToCollection(collection, journal1);

            System.out.println("✅ Sample data loaded!\n");

            while (true) {
                System.out.println("""
                        🔹 What do you want to do?
                        1. List all items
                        2. List all collections
                        3. Traverse collection recursively (Depth-First)
                        4. Exit
                        """);

                System.out.print("👉 Your choice: ");
                String choice = input.nextLine();

                switch (choice) {
                    case "1" -> {
                        System.out.println("\n📄 All Library Items:");
                        for (BibliographicItem item : service.listAllItems()) {
                            System.out.println(" • " + item.summary());
                        }
                    }
                    case "2" -> {
                        System.out.println("\n📦 All Collections:");
                        for (BibliographicItem coll : service.listCollections()) {
                            System.out.println(" • " + coll.summary());
                        }
                    }
                    case "3" -> {
                        System.out.println("\n🧭 Depth-First Traversal of ALL collections:");
                        for (BibliographicItem coll : service.listCollections()) {
                            System.out.println("\n🔸 Root: " + coll.getTitle());
                            if (coll instanceof BibliographicCollection aggregate) {
                                BibliographicIterator it = new DepthFirstBibliographicIterator(aggregate);
                                while (it.hasNext()) {
                                    System.out.println("   → " + it.next().summary());
                                }
                            }
                        }
                    }
                    case "4" -> {
                        System.out.println("👋 Bye! Logs saved to 'library.log'");
                        return;
                    }
                    default -> System.out.println("❌ Invalid choice. Try again.");
                }

                System.out.println(); // spacing
            }

        } catch (ShieldedException ex) {
            System.err.println("❗ " + ex.getUserMessage());
        }
    }
}