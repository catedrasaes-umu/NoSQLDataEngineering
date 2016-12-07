package es.um.nosql.schemainference.util.emf.compare;

public interface IComparator<T>
{
	public boolean compare(T t1, T t2);
}
