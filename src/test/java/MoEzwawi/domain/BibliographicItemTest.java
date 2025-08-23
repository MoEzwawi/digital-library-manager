package MoEzwawi.domain;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BibliographicItemTest {
    @Test
    public void bookHasCorrectFieldsAndSummary() {
        Book book = new Book("Clean Code", "Robert C. Martin", 2008, "9780132350884", 464);

        assertEquals("Clean Code", book.getTitle());
        assertEquals("Robert C. Martin", book.getAuthor());
        assertEquals(2008, book.getYear());
        assertEquals("9780132350884", book.getIsbn());
        assertEquals(464, book.getPages());
        assertTrue(book.summary().contains("Clean Code"));
    }

    @Test
    public void journalHasCorrectFieldsAndSummary() {
        Journal journal = new Journal("ACM Transactions on Software Engineering",
                "ACM", 2023, "Vol. 42, No. 3");

        assertEquals("ACM Transactions on Software Engineering", journal.getTitle());
        assertEquals("ACM", journal.getAuthor());
        assertEquals(2023, journal.getYear());
        assertEquals("Vol. 42, No. 3", journal.getIssue());
        assertTrue(journal.summary().contains("Journal: ACM Transactions"));
    }

    @Test
    public void paperHasCorrectFieldsAndSummary() {
        Paper paper = new Paper("Attention Is All You Need", "Vaswani et al.", 2017,
                "10.5555/3295222.3295349", "NeurIPS");

        assertEquals("Attention Is All You Need", paper.getTitle());
        assertEquals("Vaswani et al.", paper.getAuthor());
        assertEquals(2017, paper.getYear());
        assertEquals("10.5555/3295222.3295349", paper.getDoi());
        assertEquals("NeurIPS", paper.getVenue());
        assertTrue(paper.summary().contains("Paper @ NeurIPS"));
    }
}
