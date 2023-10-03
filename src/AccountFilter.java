/**
 * Interface for different filter types
 * Filters Accounts depending on different parameters of the account object
 * Defines one function, filter, which returns a boolean ir Account matches filter
 */

public interface AccountFilter {
    boolean filter(Account account);
}


