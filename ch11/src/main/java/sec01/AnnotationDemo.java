package sec01;

import sec02.BugReport;
import sec02.Reference;
import sec02.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.List;

@interface Entity {
}

@interface Param {
    String value();
}

@Target(ElementType.TYPE_PARAMETER)
@interface Immutable {
}


public abstract class AnnotationDemo {
    @SuppressWarnings("unchecked")
    private List<User> users = (List<User>) getList("users");

    @Test
    public void checkRandomInsertions() {
    }

    @Test(timeout = 10000)
    public void hopefullyNotTooSlowAlgo() {
    }

    @BugReport(showStopper = true,
            assignedTo = "Harry",
            testCase = CacheTest.class,
            status = BugReport.Status.CONFIRMED)
    public void buggyMethod() {
    }

    @BugReport(reportedBy = {"Harry", "Fred"})
    public void anotherBuggyMethod() {
    }

    @BugReport(reportedBy = "Harry")
    public void yetAnotherBuggyMethod() {
    }

    @BugReport(ref = @Reference(id = 11235811))
    public void andAnotherBuggyMethod() {
    }

    @Test
    @BugReport(showStopper = true, reportedBy = "Joe")
    public void checkMoreRandomInsertions() {
    }

    @BugReport(showStopper = true, reportedBy = "Joe")
    @BugReport(reportedBy = {"Harry", "Carl"})
    public void checkEvenMoreRandomInsertions() {
    }

    public abstract List<?> getList(String table);

    public User getUser(@Param("id") String userId) {
        return null;
    }

    class CacheTest {
    }

    @Entity
    public class User {
    }

    public class Cache<@Immutable V> {
    }
}
