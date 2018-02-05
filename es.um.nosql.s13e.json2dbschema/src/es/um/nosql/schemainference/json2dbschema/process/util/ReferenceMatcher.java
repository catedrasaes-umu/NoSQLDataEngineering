/**
 * 
 */
package es.um.nosql.s13e.json2dbschema.process.util;

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
			Arrays.asList("id", "ptr", "ref", "ids", "refs", "has", "");

	private static List<String> StopChars =
			Arrays.asList("_", ".", "-", "");

	// Unlikely words to appear in a reference
	private static List<String> UnlikelyWords = Arrays.asList("count");

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
						Pair.of(("^" + entry.getKey() + c + affix + ".*$").toLowerCase(), entry.getValue())),
					Stream.concat(StopChars.stream().map(c ->
							Pair.of(("^" + affix + c + entry.getKey() + ".*$").toLowerCase(), entry.getValue())),
						// postfix
						Stream.concat(StopChars.stream().filter(c -> !c.isEmpty() || !affix.isEmpty()).map(c ->
								Pair.of(("^.*?" + entry.getKey() + c + affix + "$").toLowerCase(), entry.getValue())),								
									StopChars.stream().filter(c -> !c.isEmpty() || !affix.isEmpty()).map(c ->
										Pair.of(("^.*?" + affix + c + entry.getKey() + "$").toLowerCase(), entry.getValue()))))
				)
			)
		).collect(Collectors.toList());
	}

	public Optional<T> maybeMatch(String id)
	{
		if (UnlikelyWords.stream().anyMatch(w -> id.toLowerCase().contains(w)))
			return Optional.<T>empty();
		
		return idRegexps.stream()
			.filter(pair -> id.toLowerCase().matches(pair.getKey())).findFirst()
			.map(Pair::getValue);
	}
}
