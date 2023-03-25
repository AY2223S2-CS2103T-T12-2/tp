package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_WARD;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ward.Ward;

/**
 * Adds a patient to MedInfo.
 */
public class AddWardCommand extends Command{

    public static final String COMMAND_WORD = "addward";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a ward to MedInfo. \n"
            + "Parameters: "
            + PREFIX_WARD + "WARDNUMBER\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_WARD + "A03\n";

    public static final String MESSAGE_SUCCESS = "New ward added: %1$s";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This ward already exists in MedInfo";

    private final Ward toAdd;

    /**
     * Creates an AddWardCommand to add the specified {@code Patient}
     */
    public AddWardCommand(Ward ward) {
        requireNonNull(ward);
        toAdd = ward;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasWard(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        model.addWard(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddWardCommand // instanceof handles nulls
                && toAdd.equals(((AddWardCommand) other).toAdd));
    }

}
