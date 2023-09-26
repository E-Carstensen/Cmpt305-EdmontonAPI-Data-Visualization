import java.util.HashMap;

public class AssessmentClassFilter implements AccountFilter {
    String assessmentClass;

    public AssessmentClassFilter(String assessmentClass) {
        this.assessmentClass = assessmentClass;
    }
    @Override
    public boolean filter(Account account) {
        return account.getAssessmentClasses().containsKey(this.assessmentClass);
    }
}
