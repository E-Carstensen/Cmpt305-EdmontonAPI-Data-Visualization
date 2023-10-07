import java.util.HashMap;


/**
 * Filters properties by their Assessment Class
 * Returns true if the property given in filter() contains an assessment class
 * matching the assessment class given in the constructor
 * Ignores case
 */
public class AssessmentClassFilter implements AccountFilter {
    String assessmentClass;

    public AssessmentClassFilter(String assessmentClass) {
        this.assessmentClass = assessmentClass; // Set filter to the assessment class specified in constructor
    }
    @Override
    public boolean filter(Account account) { // Check each assessment class against the filter

        if (account.assessmentClasses == null) {return false;}

        for (String key : account.getAssessmentClasses().keySet()) {
            if (key.equalsIgnoreCase(this.assessmentClass)) { // If class matches filter ignoring case, return true
                return true;
            }
        }
        return false; // No match, return false
    }
}
