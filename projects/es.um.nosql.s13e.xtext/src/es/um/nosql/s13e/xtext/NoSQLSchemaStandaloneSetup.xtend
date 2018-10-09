package es.um.nosql.s13e.xtext


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class NoSQLSchemaStandaloneSetup extends NoSQLSchemaStandaloneSetupGenerated {

	def static void doSetup() {
		new NoSQLSchemaStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}
