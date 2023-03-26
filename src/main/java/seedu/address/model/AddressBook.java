package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.UniquePatientList;
import seedu.address.model.ward.UniqueWardList;
import seedu.address.model.ward.Ward;
import seedu.address.model.ward.exceptions.WardNotFoundException;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePatient comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePatientList patients;
    private final UniqueWardList wards;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication
     * between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other
     * ways to avoid duplication
     * among constructors.
     */
    {
        patients = new UniquePatientList();
        wards = new UniqueWardList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Patients in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the patient list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setPatients(List<Patient> patients) {
        this.patients.setPatients(patients);
    }

    /**
     * Replaces the contents of the ward list with {@code wards}.
     * {@code wards} must not contain duplicate wards.
     */
    public void setWards(List<Ward> wards) {
        this.wards.setWards(wards);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPatients(newData.getPatientList());
        setWards(newData.getWardList());
//        fixData();
    }

    //// patient-level operations

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in
     * the address book.
     */
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return patients.contains(patient);
    }

    /**
     * Returns true if a patient with the same NRIC as {@code patient} exists in
     * the address book.
     */
    public boolean hasPatientNric(Patient patient) {
        requireNonNull(patient);
        return patients.containsNric(patient);
    }

    /**
     * Adds a patient to the address book.
     * The patient must not already exist in the address book.
     */
    public void addPatient(Patient p) {
        if (!wards.contains(p.getWard())) { //If wardlist does not contain patient's ward, don't add it in.
            throw new WardNotFoundException();
        }
        patients.add(p);
        wards.addPatient(p);
    }

    /**
     * Replaces the given patient {@code target} in the list with
     * {@code editedPatient}.
     * {@code target} must exist in the address book.
     * The patient identity of {@code editedPatient} must not be the same as another
     * existing patient in the address book.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);
        patients.setPatient(target, editedPatient);
        wards.setPatient(target, editedPatient);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePatient(Patient key) {
        requireNonNull(key);
        patients.remove(key);
        wards.remove(key);
    }

    //// ward-level operations

    /**
     * Returns true if a ward with the same identity as {@code ward} exists in
     * the address book.
     */
    public boolean hasWard(Ward ward) {
        requireNonNull(ward);
        return wards.contains(ward);
    }

    /**
     * Adds a ward to the address book.
     * The ward must not already exist in the address book.
     */
    public void addWard(Ward ward) {
        wards.add(ward);
    }

    /**
     * Replaces the given ward {@code target} in the list with
     * {@code editedWard}.
     * {@code target} must exist in the address book.
     * The ward identity of {@code editedWard} must not be the same as another
     * existing ward in the address book.
     */
    public void setWard(Ward target, Ward editedWard) {
        requireNonNull(editedWard);
        wards.setWard(target, editedWard);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeWard(Ward ward) {
        wards.remove(ward);
    }


    //// util methods

    @Override
    public String toString() {
        return patients.asUnmodifiableObservableList().size() + " patients";
    }

    @Override
    public ObservableList<Patient> getPatientList() {
        return patients.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Ward> getWardList() {
        return wards.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                        && patients.equals(((AddressBook) other).patients));
    }

    @Override
    public int hashCode() {
        return patients.hashCode();
    }
}
