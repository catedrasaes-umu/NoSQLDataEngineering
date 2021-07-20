package es.um.unosql.xtext.athena.ide.highlighting

import org.eclipse.xtext.ide.editor.syntaxcoloring.DefaultSemanticHighlightingCalculator
import com.google.inject.Inject
import es.um.unosql.xtext.athena.services.AthenaGrammarAccess
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.ide.editor.syntaxcoloring.IHighlightedPositionAcceptor
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.ide.editor.syntaxcoloring.HighlightingStyles
import es.um.unosql.xtext.athena.athena.AthenaPackage
import es.um.unosql.xtext.athena.athena.TopLevelStructureDefiningElementDecl
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.athena.athena.ComposedReference
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.athena.ComposedReferenceTarget
import es.um.unosql.xtext.athena.athena.SimpleAggregateTarget
import es.um.unosql.xtext.athena.athena.VariationDeclRef
import es.um.unosql.xtext.athena.athena.TopLevelStructureDefiningElementDeclRef
import es.um.unosql.xtext.athena.athena.SQLStructure
import es.um.unosql.xtext.athena.athena.SQLConstraintDefinition
import es.um.unosql.xtext.athena.athena.SQLReferenceTarget
import es.um.unosql.xtext.athena.athena.SQLDefinition
import es.um.unosql.xtext.athena.athena.SchemaId

class AthenaSemanticHighlightingCalculator extends DefaultSemanticHighlightingCalculator
{
  @Inject package AthenaGrammarAccess grammar

  override protected boolean highlightElement(EObject object, IHighlightedPositionAcceptor acceptor, CancelIndicator cancelIndicator)
  {
    switch (object)
    {
      SchemaId:                                { highlightFeature(acceptor, object, AthenaPackage.eINSTANCE.schemaId_Name, HighlightingStyles.DEFAULT_ID) }
      SimpleFeature:                           { highlightFeature(acceptor, object, AthenaPackage.eINSTANCE.simpleFeature_Name, HighlightingStyles.DEFAULT_ID) }
      TopLevelStructureDefiningElementDecl:    { highlightFeature(acceptor, object, AthenaPackage.eINSTANCE.topLevelStructureDefiningElementDecl_Name, HighlightingStyles.DEFAULT_ID) }
      ComposedReference:                       { highlightFeature(acceptor, object, AthenaPackage.eINSTANCE.composedReference_Names, HighlightingStyles.DEFAULT_ID) }
      SQLStructure:                            { highlightFeature(acceptor, object, AthenaPackage.eINSTANCE.SQLStructure_Name, HighlightingStyles.DEFAULT_ID) }
      SQLConstraintDefinition:                 { highlightFeature(acceptor, object, AthenaPackage.eINSTANCE.SQLConstraintDefinition_ColNames, HighlightingStyles.DEFAULT_ID) }
      SQLReferenceTarget:                      { highlightFeature(acceptor, object, AthenaPackage.eINSTANCE.SQLReferenceTarget_Ref, HighlightingStyles.DEFAULT_ID)
                                                 highlightFeature(acceptor, object, AthenaPackage.eINSTANCE.SQLReferenceTarget_ColNames, HighlightingStyles.DEFAULT_ID) }
      SQLDefinition:                           { highlightFeature(acceptor, object, AthenaPackage.eINSTANCE.SQLDefinition_Name, HighlightingStyles.DEFAULT_ID) }
      SimpleReferenceTarget:                   { highlightFeature(acceptor, object, AthenaPackage.eINSTANCE.simpleReferenceTarget_Ref, HighlightingStyles.DEFAULT_ID)
                                                 highlightFeature(acceptor, object, AthenaPackage.eINSTANCE.simpleReferenceTarget_FeaturedBy, HighlightingStyles.DEFAULT_ID) }
      ComposedReferenceTarget:                 { highlightFeature(acceptor, object, AthenaPackage.eINSTANCE.composedReferenceTarget_Ref, HighlightingStyles.DEFAULT_ID) }
      SimpleAggregateTarget:                   { highlightFeature(acceptor, object, AthenaPackage.eINSTANCE.simpleAggregateTarget_Aggr, HighlightingStyles.DEFAULT_ID) }
      VariationDeclRef:                        { highlightFeature(acceptor, object, AthenaPackage.eINSTANCE.variationDeclRef_Ref, HighlightingStyles.DEFAULT_ID) }
      TopLevelStructureDefiningElementDeclRef: { highlightFeature(acceptor, object, AthenaPackage.eINSTANCE.topLevelStructureDefiningElementDeclRef_Ref, HighlightingStyles.DEFAULT_ID) }
    }

    super.highlightElement(object, acceptor, cancelIndicator)
  }
}
