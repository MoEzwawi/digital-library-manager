package MoEzwawi.domain;

import MoEzwawi.iterator.BibliographicAggregate;
import MoEzwawi.iterator.BibliographicIterator;
import MoEzwawi.iterator.SimpleBibliographicIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The BibliographicCollection class represents a collection of bibliographic items.
 * <p>
 * This class serves as the Composite in the Composite design pattern, allowing
 * multiple {@link BibliographicItem} instances to be managed as a single entity.
 * It also provides an iterator for traversing all items of the collection.
 * </p>
 * <p>
 * Attributes:
 * </p>
 * <ul>
 *   <li>items: A list of bibliographic items contained in this collection.</li>
 * </ul>
 * <p>
 * Constructors:
 * </p>
 * <ul>
 *   <li>{@link #BibliographicCollection(String, String, int)}: Constructs a collection
 *       with a title, author, and year.</li>
 * </ul>
 * <p>
 * Methods:
 * </p>
 * <ul>
 *   <li>{@link #addItem(BibliographicItem)}: Adds a bibliographic item to the collection.</li>
 *   <li>{@link #getItemsAtCurrentLevel()}: Returns an unmodifiable view of the items in the collection.</li>
 *   <li>{@link #getBooksAtCurrentLevel()}: Returns a list of {@link Book} instances directly contained in this collection.</li>
 *   <li>{@link #getPapersAtCurrentLevel()}: Returns a list of {@link Journal} instances directly contained in this collection.</li>
 *   <li>{@link #getBooksAtCurrentLevel()}: Returns a list of {@link Paper} instances directly contained in this collection.</li>
 *   <li>{@link #getDirectSubcollections()}: Returns a list of {@link BibliographicCollection} instances directly contained in this collection.</li>
 *   <li>{@link #getSize()}: Returns the number of the items of the collection..</li>
 *   <li>{@link #getItemAt(int)}: Returns a bibliographic item at a specified index.</li>
 *   <li>{@link #getLeaves()}: Returns a list of all leaf items contained in this collection.</li>
 *   <li>{@link #getAllBooks()}: Returns a list of all {@link Book} instances contained in this collection and any nested sub-collections.</li>
 *   <li>{@link #getAllJournals()}: Returns a list of all {@link Journal} instances contained in this collection and any nested sub-collections.</li>
 *   <li>{@link #getAllPapers()}: Returns a list of all {@link Paper} instances contained in this collection and any nested sub-collections.</li>
 *   <li>{@link #getAllSubcollections()}: Returns a list of all {@link BibliographicCollection} instances contained in this collection and any nested sub-collections.</li>
 *   <li>{@link #countLeaves()}: Returns the number of leaf items in this collection.</li>
 *   <li>{@link #summary()}: Returns a formatted summary string for the collection.</li>
 *   <li>{@link #iterator()}: Returns an iterator to traverse all items of the collection.</li>
 * </ul>
 */
public class BibliographicCollection extends BibliographicItem implements BibliographicAggregate {
    /**
     * A list of bibliographic items contained in this collection.
     */
    private final List<BibliographicItem> items = new ArrayList<>();

    /**
     * Constructs a new BibliographicCollection instance.
     *
     * @param title  The title of the collection.
     * @param author The creator of the collection.
     * @param year   The creation year of the collection.
     */
    public BibliographicCollection(String title, String author, int year) {
        super(title, author, year);
    }
    /**
     * Adds a bibliographic item to this collection.
     *
     * @param item The {@link BibliographicItem} to add.
     */
    public void addItem(BibliographicItem item){
        this.items.add(item);
    }
    /**
     * Returns an unmodifiable view of the items in the collection.
     *
     * @return List of {@link BibliographicItem} contained in this collection.
     */
    public List<BibliographicItem> getItemsAtCurrentLevel() {
        return List.copyOf(this.items);
    }

    /**
     * Retrieves all {@link Book} instances directly contained in this collection.
     * <p>
     * This method operates on the current level of the composite structure only,
     * without recursively exploring nested {@link BibliographicCollection}s.
     * </p>
     *
     * @return a list of {@link Book} items at the top level of this collection
     */
    public List<Book> getBooksAtCurrentLevel() {
        return this.items.stream()
                .filter(Book.class::isInstance)
                .map(Book.class::cast)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all {@link Journal} instances directly contained in this collection.
     * <p>
     * This method inspects only the immediate children of the collection and does not
     * traverse nested {@link BibliographicCollection}s.
     * </p>
     *
     * @return a list of {@link Journal} items at the top level of this collection
     */
    public List<Journal> getJournalsAtCurrentLevel() {
        return this.items.stream()
                .filter(Journal.class::isInstance)
                .map(Journal.class::cast)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all {@link Paper} instances directly contained in this collection.
     * <p>
     * The method filters only the items at the current level of this collection
     * and does not enter nested {@link BibliographicCollection}s.
     * </p>
     *
     * @return a list of {@link Paper} items at the top level of this collection
     */
    public List<Paper> getPapersAtCurrentLevel() {
        return this.items.stream()
                .filter(Paper.class::isInstance)
                .map(Paper.class::cast)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all {@link BibliographicCollection} instances directly contained in this collection.
     * <p>
     * This method filters the immediate children of this collection and returns only those
     * that are themselves {@link BibliographicCollection}s.
     * </p>
     *
     * @return a list of immediate {@link BibliographicCollection} instances at the top level
     */
    public List<BibliographicCollection> getDirectSubcollections() {
        return this.items.stream()
                .filter(BibliographicCollection.class::isInstance)
                .map(BibliographicCollection.class::cast)
                .collect(Collectors.toList());
    }


    /**
     * Returns the number of direct children of the collection.
     *
     * @return the size of the collection.
     */
    @Override
    public int getSize(){ return this.items.size(); }

    /**
     * Returns a bibliographic item at a specified index.
     *
     * @return a {@link BibliographicItem} at a specified index.
     */
    @Override
    public BibliographicItem getItemAt(int index){
        return this.items.get(index);
    };

    /**
     * Retrieves a list of all leaf items contained in this collection.
     * <p>
     * A leaf item is any {@link BibliographicItem} that is not itself a
     * {@link BibliographicCollection}, such as {@link Book}, {@link Journal} or {@link Paper}.
     * The list is constructed recursively, traversing all nested collections.
     * </p>
     *
     * @return a list of all leaf items contained in this collection
     */
    public List<BibliographicItem> getLeaves() {
        List<BibliographicItem> leaves = new ArrayList<>();
        for (BibliographicItem item : this.items) {
            if (item instanceof BibliographicCollection collection) {
                leaves.addAll(collection.getLeaves());
            } else {
                leaves.add(item);
            }
        }
        return leaves;
    }

    /**
     * Retrieves all {@link Book} instances contained in this collection and any nested sub-collections.
     * <p>
     * This method performs a recursive traversal using {@link #getLeaves()} to gather all leaf elements,
     * then filters only those of type {@link Book}.
     * </p>
     *
     * @return a list of all {@link Book} items in the entire hierarchy
     */
    public List<Book> getAllBooks() {
        return getLeaves().stream()
                .filter(Book.class::isInstance)
                .map(Book.class::cast)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all {@link Journal} instances contained in this collection and any nested sub-collections.
     * <p>
     * This method performs a recursive traversal using {@link #getLeaves()} to gather all leaf elements,
     * then filters only those of type {@link Journal}.
     * </p>
     *
     * @return a list of all {@link Journal} items in the entire hierarchy
     */
    public List<Journal> getAllJournals() {
        return getLeaves().stream()
                .filter(Journal.class::isInstance)
                .map(Journal.class::cast)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all {@link Paper} instances contained in this collection and any nested sub-collections.
     * <p>
     * This method performs a recursive traversal using {@link #getLeaves()} to gather all leaf elements,
     * then filters only those of type {@link Paper}.
     * </p>
     *
     * @return a list of all {@link Paper} items in the entire hierarchy
     */
    public List<Paper> getAllPapers() {
        return getLeaves().stream()
                .filter(Paper.class::isInstance)
                .map(Paper.class::cast)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all nested {@link BibliographicCollection} instances contained within this collection.
     * <p>
     * This method recursively explores each child item. If the item is a {@link BibliographicCollection},
     * it is added to the result and the method continues traversal within it.
     * </p>
     *
     * @return a list of all sub-collections.
     */
    public List<BibliographicCollection> getAllSubcollections() {
        List<BibliographicCollection> subcollections = new ArrayList<>();
        for (BibliographicItem item : this.items) {
            if (item instanceof BibliographicCollection collection) {
                subcollections.add(collection);
                subcollections.addAll(collection.getAllSubcollections());
            }
        }
        return subcollections;
    }



    /**
     * Counts the number of leaf items in this collection.
     * <p>
     * A leaf item is any {@link BibliographicItem} that is not itself a
     * {@link BibliographicCollection}, such as {@link Book}, {@link Journal} or {@link Paper}.
     * The count is computed recursively, traversing all nested collections.
     * </p>
     *
     * @return the total number of leaf items contained in this collection
     */
    public int countLeaves(){
        int count = 0;
        for(BibliographicItem item : this.items){
            if(item instanceof BibliographicCollection collection){
                count += collection.countLeaves();
            } else {
                count++;
            }
        }
        return count;
    }

    /**
     * Provides a formatted summary of the collection.
     *
     * @return string in the form "Collection: Title by Author (Year), N item(s)".
     */

    @Override
    public String summary() {
        return "Collection: " + super.getTitle() + " by " + super.getAuthor() + " (" + super.getYear() + "), "
                + items.size() + " item(s)";
    }

    /**
     * Creates and returns an iterator to traverse all items in this collection.
     *
     * @return a {@link BibliographicIterator} for this aggregate.
     */
    @Override
    public BibliographicIterator iterator() {
        return new SimpleBibliographicIterator(this);
    }
}
