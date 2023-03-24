package seedu.address.testutil;

import seedu.address.model.patient.Discharge;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Status;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_NRIC = "T1234567A";
    public static final String DEFAULT_NAME = "Alex Smith";
    public static final String DEFAULT_STATUS = "GRAY";
    public static final String DEFAULT_WARD = "Waiting Room";
    public static final String DEFAULT_DISCHARGE = "To Be Confirmed";


    private Nric nric;
    private Name name;
    private Status status;
    private String ward;
    private Discharge discharge;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PatientBuilder() {
        nric = new Nric(DEFAULT_NRIC);
        name = new Name(DEFAULT_NAME);
        status = new Status(DEFAULT_STATUS);
        ward = DEFAULT_WARD;
        discharge = new Discharge(DEFAULT_DISCHARGE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        nric = patientToCopy.getNric();
        name = patientToCopy.getName();
        status = patientToCopy.getStatus();
        ward = patientToCopy.getWard();
        discharge = patientToCopy.getDischarge();
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code Patient} that we are building.
     */
    public PatientBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Patient} that we are building.
     */
    public PatientBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Ward} of the {@code Patient} that we are building.
     */
    public PatientBuilder withWard(String ward) {
        this.ward = ward;
        return this;
    }

    /**
     * Sets the {@code Discharge} of the {@code Patient} that we are building.
     */
    public PatientBuilder withDischarge(String discharge) {
        this.discharge = new Discharge(discharge);
        return this;
    }

    public Patient build() {
        return new Patient(nric, name, status, ward, discharge);
    }
}
