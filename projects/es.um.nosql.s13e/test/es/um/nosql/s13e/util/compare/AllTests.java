package es.um.nosql.s13e.util.compare;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
{ CompareNoSQLSchemaTest.class, ComparePropertyTest.class, CompareDataTypeTest.class })
public class AllTests
{
}
