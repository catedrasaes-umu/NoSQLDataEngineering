package es.um.unosql.xtext.athena;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CrossReferenceTest.class, ExprSimplifierTest.class, InnerStructureLiteralTest.class, ModelLoaderTest.class, ModelSerializerTest.class, ModelWriterTest.class, AthenaFormatterTest.class })
public class AllTests
{
}
