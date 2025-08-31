package MoEzwawi.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BibliographicCollectionTest {

    @Test
    public void addItem_shouldStoreItem() {
        BibliographicCollection collection = new BibliographicCollection("My Collection", "Curator", 2024);
        Book book = new Book("Java for Dummies", "Barry Burd", 2006, "1234567890", 464);

        collection.addItem(book);

        List<BibliographicItem> items = collection.getItems();
        assertEquals(1, items.size());
        assertTrue(items.contains(book));
    }

    @Test
    public void getItems_shouldReturnUnmodifiableList() {
        BibliographicCollection collection = new BibliographicCollection("My Collection", "Curator", 2024);
        Book book = new Book("Java for Dummies", "Barry Burd", 2006, "1234567890", 464);

        collection.addItem(book);

        List<BibliographicItem> items = collection.getItems();
        assertThrows(RuntimeException.class, () -> items.add(book));
    }

    @Test
    public void getLeaves_shouldReturnLeafItemsOnly() {
        BibliographicCollection root = new BibliographicCollection("Root", "R", 2024);
        BibliographicCollection nested = new BibliographicCollection("Nested", "N", 2024);
        Paper paper = new Paper("AI Study", "Alice", 2023, "10.9999/demo.paper.01", "MLConf");
        Journal journal = new Journal("J. Comp", "Bob", 2022, "Vol. 1");

        nested.addItem(paper);
        root.addItem(journal);
        root.addItem(nested);

        List<BibliographicItem> leaves = root.getLeaves();
        assertEquals(2, leaves.size());
        assertTrue(leaves.contains(paper));
        assertTrue(leaves.contains(journal));
    }

    @Test
    public void countLeaves_shouldReturnCorrectLeafCount() {
        BibliographicCollection root = new BibliographicCollection("Root", "R", 2024);
        BibliographicCollection nested = new BibliographicCollection("Nested", "N", 2024);
        Paper paper = new Paper("AI Study", "Alice", 2023, "10.9999/demo.paper.01", "MLConf");
        Journal journal = new Journal("J. Comp", "Bob", 2022, "Vol. 1");

        nested.addItem(paper);
        root.addItem(journal);
        root.addItem(nested);
        Book book = new Book("Java for Dummies", "Barry Burd", 2006, "1234567890", 464);
        root.addItem(book);

        assertEquals(3, root.countLeaves());
    }
}
