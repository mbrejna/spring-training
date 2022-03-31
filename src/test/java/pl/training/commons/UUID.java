package pl.training.commons;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class UUID extends TypeSafeMatcher<String> {

    public static final UUID isValidUUID = new UUID();

    private static final String UUID_PATTERN = "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}";

    @Override
    protected boolean matchesSafely(String uuid) {
        return uuid.matches(UUID_PATTERN);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Invalid UUID");
    }

}
