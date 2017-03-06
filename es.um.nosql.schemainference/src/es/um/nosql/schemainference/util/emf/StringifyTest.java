package es.um.nosql.schemainference.util.emf;

import java.io.File;

import es.um.nosql.schemainference.NoSQLSchema.Entity;
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.schemainference.NoSQLSchema.Property;

public class StringifyTest
{
	public static void main(String[] args)
	{
		ModelLoader<NoSQLSchema> loader = new ModelLoader<NoSQLSchema>(NoSQLSchemaPackage.eINSTANCE);
		NoSQLSchema schema = loader.load(new File("/home/alberto/repos/NoSQLDataEngineering/es.um.nosql.orchestrator.test/models/mongoMovies3.xmi"));
		
		for (Entity entity : schema.getEntities())
			for (EntityVersion eVersion : entity.getEntityversions())
			{
				System.out.println(entity.getName() + "_" + eVersion.getVersionId());
				for (Property property : eVersion.getProperties())
					System.out.println("\t" + NoSQLSchemaSerializer.getInstance().serialize(property));
			}
//		System.out.println(NoSQLSchemaPrettyPrinter.getInstance().stringify(schema));
	}
}
