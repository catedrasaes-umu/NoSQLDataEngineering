/**
 *
 */
package es.um.nosql.streaminginference.json2dbschema.util.abstractjson;

import java.io.File;

/**
 * @author dsevilla
 *
 */
public interface IAJAdapter<JET>
{
	public IAJElement wrap(JET e);

	public IAJElement readFromFile(File jsonFile);
	
	public IAJElement readFromString(String content);
}
