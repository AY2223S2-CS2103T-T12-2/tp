package seedu.medinfo.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.medinfo.commons.exceptions.DataConversionException;
import seedu.medinfo.model.MedInfo;
import seedu.medinfo.model.ReadOnlyMedInfo;

/**
 * Represents a storage for {@link MedInfo}.
 */
public interface MedInfoStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns MedInfo data as a {@link ReadOnlyMedInfo}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMedInfo> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyMedInfo> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMedInfo} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyMedInfo addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyMedInfo)
     */
    void saveAddressBook(ReadOnlyMedInfo addressBook, Path filePath) throws IOException;

}
