package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_SALES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";

    public static final String VALID_ORDER_INDEX_ONE = "1";
    public static final String VALID_ORDER_INDEX_TWO = "2";
    public static final String VALID_PRODUCT_CUPCAKE = "Cupcake";
    public static final String VALID_PRODUCT_COOKIE = "Cookie";
    public static final String VALID_PRODUCT_DOUGHNUT = "Doughnut";
    public static final String VALID_PRICE_TEN = "10";
    public static final String VALID_PRICE_TWENTY = "20";
    public static final String VALID_PRICE_THIRTY = "30";
    public static final String VALID_PRICE_FORTY = "40";
    public static final String VALID_QUANTITY_ONE = "1";
    public static final String VALID_QUANTITY_TWO = "2";
    public static final String VALID_QUANTITY_THREE = "3";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String PRODUCT_DESC_CUPCAKE = " " + PREFIX_PRODUCT_NAME + VALID_PRODUCT_CUPCAKE;
    public static final String PRODUCT_DESC_CUPCAKE_WITH_PRICE = " " + PREFIX_PRODUCT_NAME + VALID_PRODUCT_CUPCAKE
            + " " + PREFIX_PRODUCT_COST + VALID_PRICE_TEN + " " + PREFIX_PRODUCT_SALES + VALID_PRICE_TWENTY;
    public static final String PRODUCT_DESC_COOKIE = " " + PREFIX_PRODUCT_NAME + VALID_PRODUCT_COOKIE;
    public static final String PRODUCT_DESC_COOKIE_WITH_PRICE = " " + PREFIX_PRODUCT_NAME + VALID_PRODUCT_COOKIE
            + " " + PREFIX_PRODUCT_COST + VALID_PRICE_THIRTY + " " + PREFIX_PRODUCT_SALES + VALID_PRICE_FORTY;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String ORDER_INDEX_DESC_ONE = " " + PREFIX_ORDER + VALID_ORDER_INDEX_ONE;
    public static final String ORDER_INDEX_DESC_TWO = " " + PREFIX_ORDER + VALID_ORDER_INDEX_TWO;
    public static final String COST_DESC_TEN = " " + PREFIX_PRODUCT_COST + VALID_PRICE_TEN;
    public static final String SALES_DESC_TWENTY = " " + PREFIX_PRODUCT_SALES + VALID_PRICE_TWENTY;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PRODUCT_DESC = " " + PREFIX_PRODUCT_NAME + "Cupcakes&"; // '&' not allowed
    public static final String INVALID_QUANTITY_DESC = " " + PREFIX_PRODUCT_QUANTITY + "a"; // 'a' not allowed
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_PRODUCT_DESC_COST = " " + PREFIX_PRODUCT_NAME + "Cupcakes "
            + PREFIX_PRODUCT_COST + "20c";

    public static final String INVALID_PRODUCT_DESC_SALES = " " + PREFIX_PRODUCT_NAME + "Cupcakes "
            + PREFIX_PRODUCT_SALES + "20c";
    public static final String INVALID_PRODUCT_DESC_MISSING_NAME = " " + PREFIX_PRODUCT_SALES + VALID_PRICE_TWENTY
            + " " + PREFIX_PRODUCT_COST + VALID_PRICE_THIRTY;
    public static final String INVALID_PRODUCT_DESC_MISSING_COST = " " + PREFIX_PRODUCT_NAME + "Cupcakes "
            + PREFIX_PRODUCT_SALES + VALID_PRICE_TWENTY;

    public static final String INVALID_PRODUCT_DESC_MISSING_SALES = " " + PREFIX_PRODUCT_NAME + "Cupcakes "
            + PREFIX_PRODUCT_COST + VALID_PRICE_TWENTY;
    public static final String INVALID_PRODUCT_DESC_MISSING_NAME_PREFIX = " " + "Cupcakes "
            + PREFIX_PRODUCT_SALES + VALID_PRICE_TWENTY + " " + PREFIX_PRODUCT_COST + VALID_PRICE_THIRTY;
    public static final String INVALID_PRODUCT_DESC_MISSING_COST_PREFIX = " " + PREFIX_PRODUCT_NAME + "Cupcakes "
            + PREFIX_PRODUCT_SALES + VALID_PRICE_TWENTY + " " + VALID_PRICE_THIRTY;

    public static final String INVALID_PRODUCT_DESC_MISSING_SALES_PREFIX = " " + PREFIX_PRODUCT_NAME + "Cupcakes "
            + VALID_PRICE_TWENTY + " " + PREFIX_PRODUCT_COST + VALID_PRICE_THIRTY;


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCustomerCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCustomerCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
