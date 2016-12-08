/**
 * 
 */
package es.um.nosql.schemainference.json2dbschema.process.util;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author dsevilla
 *
 */
public class ReferenceMatcher<T>
{
	// List of affixes to check for references
	private static List<String> Affixes =
			Arrays.asList("_id", "id", "_ptr", "_ref", "_ids", "_refs");

	private List<Pair<String, T>> idRegexps;
		
	public ReferenceMatcher(Stream<Pair<String, T>> stream)
	{		
		// Build the regexp that will allow checking if a field may be a reference
		// to another entity
		idRegexps = stream.flatMap(entry -> 
			Stream.concat(
				Affixes.stream().map(str ->
					Pair.of((entry.getKey() + str).toLowerCase(), entry.getValue()))
					,
				Stream.concat(
						Affixes.stream().map(str ->
							Pair.of((str + entry.getKey()).toLowerCase(), entry.getValue()))
						,
						Stream.of(Pair.of(entry.getKey().toLowerCase(), entry.getValue()))))
		).collect(Collectors.toList());
	}
	
	public Optional<T> maybeMatch(String id)
	{
		return idRegexps.stream()
			.filter(pair -> id.toLowerCase().matches(pair.getKey())).findFirst()
			.map(Pair::getValue);
	}
}
