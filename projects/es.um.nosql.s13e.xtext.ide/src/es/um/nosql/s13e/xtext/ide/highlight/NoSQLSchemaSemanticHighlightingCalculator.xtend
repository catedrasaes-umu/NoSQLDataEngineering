package es.um.nosql.s13e.xtext.ide.highlight

import org.eclipse.xtext.ide.editor.syntaxcoloring.DefaultSemanticHighlightingCalculator
import javax.inject.Inject
import es.um.nosql.s13e.xtext.services.NoSQLSchemaGrammarAccess
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.ide.editor.syntaxcoloring.IHighlightedPositionAcceptor
import org.eclipse.xtext.util.CancelIndicator
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema
import es.um.nosql.s13e.NoSQLSchema.SchemaType
import es.um.nosql.s13e.NoSQLSchema.Property
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage
import org.eclipse.xtext.ide.editor.syntaxcoloring.HighlightingStyles
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType

class NoSQLSchemaSemanticHighlightingCalculator extends DefaultSemanticHighlightingCalculator
{
  // Watch out. Apparently google.inject works as fine as javax.inject
  // We will stick to javax.inject but feel free to check google.inject in case errors appear.
  @Inject package NoSQLSchemaGrammarAccess grammar

  override protected boolean highlightElement(EObject object, IHighlightedPositionAcceptor acceptor, CancelIndicator cancelIndicator)
  {
    switch (object)
    {
      NoSQLSchema:
      {
        highlightFeature(acceptor, object, NoSQLSchemaPackage.eINSTANCE.noSQLSchema_Name, HighlightingStyles.DEFAULT_ID)
      }
      SchemaType:
      {
        highlightFeature(acceptor, object, NoSQLSchemaPackage.eINSTANCE.schemaType_Name, HighlightingStyles.DEFAULT_ID)
      }
      Property:
      {
        highlightFeature(acceptor, object, NoSQLSchemaPackage.eINSTANCE.property_Name, HighlightingStyles.DEFAULT_ID)
      }
      PrimitiveType:
      {
        highlightFeature(acceptor, object, NoSQLSchemaPackage.eINSTANCE.primitiveType_Name, HighlightingStyles.DEFAULT_ID)
      }
    }

    false
  }
}
