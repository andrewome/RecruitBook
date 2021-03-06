package seedu.recruit.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.recruit.commons.util.EmailUtil;
import seedu.recruit.logic.parser.Prefix;
import seedu.recruit.model.candidate.Candidate;
import seedu.recruit.model.company.Company;
import seedu.recruit.model.company.CompanyName;
import seedu.recruit.model.joboffer.JobOffer;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Candidate> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Company> PREDICATE_SHOW_ALL_COMPANIES = unused -> true;
    Predicate<JobOffer> PREDICATE_SHOW_ALL_JOBOFFERS = unused -> true;
    Predicate<Candidate> PREDICATE_HIDE_ALL_PERSONS = unused -> false;
    Predicate<Company> PREDICATE_HIDE_ALL_COMPANIES = unused -> false;
    Predicate<JobOffer> PREDICATE_HIDE_ALL_JOBOFFERS = unused -> false;

    // ================================== RecruitBook-level functions ===================================== //

    boolean canUndoRecruitBook();

    boolean canRedoRecruitBook();

    void commitRecruitBook();

    void undoRecruitBook();

    void redoRecruitBook();



    // ================================== CandidateBook functions ====================================== //
    /** Clears existing backing model and replaces with the provided new data. */
    void resetCandidateData(ReadOnlyCandidateBook newData);

    /** Returns the CandidateBook */
    ReadOnlyCandidateBook getCandidateBook();

    /**
     * Returns true if a candidate with the same identity as {@code candidate} exists in the recruit book.
     */
    boolean hasCandidate(Candidate candidate);

    /**
     * Deletes the given candidate.
     * The candidate must exist in the candidate book.
     */
    void deleteCandidate(Candidate target);

    /**
     * Adds the given candidate.
     * {@code candidate} must not already exist in the recruit book.
     */
    void addCandidate(Candidate candidate);

    /**
     * Sorts the candidates in CandidateBook
     */
    void sortCandidates(Prefix prefix);

    /**
     * Replaces the given candidate {@code target} with {@code editedCandidate}.
     * {@code target} must exist in the CandidateBook.
     * The candidate identity of {@code editedCandidate} must not be the same as another existing candidate in the
     * recruit book.
     */
    void updateCandidate(Candidate target, Candidate editedCandidate);

    /**
     * Returns an unmodifiable view of the master candidate list
     */
    ObservableList<Candidate> getMasterCandidateList();

    /**
     * Returns an unmodifiable view of the filtered candidate list.
     * */
    ObservableList<Candidate> getFilteredCandidateList();

    /**
     * Updates the filter of the filtered candidate list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCandidateList(Predicate<Candidate> predicate);



    // ================================== CompanyBook functions ===================================== //

    /** Clears existing backing model and replaces with the provided new data. */
    void resetCompanyData(ReadOnlyCompanyBook newData);

    /** Returns the CompanyBook */
    ReadOnlyCompanyBook getCompanyBook();

    /**
     * Returns true if a company with the same identity as {@code company} exists in the CompanyBook.
     */
    boolean hasCompany(Company company);

    /**
     * Deletes the given company.
     * The company must exist in the CompanyBook.
     */
    void deleteCompany(Company target);

    /**
     * Adds the given company.
     * {@code company} must not already exist in the CompanyBook.
     */
    void addCompany(Company company);

    /**
     * Sorts the list of companies in CompanyBook
     */
    void sortCompanies(Prefix prefix);

    /**
     * Replaces the given company {@code target} with {@code editedCompany}.
     * {@code target} must exist in the CompanyBook.
     * The company identity of {@code editedCompany} must not be the same as another existing company in the
     * CompanyBook.
     */
    void updateCompany(Company target, Company editedCompany);

    /** Cascade company name changes to job offers
     */
    void cascadeToJobOffers(CompanyName targetName, CompanyName editedName);

    /** Returns an unmodifiable view of the filtered company list */
    ObservableList<Company> getFilteredCompanyList();

    /** Returns index of Company using @param companyName
     *  companyName is enforced to be unique in CompanyBook
     */
    int getCompanyIndexFromName(CompanyName companyName);

    /** Returns the Company object based on @param index
     */
    public Company getCompanyFromIndex(int index);

    /**
     * Updates the filter of the filtered company list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCompanyList(Predicate<Company> predicate);



    // ================================== Job Offer functions ===================================== //

    /**
     * Adds a given job offer to the specified company name
     * @code companyName has to exist in the CompanyBook
     * @code jobOffer must not already exist inside the job list of companyName
     */
    void addJobOffer(JobOffer jobOffer);

    /**
     * Sorts the list of job offers in CompanyBook
     */
    void sortJobOffers(Prefix prefix);

    /**
     * Returns true if a company has a job offer with the same identity as {@code jobOffer} exists in the CompanyBook.
     */
    boolean hasJobOffer(JobOffer jobOffer);

    /**
     * Replaces the given job offer {@code target} in the list with {@code editedJobOffer}.
     * {@code target} must exist in the company book.
     * The job offer identity of {@code editedJobOffer} must not be the same as another existing job offer in the
     * company book.
     */
    void updateJobOfferInCompanyBook(JobOffer target, JobOffer editedJobOffer);

    /**
     * Deletes the given job offer.
     * The job offer must exist in the CompanyBook.
     */
    void deleteJobOffer(JobOffer target);

    /**
     * Returns an unmodifiable view of the master job list
     */
    ObservableList<JobOffer> getMasterJobList();

    /**
     * Returns an unmodifiable view of the filtered job lists of all companies
     */
    ObservableList<JobOffer> getFilteredCompanyJobList();

    /**
     * Updates the filter of the filtered company job list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCompanyJobList(Predicate<JobOffer> predicate);

    /**
     * Adds a candidate to the candidateList of the jobOffer
     * @param jobOffer job offer to shortlist to
     * @param candidate candidate to be shortlisted
     */
    void shortlistCandidateToJobOffer(Candidate candidate, JobOffer jobOffer);

    /**
     * Deletes a candidate from the candidateList of the jobOffer
     * @param jobOffer job offer to delete from
     * @param candidate candidate to be deleted
     */
    void deleteShortlistedCandidateFromJobOffer(Candidate candidate, JobOffer jobOffer);

    // ================================== Email Command functions ===================================== //

    /**
     * Returns emailUtil in model
     */
    EmailUtil getEmailUtil();

    /**
     * Setter for emailUtil in model
     */
    void setEmailUtil(EmailUtil emailUtil);

    /**
     * resets emailUtil object
     */
    void resetEmailUtil();
}
