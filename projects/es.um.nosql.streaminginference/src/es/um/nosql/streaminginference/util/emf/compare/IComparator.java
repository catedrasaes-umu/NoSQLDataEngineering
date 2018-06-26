package es.um.nosql.streaminginference.util.emf.compare;

public interface IComparator<T>
{
	public boolean compare(T t1, T t2);
}
