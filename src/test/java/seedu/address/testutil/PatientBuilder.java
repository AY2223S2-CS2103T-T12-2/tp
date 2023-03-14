package seedu.address.testutil;

import seedu.address.model.patient.NRIC;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_NRIC = "T1234567A";
    public static final String DEFAULT_NAME = "Alex Smith";

    private NRIC nric;
    private Name name;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PatientBuilder() {
        nric = new NRIC(DEFAULT_NRIC);
        name = new Name(DEFAULT_NAME);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        nric = patientToCopy.getNric();
        name = patientToCopy.getName();
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code NRIC} of the {@code Patient} that we are building.
     */
    public PatientBuilder withNRIC(String nric) {
        this.nric = new NRIC(nric);
        return this;
    }

    public Patient build() {
        return new Patient(nric, name);
    }

}