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

import com.google.common.base.Strings;

/**
 * @author dsevilla
 *
 */
public class ReferenceMatcher<T>
{
	// List of affixes to check for references
	private static List<String> Affixes =
			Arrays.asList("id", "ptr", "ref", "ids", "refs", "");

	private static List<String> StopChars =
			Arrays.asList("_", ".", "-", "");

	private List<Pair<String,T>> idRegexps;
		
	public ReferenceMatcher(Stream<Pair<String, T>> stream)
	{		
		// Build the regexp that will allow checking if a field may be a reference
		// to another entity
		
		idRegexps = stream.flatMap(entry ->
			Affixes.stream().flatMap(affix ->
				Stream.concat(
					// prefix
					StopChars.stream().map(c ->
						Pair.of(("^" + entry.getKey() + c + affix).toLowerCase(), entry.getValue())),
					// postfix
					StopChars.stream().filter(c -> !c.isEmpty() || !affix.isEmpty()).map(c ->
						Pair.of((affix + c + entry.getKey()).toLowerCase(), entry.getValue()))
				)
			)
		).collect(Collectors.toList());
	}
	
	public Optional<T> maybeMatch(String id)
	{
		return idRegexps.stream()
			.filter(pair -> id.toLowerCase().matches(pair.getKey())).findFirst()
			.map(Pair::getValue);
	}
}
