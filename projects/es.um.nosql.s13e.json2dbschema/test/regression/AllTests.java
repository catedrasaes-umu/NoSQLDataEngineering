package regression;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
{ InflectorTest.class, J2SchemaSimpleTests.class, RemovePMapTest.class })
public class AllTests
{

}
