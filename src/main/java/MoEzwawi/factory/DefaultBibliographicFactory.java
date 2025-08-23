package MoEzwawi.factory;

import MoEzwawi.domain.*;
import MoEzwawi.error.InvalidInputException;

import java.util.Map;

public class DefaultBibliographicFactory implements BibliographicFactory {
    private static int parseIntNonNegative(String key, String value){
        int n = Integer.parseInt(value);
        if (n < 0) throw new IllegalArgumentException("Value of " + key + " must be non negative.");
        return n;
    }

    private static String required(Map<String, String> map, String key){
        String value = map.get(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing required parameter: " + key);
        }
        return value;
    }

    @Override
    public BibliographicItem create(EntryType type, Map<String, String> params) throws InvalidInputException {
        try {
            final String title = required(params, "title");
            final String author = required(params, "author");
            final int year = parseIntNonNegative("year", params.getOrDefault("year", "0"));

            return switch (type) {
                case BOOK -> new Book(
                        title, author, year,
                        params.getOrDefault("isbn", ""),
                        parseIntNonNegative("pages", params.getOrDefault("pages", "0"))
                );
                case JOURNAL -> new Journal(
                        title, author, year,
                        params.getOrDefault("issue", "")
                );
                case PAPER -> new Paper(
                        title, author, year,
                        params.getOrDefault("doi", ""),
                        params.getOrDefault("venue", "")
                );
            };
        } catch (Exception e) {
            throw new InvalidInputException("Invalid parameters for " + type + ": " + e.getMessage(), e);
        }
    }
}
