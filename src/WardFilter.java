public class WardFilter implements AccountFilter {
    String filter;

    public WardFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public boolean filter(Account account) {
        return account.getWard().equals(this.filter);
    }
}
