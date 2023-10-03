import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NeighborhoodFilterTest {

    private Account a1, a2;

    private NeighborhoodFilter filter;

    @BeforeEach
    void setUp() {
        this.a1 = new Account();
        this.a1.setNeighborhood("Pass");

        this.a2 = new Account();
        this.a2.setNeighborhood("Fail");

        this.filter = new NeighborhoodFilter("Pass");
    }

    @Test
    void filter() {
        assertTrue(this.filter.filter(a1));
        assertFalse(this.filter.filter(a2));

    }
}