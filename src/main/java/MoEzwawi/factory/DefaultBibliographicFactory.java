package MoEzwawi.factory;

import MoEzwawi.domain.*;
import MoEzwawi.error.InvalidInputException;

import java.util.Map;

/**
 * Default implementation of {@link BibliographicFactory}.
 * Creates instances of {@link Book}, {@link Journal} or {@link Paper} from a parameter map.
 */
public class DefaultBibliographicFactory implements BibliographicFactory {
    /**
     * Private constructor to enforce Singleton pattern.
     * Prevents instantiation from outside the class.
     */
    private DefaultBibliographicFactory(){}
    /**
     * Holder class for the singleton instance.
     * This leverages the Bill Pugh Singleton pattern to ensure
     * thread-safe, lazy initialization of the singleton instance.
     */
    private static class SingletonHolder {
        private static final DefaultBibliographicFactory INSTANCE = new DefaultBibliographicFactory();
    }
    /**
     * Returns the singleton instance of {@link DefaultBibliographicFactory}.
     * <p>
     * This method provides access to the single instance, guaranteeing
     * lazy initialization and thread safety without synchronization overhead.
     *
     * @return the singleton {@link DefaultBibliographicFactory} instance.
     */
    public static DefaultBibliographicFactory getInstance(){
        return SingletonHolder.INSTANCE;
    }
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

    /**
     * Implementation of the create method declared in the {@link BibliographicFactory} interface.
     * Creates a bibliographic item of the given type using the provided parameters.
     * @param type the type of bibliographic item requested.
     * @param params a map of parameters (title and author are required!)
     * @return a concrete instance of the {@link BibliographicItem} abstract class,
     * such as {@link Book}, {@link Journal} or {@link Paper}, depending on the
     * @throws InvalidInputException for invalid params (e.g., missing title, negative year).
     */
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
