package MoEzwawi.factory;

import MoEzwawi.domain.BibliographicItem;
import MoEzwawi.error.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DefaultBibliographicFactoryTest {
    @Test
    public void createsPaperWithCorrectTitle() {
        BibliographicFactory factory = new DefaultBibliographicFactory();
        BibliographicItem paper = factory.create(
                EntryType.PAPER,
                Map.of(
                        "title","A",
                        "author","B",
                        "year","2020",
                        "doi","X",
                        "venue","Y"
        ));
        assertEquals("A", paper.getTitle());
    }

    @Test
    public void throwsOnMissingTitle() {
        BibliographicFactory factory = new DefaultBibliographicFactory();
        assertThrows(InvalidInputException.class,
                () ->
                factory.create(EntryType.BOOK, Map.of("author","A","year","2020"))
        );
    }
    @Test
    public void createsBookWithDefaultYear(){
        BibliographicFactory factory = new DefaultBibliographicFactory();
        BibliographicItem book = factory.create(
                EntryType.BOOK,
                Map.of(
                        "title", "A",
                        "author","B"
                )
        );
        assertEquals(0,book.getYear());
    }
}
