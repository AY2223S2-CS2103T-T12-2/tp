package seedu.medinfo.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.medinfo.commons.core.LogsCenter;
import seedu.medinfo.commons.exceptions.DataConversionException;
import seedu.medinfo.commons.exceptions.IllegalValueException;
import seedu.medinfo.commons.util.FileUtil;
import seedu.medinfo.commons.util.JsonUtil;
import seedu.medinfo.model.ReadOnlyMedInfo;

/**
 * A class to access MedInfo data stored as a json file on the hard disk.
 */
public class JsonMedInfoStorage implements MedInfoStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMedInfoStorage.class);

    private Path filePath;

    public JsonMedInfoStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMedInfo> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMedInfo> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableMedInfo> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableMedInfo.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyMedInfo addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyMedInfo)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyMedInfo addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMedInfo(addressBook), filePath);
    }

}
