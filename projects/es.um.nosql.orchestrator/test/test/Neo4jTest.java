package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;

import static org.neo4j.driver.v1.Values.parameters;

public class Neo4jTest
{
  private Driver driver = null;

  @Before
  public void setUp() throws Exception
  {
    driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "password"));
  }

  @After
  public void tearDown() throws Exception
  {
    driver.close();
  }

  @Test
  public void test()
  {
    String message = "this is the message!";

    try(Session session = driver.session())
    {
      String greeting = session.writeTransaction( new TransactionWork<String>()
      {
        @Override
        public String execute(Transaction tx)
        {
          StatementResult result = tx.run( "CREATE (a:Greeting) " + "SET a.message = $message " + "RETURN a.message + ', from node ' + id(a)",
              parameters("message", message));
          return result.single().get( 0 ).asString();
        }
      }
          );
      System.out.println( greeting );
    }
  }
}
