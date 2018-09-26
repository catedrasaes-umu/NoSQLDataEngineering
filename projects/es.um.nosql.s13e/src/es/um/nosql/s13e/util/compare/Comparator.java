package es.um.nosql.s13e.util.compare;

public abstract class Comparator<T>
{
  public abstract boolean compare(T o1, T o2);

  public boolean checkNulls(T o1, T o2)
  {
    return o1 == null || o2 == null;
  }

  public boolean checkEquals(T o1, T o2)
  {
    return o1 == o2 || o1.equals(o2);
  }
}
