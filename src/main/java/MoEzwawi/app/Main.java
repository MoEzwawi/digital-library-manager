package MoEzwawi.app;


import MoEzwawi.domain.*;

public class Main {
    public static void main(String[] args) {
        Book book = new Book("Clean Code", "Robert C. Martin", 2008, "9780132350884", 464);
        Journal journal = new Journal("ACM Transactions on Software Engineering",
                "ACM", 2023, "Vol. 42, No. 3");

        Paper paper = new Paper("Attention Is All You Need", "Vaswani et al.", 2017,
                "10.5555/3295222.3295349", "NeurIPS");
        String bookSummary = book.summary();
        String journalSummary = journal.summary();
        String paperSummary = paper.summary();
        System.out.println("Book summary:");
        System.out.println(bookSummary);
        System.out.println("Journal summary:");
        System.out.println(journalSummary);
        System.out.println("Paper summary:");
        System.out.println(paperSummary);
    }
}
