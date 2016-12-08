/**
 *
 */
package es.um.nosql.schemainference.json2dbschema.main.util;


import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonProcessingException;

import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.json2dbschema.process.NoSQLModelBuilder;
import es.um.nosql.schemainference.json2dbschema.process.SchemaInference;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJAdapter;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJArray;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJElement;

/**
 * @author dsevilla
 *
 * @param <JE> JSON Element (Base object of the JSON hierarchy)
 * @param <Adapter> The specific adapter for this JSON Element used
 */
public class JSON2Schema<JE, Adapter extends IAJAdapter<JE>>
{
	private final NoSQLSchemaFactory factory;
	private final IAJAdapter<JE> adapter;

	public JSON2Schema(NoSQLSchemaFactory factory, Adapter adapter)
	{
		this.factory = factory;
		this.adapter = adapter;
	}

	public es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema fromJSONFile(String jsonFileName) throws JsonProcessingException, IOException
	{
		File jsonFile = new File(jsonFileName);

		// Extract schema name from the file name
		String schemaName = jsonFile.getName().split("\\.")[0];

		// Read the JSON file
		IAJElement root = adapter.readFromFile(jsonFile);
		assert(root.isArray());

		IAJArray rows = root.get("rows").asArray();

		return fromJSONArray(schemaName, rows);
	}

	public NoSQLSchema fromJSONArray(String schemaName, IAJArray rows)
	{
		SchemaInference si = new SchemaInference(rows);
		NoSQLModelBuilder builder = new NoSQLModelBuilder(factory, schemaName);
		return builder.build(si.infer());
	}
}
