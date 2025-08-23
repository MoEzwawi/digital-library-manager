package MoEzwawi.factory;

import MoEzwawi.domain.BibliographicItem;
import MoEzwawi.error.InvalidInputException;

import java.util.Map;

public interface BibliographicFactory {
    public BibliographicItem create(EntryType type, Map<String, String> params) throws InvalidInputException;
}
