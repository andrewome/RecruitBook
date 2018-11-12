package seedu.recruit.commons.util;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Rule;
import org.junit.jupiter.api.Test;

import org.junit.rules.ExpectedException;
import seedu.recruit.model.candidate.Candidate;
import seedu.recruit.model.joboffer.JobOffer;
import seedu.recruit.testutil.CandidateBuilder;
import seedu.recruit.testutil.JobOfferBuilder;

public class EmailUtilTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CandidateBuilder candidateBuilder = new CandidateBuilder();
    private Candidate testCandidate = candidateBuilder.build();
    private JobOfferBuilder jobOfferBuilder = new JobOfferBuilder();
    private JobOffer testJobOffer = jobOfferBuilder.build();
    private EmailUtil emailUtil = new EmailUtil();

    @Test
    public void addCandidate_emailUtil_addTestObject() {
        int currentSize = emailUtil.getCandidates().size();
        emailUtil.addCandidate(testCandidate);
        assertEquals(currentSize + 1, emailUtil.getCandidates().size());
        assertFalse(emailUtil.addCandidate(testCandidate));
        assertEquals(currentSize + 1, emailUtil.getCandidates().size());
    }

    @Test
    public void addJobOffer_emailUtil_addTestObject() {
        int currentSize = emailUtil.getJobOffers().size();
        emailUtil.addJobOffer(testJobOffer);
        assertEquals(currentSize + 1, emailUtil.getJobOffers().size());
        assertFalse(emailUtil.addJobOffer(testJobOffer));
        assertEquals(currentSize + 1, emailUtil.getJobOffers().size());
    }

    @Test
    public void getRecipientJobOfferName_emailUtil_fromTestObject() {
        String testName = emailUtil.getRecipientJobOfferName(testJobOffer);
        StringBuilder actualName = new StringBuilder();
        actualName.append(testJobOffer.getCompanyName().toString());
        actualName.append(" regarding job offer: ");
        actualName.append(testJobOffer.getJob().toString());
        assertTrue(actualName.toString().equals(testName));
        assertFalse(testName.equals(testCandidate.toString()));
    }

    @Test
    public void getContentJobOfferName_emailUtil_fromTestObject() {
        String testName = emailUtil.getContentJobOfferName(testJobOffer);
        StringBuilder actualName = new StringBuilder();
        actualName.append(testJobOffer.getJob().toString());
        actualName.append(" at ");
        actualName.append(testJobOffer.getCompanyName().toString());
        assertTrue(actualName.toString().equals(testName));
        assertFalse(testName.equals(testJobOffer.toString()));
    }
}
