/*
 * generated by Xtext 2.20.0
 */
package es.um.unosql.xtext.orion.scoping

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference

/**
 * This class contains custom scoping description.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 */
class OrionScopeProvider extends AbstractOrionScopeProvider
{
  override getScope(EObject context, EReference reference)
  {
/*    if (context instanceof EntityRef)
    {
      val rootElement = EcoreUtil2.getRootContainer(context) as OrionOperations

      if (rootElement.importURI === null)
        return Scopes.scopeFor(EcoreUtil2.getAllContentsOfType(rootElement, EntityName))
      else
        return Scopes.scopeFor(EcoreUtil2.getAllContentsOfType(rootElement, EntityName)
          + EcoreUtil2.getAllContentsOfType(rootElement.importURI, es.um.unosql.xtext.athena.athena.EntityDecl))
    }
    if (context instanceof EntitySplit && (context as EntitySplit).ref !== null)
    {
      val ref = (context as EntitySplit).ref.ref
      if (ref instanceof es.um.unosql.xtext.athena.athena.EntityDecl)
      {
        val candidates = EcoreUtil2.getAllContentsOfType(ref, SimpleDataFeature)

        return Scopes.scopeFor(candidates)
      } else if (ref instanceof EntityDecl)
      {
        val rootElement = EcoreUtil2.getRootContainer(context) as OrionOperations
        val candidates = EcoreUtil2.getAllContentsOfType(rootElement, FeatureDecl)

        return Scopes.scopeFor(candidates)
      }
    }*/
/*    // TODO: Problema. Las variaciones se mapean como 1, 2, 3...y no como mongomovies::Entity::1. Tenemos que conseguir renombrar esto.
    if (context instanceof FeatureSetDecl || context instanceof SchemaTypeDecl || context instanceof VariationDecl
      || context instanceof CommonSpec || context instanceof StructureExpr)
    {
      val rootElement = EcoreUtil2.getRootContainer(context) as AthenaSchema
//      val candidates = AthenaScopeUtils.getAllFeatureSets(rootElement) +
//        AthenaScopeUtils.getAllEntities(rootElement) +
//        AthenaScopeUtils.getAllRelationships(rootElement) +
//        AthenaScopeUtils.getAllVariations(rootElement)

//      return Scopes.scopeFor(candidates)

      val candidates = AthenaScopeUtils.getAllQualifiedFeatureSets(rootElement) + AthenaScopeUtils.getAllQualifiedEntities(rootElement) +
        AthenaScopeUtils.getAllQualifiedRelationships(rootElement) + AthenaScopeUtils.getAllQualifiedVariations(rootElement)

      return new SimpleScope(IScope.NULLSCOPE, candidates)
    }
*/
    return super.getScope(context, reference)
  }
}
