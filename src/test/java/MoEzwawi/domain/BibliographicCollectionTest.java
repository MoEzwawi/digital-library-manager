package MoEzwawi.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BibliographicCollectionTest {

    private BibliographicCollection root;
    private Book book1;
    private Book book2;
    private Journal journal;
    private Paper paper;
    private BibliographicCollection subcollection;

    @BeforeEach
    public void setUp() {
        this.root = new BibliographicCollection("Root", "Admin", 2023);

        this.book1 = new Book("Java for Dummies", "Barry Burd", 2006, "123", 100);
        this.journal = new Journal("J. CompSci", "Alice", 2021, "Vol. 1");
        this.paper = new Paper("AI Paper", "Charlie", 2022, "10.9999/demo.paper.01", "Conf AI");
        this.subcollection = new BibliographicCollection("Sub", "Admin", 2024);
        this.book2 = new Book("Python", "Alice", 2021, "456", 80);

        this.subcollection.addItem(book2);
        this.root.addItem(this.book1);
        this.root.addItem(this.journal);
        this.root.addItem(this.paper);
        this.root.addItem(this.subcollection);
    }

    @Test
    public void getItemsAtCurrentLevel_returnsCorrectSize() {
        List<BibliographicItem> topItems = root.getItemsAtCurrentLevel();
        assertEquals(4, topItems.size());
    }

    @Test
    public void getBooksAtCurrentLevel_returnsOnlyBooksAtRoot() {
        List<Book> books = root.getBooksAtCurrentLevel();
        assertEquals(1, books.size());
        assertEquals("Java for Dummies", books.get(0).getTitle());
    }

    @Test
    public void getAllBooks_returnsAllBooksInHierarchy() {
        List<Book> books = root.getAllBooks();
        assertEquals(2, books.size());
    }

    @Test
    public void getAllJournals_returnsAllJournalsInHierarchy() {
        List<Journal> journals = root.getAllJournals();
        assertEquals(1, journals.size());
    }

    @Test
    public void getAllPapers_returnsAllPapersInHierarchy() {
        List<Paper> papers = root.getAllPapers();
        assertEquals(1, papers.size());
    }

    @Test
    public void getDirectSubcollections_returnsOnlyTopLevelSubcollections() {
        List<BibliographicCollection> subs = root.getDirectSubcollections();
        assertEquals(1, subs.size());
        assertEquals("Sub", subs.get(0).getTitle());
    }

    @Test
    public void getAllSubcollections_returnsRecursiveCollections() {
        BibliographicCollection nested = new BibliographicCollection("Nested", "Someone", 2025);
        subcollection.addItem(nested);
        List<BibliographicCollection> all = root.getAllSubcollections();
        assertEquals(2, all.size());
    }

    @Test
    public void getLeaves_returnsAllLeafItems() {
        List<BibliographicItem> leaves = root.getLeaves();
        assertEquals(4, leaves.size()); // book1, journal, paper, book2
    }

    @Test
    public void countLeaves_returnsCorrectCount() {
        assertEquals(4, root.countLeaves());
    }
}

