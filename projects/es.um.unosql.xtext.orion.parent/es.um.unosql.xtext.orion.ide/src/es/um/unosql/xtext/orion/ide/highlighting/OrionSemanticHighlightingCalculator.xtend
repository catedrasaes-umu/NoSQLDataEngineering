package es.um.unosql.xtext.orion.ide.highlighting

import org.eclipse.xtext.ide.editor.syntaxcoloring.DefaultSemanticHighlightingCalculator
import es.um.unosql.xtext.orion.services.OrionGrammarAccess
import org.eclipse.xtext.ide.editor.syntaxcoloring.IHighlightedPositionAcceptor
import es.um.unosql.xtext.orion.orion.OrionOperations
import es.um.unosql.xtext.orion.orion.EntityAddSpec
import es.um.unosql.xtext.orion.orion.OrionPackage
import org.eclipse.xtext.ide.editor.syntaxcoloring.HighlightingStyles
import es.um.unosql.xtext.orion.orion.EntityRenameSpec
import es.um.unosql.xtext.orion.orion.SimpleDataFeature
import es.um.unosql.xtext.orion.orion.AggregateMorphSpec
import es.um.unosql.xtext.orion.orion.ReferenceMorphSpec
import es.um.unosql.xtext.orion.orion.ReferenceAddSpec
import es.um.unosql.xtext.orion.orion.EntityMergeSpec
import es.um.unosql.xtext.orion.orion.EntityExtractSpec
import es.um.unosql.xtext.orion.orion.EntitySplitSpec
import es.um.unosql.xtext.orion.orion.EntityDeleteSpec
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.util.CancelIndicator
import com.google.inject.Inject
import es.um.unosql.xtext.orion.orion.FeatureSelector

class OrionSemanticHighlightingCalculator extends DefaultSemanticHighlightingCalculator
{
  @Inject package OrionGrammarAccess grammar

  override protected boolean highlightElement(EObject object, IHighlightedPositionAcceptor acceptor, CancelIndicator cancelIndicator)
  {
    switch (object)
    {
      OrionOperations:    { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.orionOperations_Name, HighlightingStyles.DEFAULT_ID) }
      EntityAddSpec:      { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityAddSpec_Name, HighlightingStyles.DEFAULT_ID) }
      EntityDeleteSpec:   { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityDeleteSpec_Ref, HighlightingStyles.DEFAULT_ID) }
      EntityRenameSpec:   { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityRenameSpec_Name, HighlightingStyles.DEFAULT_ID)
                            highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityRenameSpec_Ref, HighlightingStyles.DEFAULT_ID) }
      EntitySplitSpec:    { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entitySplitSpec_Ref, HighlightingStyles.DEFAULT_ID)
                            highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entitySplitSpec_Name1, HighlightingStyles.DEFAULT_ID)
                            highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entitySplitSpec_Name2, HighlightingStyles.DEFAULT_ID) }
      EntityExtractSpec:  { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityExtractSpec_Ref, HighlightingStyles.DEFAULT_ID)
                            highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityExtractSpec_Name, HighlightingStyles.DEFAULT_ID) }
      EntityMergeSpec:    { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityMergeSpec_Name, HighlightingStyles.DEFAULT_ID)
                            highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityMergeSpec_Ref1, HighlightingStyles.DEFAULT_ID)
                            highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityMergeSpec_Ref2, HighlightingStyles.DEFAULT_ID) }
      ReferenceAddSpec:   { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.referenceAddSpec_Ref, HighlightingStyles.DEFAULT_ID) }
      ReferenceMorphSpec: { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.referenceMorphSpec_Name, HighlightingStyles.DEFAULT_ID) }
      AggregateMorphSpec: { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.aggregateMorphSpec_Name, HighlightingStyles.DEFAULT_ID) }
      FeatureSelector:    { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.featureSelector_Ref, HighlightingStyles.DEFAULT_ID) }
      SimpleDataFeature:  { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.simpleDataFeature_Name, HighlightingStyles.DEFAULT_ID) }
    }

    super.highlightElement(object, acceptor, cancelIndicator)
  }
}
