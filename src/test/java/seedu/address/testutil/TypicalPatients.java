package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.patient.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {

    public static final Patient ALICE = new PatientBuilder().withNric("S1334567A")
            .withName("Alex Smith").build();
    public static final Patient BENSON = new PatientBuilder().withNric("S7654321F")
            .withName("Benson Tillman").build();
    public static final Patient CARL = new PatientBuilder().withNric("S2468024G")
            .withName("Carl Leigh").build();
    public static final Patient DANIEL = new PatientBuilder().withNric("S1244567A")
            .withName("Daniel Wellington").build();
    public static final Patient ELLE = new PatientBuilder().withNric("S1235567A")
            .withName("Elle Schmidt").build();
    public static final Patient FIONA = new PatientBuilder().withNric("S6969696B")
            .withName("Fiona Shrekt").build();
    public static final Patient GEORGE = new PatientBuilder().withNric("S1224567A")
            .withName("George Townsend").build();

    // Manually added
    public static final Patient HOON = new PatientBuilder().withName("Hoon Meier").withNric("T2222222L").build();
    public static final Patient IDA = new PatientBuilder().withName("Ida Mueller").withNric("S3333333L").build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder().withName(VALID_NAME_AMY).withNric(VALID_NRIC_AMY).build();
    public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB).withNric(VALID_NRIC_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatients() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Patient patient : getTypicalPatients()) {
            ab.addPatient(patient);
        }
        return ab;
    }

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
