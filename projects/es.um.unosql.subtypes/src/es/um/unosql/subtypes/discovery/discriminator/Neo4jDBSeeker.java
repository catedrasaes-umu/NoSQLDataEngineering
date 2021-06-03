package es.um.unosql.subtypes.discovery.discriminator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import es.um.unosql.subtypes.util.types.EntitySubtype;
import es.um.unosql.uNoSQLSchema.Feature;

import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;

import static org.neo4j.driver.Values.parameters;

public class Neo4jDBSeeker implements DBDiscriminatorSeeker, AutoCloseable {

	private Driver client;
	
	private Feature discriminator;
	private Map<EntitySubtype, Object> discriminatorValues;
	private List<EntitySubtype> subtypes;
	private List<Feature> candidates;
	

	public Neo4jDBSeeker(String ip) {
		this.client = GraphDatabase.driver(ip);
	}

	@Override
	public List<EntitySubtype> getSubtypes() {
		return this.subtypes;
	}

	@Override
	public List<Feature> getCandidates() {
		return this.candidates;
	}

	@Override
	public Feature getDiscriminator() {
		return this.discriminator;
	}

	@Override
	public Map<EntitySubtype, Object> getDiscriminatorValues() {
		return this.discriminatorValues;
	}

	@Override
	public void doSeek(String collectionName, List<EntitySubtype> subtypes, List<Feature> candidates) {

	    this.subtypes = subtypes;
	    this.candidates = candidates;
	    

	    Map<Feature, Map<EntitySubtype, Object>> candidateMap = new HashMap<Feature, Map<EntitySubtype, Object>>();
	    // List<Feature> candidatesToBeRemoved = new ArrayList<Feature>();
	    

	    
	    
//	    // For each candidate feature:
//	    //  We create a map entry: <Feature>: Map<Subtype, Value>
//	    for (Feature candidate : candidates)
//	      candidateMap.put(candidate, new HashMap<EntitySubtype, Object>());
//	    
//	    for (Document doc : documents.find())
//	    {
//	      // For each candidate feature, get this document subtype
//	      // Was the candidate feature initialized?
//	      // No: Then initialize the feature value with the read value
//	      // Yes: Are the stored value and the read value the same?
//	      //   Yes: Go on!
//	      //   No: Then eliminate this feature as candidate
//
//	      // When finishing, among all remaining candidate feature, get the one with all distinct values between subtypes.
//
//	      EntitySubtype theSubtype = detectSubtype(doc);
//	      if (theSubtype == null)
//	        continue;
//	      // If subtype could not be identified then the object is an outlier or its subtype was not identified by the previous process.
//	      // Ignore it and continue.
//
//	      for (Feature candidate : candidates)
//	      {
//	        Map<EntitySubtype, Object> auxMap = candidateMap.get(candidate);
//	        if (!auxMap.containsKey(theSubtype))
//	          auxMap.put(theSubtype, doc.get(candidate.getName()));
//	        else if (!auxMap.get(theSubtype).equals(doc.get(candidate.getName())))
//	          candidatesToBeRemoved.add(candidate);
//	      }
//
//	      candidates.removeAll(candidatesToBeRemoved);
//	      candidatesToBeRemoved.clear();
//	    }
//
//	    client.close();

	    // From all remaining candidates, get the one with all distinct values for each subtype.
	    for (Feature candidate : candidateMap.keySet())
	      if (candidateMap.get(candidate).values().stream().distinct().count() != candidateMap.get(candidate).size())
	        candidates.remove(candidate);

	    discriminatorValues = candidateMap.get(candidates.get(0));
	    discriminator = candidates.get(0);
	    
	    
	    
	    
	    
		final String string = "asd";
		
		try ( Session session = client.session() )
        {
            String greeting = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
					Result result = tx.run( "CREATE (a:Greeting) " +
                                                     "SET a.message = $message " +
                                                     "RETURN a.message + ', from node ' + id(a)",
                            parameters( "message", string ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
	}


    public static void main( String... args ) throws Exception
    {
        try ( Neo4jDBSeeker greeter = new Neo4jDBSeeker( "bolt://localhost:7687") )
        {
            greeter.doSeek( "hello, world", new LinkedList<EntitySubtype>(), new LinkedList<Feature>() );
        }
    }
    
	@Override
	public void close() throws Exception {
		client.close();
	}

}
