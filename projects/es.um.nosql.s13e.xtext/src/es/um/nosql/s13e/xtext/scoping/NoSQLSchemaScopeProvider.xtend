package es.um.nosql.s13e.xtext.scoping

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import es.um.nosql.s13e.NoSQLSchema.Aggregate
import org.eclipse.xtext.scoping.Scopes
import es.um.nosql.s13e.NoSQLSchema.EntityType

/**
 * This class contains custom scoping description.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 */
class NoSQLSchemaScopeProvider extends AbstractNoSQLSchemaScopeProvider
{
  override getScope(EObject context, EReference reference)
  {
    if (context instanceof Aggregate)
    {
      //TODO: Solve Aggregations...
      val aggr = context as Aggregate
      return Scopes.scopeFor((aggr.eContainer.eContainer as EntityType).variations)
    }

    return super.getScope(context, reference)
  }
}
