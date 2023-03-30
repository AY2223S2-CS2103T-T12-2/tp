package seedu.medinfo.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.medinfo.commons.exceptions.DataConversionException;
import seedu.medinfo.model.ReadOnlyMedInfo;
import seedu.medinfo.model.ReadOnlyUserPrefs;
import seedu.medinfo.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends MedInfoStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyMedInfo> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyMedInfo addressBook) throws IOException;

}
