package es.um.nosql.s13e.m2m.util.printer;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

import es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec;
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation;
import es.um.nosql.s13e.EntityDifferentiation.EntityVersionProp;
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage;
import es.um.nosql.s13e.EntityDifferentiation.PropertySpec;
import es.um.nosql.s13e.util.emf.ResourceManager;

public class EntityDiffModelPrinter
{
  public static void main(String[] args)
  {		
    EntityDifferentiationPackage tdp = EntityDifferentiationPackage.eINSTANCE;
    ResourceManager rm = new ResourceManager(tdp);

    // Load the origin model.
    rm.loadResourcesAsStrings(new File("testOutput/mongoMovies3_Diff.xmi").getAbsolutePath());
    List<Resource> ediffList = new ArrayList<Resource>();
    ediffList.addAll(rm.getResourceSet().getResources());

    for (Resource resource : ediffList)
    {
      EntityDifferentiation diff = (EntityDifferentiation)resource.getContents().get(0);
      printModel(diff);
    }
  }

  private static void printModel(EntityDifferentiation entityDifferentiation)
  {
    for (EntityDiffSpec eds : entityDifferentiation.getEntityDiffSpecs())
    {
      System.out.println("Entity: " + eds.getEntity().getName());

      System.out.println("Common:");
      for (PropertySpec ps : eds.getCommonProps())
        System.out.println(" * " + ps.getProperty().getName() + (ps.isNeedsTypeCheck() ? "*" :""));

      for (EntityVersionProp evp : eds.getEntityVersionProps())
      {
        System.out.println("EV " + evp.getEntityVersion().getVersionId() + " -----------------");

        System.out.println("Own Properties:");
        for (PropertySpec ps: evp.getPropertySpecs())
          System.out.println(" * " + ps.getProperty().getName() + (ps.isNeedsTypeCheck() ? "*" :""));

        System.out.println("Properties NOT to have:");
        for (PropertySpec ps: evp.getNotProps())
          System.out.println(" * " + ps.getProperty().getName() + (ps.isNeedsTypeCheck() ? "*" :""));
      }
      System.out.println("======================");
    }
  }
}