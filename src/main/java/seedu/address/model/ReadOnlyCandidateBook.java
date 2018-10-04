package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.candidate.Candidate;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyCandidateBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Candidate> getCandidatelist();

}