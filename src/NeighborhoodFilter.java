/**
 * AccountFilter interface
 * Filters Account objects based on their neighborhood
 *
 */

public class NeighborhoodFilter implements AccountFilter {
    String filter;
    public NeighborhoodFilter(String filter) {
        this.filter = filter;
    }

    public boolean filter(Account account) { return account.getNeighborhood().equals(filter); }
}
