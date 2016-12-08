/**
 *
 */
package es.um.nosql.schemainference.json2dbschema.util.abstractjson;

/**
 * @author dsevilla
 *
 */
public interface IAJIdentify
{
	public boolean isArray();

	public boolean isObject();

	public boolean isNumber();

	public boolean isTextual();

	public boolean isBoolean();

	public boolean isNull();

	public IAJArray asArray();

	public IAJObject asObject();

	public String asString();

	public IAJTextual asTextual();

	public IAJBoolean asBoolean();

	public IAJNull asNull();

	public IAJNumber asNumber();
}
