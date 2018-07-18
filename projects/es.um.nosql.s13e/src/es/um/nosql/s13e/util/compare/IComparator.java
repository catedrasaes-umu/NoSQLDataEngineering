package es.um.nosql.s13e.util.compare;

public interface IComparator<T>
{
	public boolean compare(T t1, T t2);
}
