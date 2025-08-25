package MoEzwawi.app;


import MoEzwawi.domain.*;
import MoEzwawi.factory.BibliographicFactory;
import MoEzwawi.factory.DefaultBibliographicFactory;
import MoEzwawi.factory.EntryType;
import MoEzwawi.util.Log;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        BibliographicFactory factory = DefaultBibliographicFactory.getFactory();
        BibliographicItem book = factory.create(
                EntryType.BOOK,
                Map.of(
                        "title", "Clean Code",
                        "author", "Robert C. Martin",
                        "year", "2008",
                        "isbn","9780132350884",
                        "pages", "464"
                )
        );
        BibliographicItem journal = factory.create(
                EntryType.JOURNAL,
                Map.of(
                        "title", "ACM Transactions on Software Engineering",
                        "author", "ACM",
                        "year", "2023",
                        "issue", "Vol. 42, No. 3"
                )
        );

        BibliographicItem paper = factory.create(
                EntryType.PAPER,
                Map.of(
                        "title", "Attention Is All You Need",
                        "author", "Vaswani et al.",
                        "year", "2017",
                        "doi", "10.5555/3295222.3295349",
                        "venue", "NeurIPS"
                )
        );
        String bookSummary = book.summary();
        String journalSummary = journal.summary();
        String paperSummary = paper.summary();
        System.out.println("Book summary:");
        System.out.println(bookSummary);
        System.out.println("toString:");
        System.out.println(book);
        System.out.println("Journal summary:");
        System.out.println(journalSummary);
        System.out.println("toString:");
        System.out.println(journal);
        System.out.println("Paper summary:");
        System.out.println(paperSummary);
        System.out.println("toString:");
        System.out.println(paper);

        Log.info("buongiornissimo");
    }
}
