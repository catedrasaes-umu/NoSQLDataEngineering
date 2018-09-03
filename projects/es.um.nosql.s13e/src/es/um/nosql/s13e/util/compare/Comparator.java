package es.um.nosql.s13e.util.compare;

public abstract class Comparator<Q> implements IComparator<Q>
{
  @Override
  public boolean compare(Q t1, Q t2) 
  {
    return t1 != null && t1 == t2 && t1.equals(t2);
  }
}
