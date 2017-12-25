package es.um.nosql.schemainference.m2t.config;

import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation;

public abstract class BaseConfig
{
  // For the moment we will return a boolean. Just in case we need it later on.
  public abstract boolean doCheck(EntityDifferentiation diff);
}
