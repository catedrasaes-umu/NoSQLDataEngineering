package es.um.nosql.orchestrator.util;

import java.io.File;
import java.util.List;

import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.PList;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.util.ModelLoader;
import es.um.nosql.s13e.util.NoSQLSchemaWriter;

public class TheMovieDBPatcher
{
  public static void main(String[] args)
  {
    TheMovieDBPatcher patcher = new TheMovieDBPatcher();
    patcher.patch("../es.um.nosql.models/themoviedb/themoviedb.xmi");
  }

  public void patch(String modelRoute)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File(modelRoute), NoSQLSchema.class);

    for (EntityType e : schema.getEntities())
      switch (e.getName())
      {
        case "Episodes": {patchEpisodes(e.getVariations()); break;}
        case "Seasons": {patchSeasons(e.getVariations()); break;}
        case "Tv": {patchTv(e.getVariations()); break;}
      }

    NoSQLSchemaWriter writer = new NoSQLSchemaWriter();
    writer.write(schema, modelRoute);
  }

  private void patchTv(List<StructuralVariation> variations)
  {
    for (StructuralVariation var : variations)
    {
      Property rmNOfSeasons = null;
      Property rmNOfEpisodes = null;
      Property rmEpRuntime = null;

      for (Property prop : var.getProperties())
        if (prop.getName().equals("number_of_seasons"))
          rmNOfSeasons = prop;
        else if (prop.getName().equals("number_of_episodes"))
          rmNOfEpisodes = prop;
        else if (prop.getName().equals("episode_run_time"))
          rmEpRuntime = prop;

      if (rmNOfSeasons != null)
      {
        var.getProperties().remove(rmNOfSeasons);
        var.getProperties().add(createAttribute("number_of_seasons", "Number", rmNOfSeasons.isOptional()));
      }
      if (rmNOfEpisodes != null)
      {
        var.getProperties().remove(rmNOfEpisodes);
        var.getProperties().add(createAttribute("number_of_episodes", "Number", rmNOfEpisodes.isOptional()));
      }
      if (rmEpRuntime != null)
      {
        var.getProperties().remove(rmEpRuntime);
        Attribute theAttribute = NoSQLSchemaFactory.eINSTANCE.createAttribute();
        theAttribute.setName("episode_run_time");
        theAttribute.setOptional(rmEpRuntime.isOptional());
        PList theList = NoSQLSchemaFactory.eINSTANCE.createPList();
        theList.setElementType(createPrimitiveType("Number"));
        var.getProperties().add(theAttribute);
      }
    }
  }

  private void patchSeasons(List<StructuralVariation> variations)
  {
    for (StructuralVariation var : variations)
    {
      Property rmSeasonNumber = null;

      for (Property prop : var.getProperties())
        if (prop.getName().equals("season_number"))
          rmSeasonNumber = prop;

      if (rmSeasonNumber != null)
      {
        var.getProperties().remove(rmSeasonNumber);
        var.getProperties().add(createAttribute("season_number", "Number", rmSeasonNumber.isOptional()));
      }
    }
  }

  private void patchEpisodes(List<StructuralVariation> variations)
  {
    for (StructuralVariation var : variations)
    {
      Property rmEpisodeNumber = null;

      for (Property prop : var.getProperties())
        if (prop.getName().equals("episode_number"))
          rmEpisodeNumber = prop;

      if (rmEpisodeNumber != null)
      {
        var.getProperties().remove(rmEpisodeNumber);
        var.getProperties().add(createAttribute("episode_number", "Number", rmEpisodeNumber.isOptional()));
      }
    }
  }

  private Attribute createAttribute(String name, String type, boolean optional)
  {
    Attribute theAttribute = NoSQLSchemaFactory.eINSTANCE.createAttribute();
    theAttribute.setName(name);
    theAttribute.setType(createPrimitiveType(type));
    theAttribute.setOptional(optional);

    return theAttribute;
  }

  private PrimitiveType createPrimitiveType(String type)
  {
    PrimitiveType theType = NoSQLSchemaFactory.eINSTANCE.createPrimitiveType();
    theType.setName(type);

    return theType;
  }
}
