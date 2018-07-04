/**
 * 
 */
package es.um.nosql.streaminginference.json2dbschema.process.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

	private Map<String, Stream<Pair<String, T>>> idRegexps;

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
				.map(pair -> Pair.of(entry.getKey().toLowerCase(), Stream.of(pair)))
			)
		)
		.collect(Collectors.toMap(Pair::getKey, Pair::getValue, (p, q) -> Stream.concat(p,q)));
	}

	public Optional<T> maybeMatch(String id)
	{
		String lowerId = id.toLowerCase();
		if (UnlikelyWords.stream().anyMatch(w -> lowerId.contains(w)))
			return Optional.<T>empty();
		
		for (String candidate : idRegexps.keySet()) 
		{
			if (lowerId.contains(candidate)) 
			{
				Optional<T> op = idRegexps
						.get(candidate)
						.filter(pair -> lowerId
								.matches(pair.getKey()))
						.findFirst()
						.map(pair -> pair.getValue());
				if (op.isPresent())
					return op;
			}
		}
		
		return Optional.empty();
	}
}
