package regression;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
{ ObjectIdTest.class, SimplifyAggrTest.class, TypesTest.class, CountTimestampTest.class })
public class AllTests
{

}
