package MoEzwawi.app;


import MoEzwawi.domain.*;
import MoEzwawi.error.ShieldedException;
import MoEzwawi.factory.DefaultBibliographicFactory;
import MoEzwawi.factory.EntryType;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            LibraryService service = new LibraryService(DefaultBibliographicFactory.getFactory());
            Map<String, String> collectionParams = Map.of(
                    "title", "my collection",
                    "author", "me",
                    "year", "2025"
            );
            BibliographicItem myCollection = service.newItem(EntryType.COLLECTION, collectionParams);
            service.addToCollection(myCollection, service.newItem(EntryType.BOOK, Map.of(
                    "title", "my book",
                    "author", "me",
                    "year", "2025"
            )));
            System.out.println(service.listAllItems());
        } catch (ShieldedException ex) {
            System.err.println("❌ " + ex.getUserMessage());
        }
    }
}
