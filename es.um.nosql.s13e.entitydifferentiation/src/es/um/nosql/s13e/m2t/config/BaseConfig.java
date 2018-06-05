package es.um.nosql.s13e.m2t.config;

import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation;

public abstract class BaseConfig
{
  // For the moment we will return a boolean. Just in case we need it later on.
  public abstract boolean doCheck(EntityDifferentiation diff);
}
