package es.um.unosql.xtext.athena.scoping

import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider
import es.um.unosql.xtext.athena.athena.AthenaSchema
import org.eclipse.xtext.naming.QualifiedName

class AthenaQualifiedNameProvider extends DefaultDeclarativeQualifiedNameProvider
{
  def QualifiedName qualifiedName(AthenaSchema schema)
  {
    return QualifiedName.create(schema.id.name + ":" + Integer.toString(schema.id.version))
  }
}
