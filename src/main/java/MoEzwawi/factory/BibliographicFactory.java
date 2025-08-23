package MoEzwawi.factory;

import MoEzwawi.domain.BibliographicItem;
import MoEzwawi.error.InvalidInputException;

import java.util.Map;
/**
 * Factory interface responsible for creating concrete bibliographic items
 * (e.g., Book, Journal, Paper) from a parameter map.
 */
public interface BibliographicFactory {
    public BibliographicItem create(EntryType type, Map<String, String> params) throws InvalidInputException;
}
