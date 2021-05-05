package es.um.unosql.xtext.orion.ide.highlighting

import org.eclipse.xtext.ide.editor.syntaxcoloring.DefaultSemanticHighlightingCalculator
import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.ide.editor.syntaxcoloring.IHighlightedPositionAcceptor
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.ide.editor.syntaxcoloring.HighlightingStyles
import es.um.unosql.xtext.orion.services.OrionGrammarAccess
import es.um.unosql.xtext.orion.orion.OrionOperations
import es.um.unosql.xtext.orion.orion.OrionPackage
import es.um.unosql.xtext.orion.orion.EntityAdd
import es.um.unosql.xtext.orion.orion.EntityDel
import es.um.unosql.xtext.orion.orion.EntityRename
import es.um.unosql.xtext.orion.orion.FeatureSelector
import es.um.unosql.xtext.orion.orion.AggrMorph
import es.um.unosql.xtext.orion.orion.RefMorph
import es.um.unosql.xtext.orion.orion.RefAdd
import es.um.unosql.xtext.orion.orion.EntitySplit
import es.um.unosql.xtext.orion.orion.EntityExtract
import es.um.unosql.xtext.orion.orion.EntityMerge
import es.um.unosql.xtext.orion.orion.SimpleDataFeature

class OrionSemanticHighlightingCalculator extends DefaultSemanticHighlightingCalculator
{
  @Inject package OrionGrammarAccess grammar

  override protected boolean highlightElement(EObject object, IHighlightedPositionAcceptor acceptor, CancelIndicator cancelIndicator)
  {
    switch (object)
    {
      OrionOperations:   { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.orionOperations_Name, HighlightingStyles.DEFAULT_ID) }
      EntityAdd:         { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityAdd_Name, HighlightingStyles.DEFAULT_ID) }
      EntityDel:         { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityDel_Ref, HighlightingStyles.DEFAULT_ID) }
      EntityRename:      { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityRename_Name, HighlightingStyles.DEFAULT_ID)
                           highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityRename_Ref, HighlightingStyles.DEFAULT_ID) }
      EntitySplit:       { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entitySplit_Ref, HighlightingStyles.DEFAULT_ID)
                           highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entitySplit_Name1, HighlightingStyles.DEFAULT_ID)
                           highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entitySplit_Name2, HighlightingStyles.DEFAULT_ID) }
      EntityExtract:     { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityExtract_Ref, HighlightingStyles.DEFAULT_ID)
                           highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityExtract_Name, HighlightingStyles.DEFAULT_ID) }
      EntityMerge:       { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityMerge_Name, HighlightingStyles.DEFAULT_ID)
                           highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityMerge_Ref1, HighlightingStyles.DEFAULT_ID)
                           highlightFeature(acceptor, object, OrionPackage.eINSTANCE.entityMerge_Ref2, HighlightingStyles.DEFAULT_ID) }
      RefAdd:            { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.refAdd_Ref, HighlightingStyles.DEFAULT_ID) }
      RefMorph:          { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.refMorph_Name, HighlightingStyles.DEFAULT_ID) }
      AggrMorph:         { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.aggrMorph_Name, HighlightingStyles.DEFAULT_ID) }
      FeatureSelector:   { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.featureSelector_Ref, HighlightingStyles.DEFAULT_ID) }
      SimpleDataFeature: { highlightFeature(acceptor, object, OrionPackage.eINSTANCE.simpleDataFeature_Name, HighlightingStyles.DEFAULT_ID) }
    }

    super.highlightElement(object, acceptor, cancelIndicator)
  }
}
