import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssessmentClassFilterTest {
    private Account a1, a2;

    private final String[] assessmentClasses = {"100", "","","Pass","",""};

    private final String[] assessmentClasses2 = {"100", "","","Fail","",""};

    private AssessmentClassFilter filter;
    @BeforeEach
    void setUp() {
        this.a1 = new Account();
        this.a1.setAssessmentClasses(assessmentClasses);

        this.a2 = new Account();
        this.a2.setAssessmentClasses(assessmentClasses2);

        this.filter = new AssessmentClassFilter("Pass");
    }

    @Test
    void filter() {
        assertTrue(filter.filter(a1));
        assertFalse(filter.filter(a2));
    }
}