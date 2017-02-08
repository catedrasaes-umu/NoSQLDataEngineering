package es.um.nosql.schemainference.nosqlimport.util.schparse;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class SchemaParser<State extends StateBase>
{
	private State state;
	
	private Map<Character, Function<State,State>> parsers;
	
	private void _init()
	{
		parsers = new HashMap<>();
		// Add the parser for ':'
		addParser(':', s -> {s.skipInput(1); return s;});
	}
	
	public SchemaParser(State state)
	{
		this.state = state;
		_init();
	}

	public SchemaParser()
	{
		state = null;
		_init();
	}
	
	public SchemaParser<State> addParser(Character c, Function<State,State> p)
	{
		parsers.put(c,p);
		return this;
	}
	
	public StateBase getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public StateBase parse()
	{
		int c;
		while ((c = state.peek()) != -1)
		{
			char q = (char) c;
			
			if (parsers.containsKey(q))
				state = parsers.get(q).apply(state);
			else
				state.skipInput(1);
		}
		return null;	
	}


}
