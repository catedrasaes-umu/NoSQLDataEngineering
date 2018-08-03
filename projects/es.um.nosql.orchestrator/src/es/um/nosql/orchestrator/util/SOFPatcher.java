package es.um.nosql.orchestrator.util;

import java.io.File;

import org.eclipse.emf.common.util.EList;

import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.util.ModelLoader;
import es.um.nosql.s13e.util.NoSQLSchemaWriter;

public class SOFPatcher
{
  public static void main(String[] args)
  {
    SOFPatcher patcher = new SOFPatcher();
    patcher.patch("../es.um.nosql.models/stackoverflow/stackoverflow.xmi");
  }

  public void patch(String modelRoute)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File(modelRoute), NoSQLSchema.class);

    for (EntityClass e : schema.getEntities())
      switch (e.getName())
      {
        case "Users": {patchUsers(e.getVariations()); break;}
        case "Badges": {patchBadges(e.getVariations()); break;}
        case "Tags": {patchTags(e.getVariations()); break;}
        case "Postlinks": {patchPostlinks(e.getVariations()); break;}
        case "Votes": {patchVotes(e.getVariations()); break;}
        case "Comments": {patchComments(e.getVariations()); break;}
        case "Posts": {patchPosts(e.getVariations()); break;}
      }

    NoSQLSchemaWriter writer = new NoSQLSchemaWriter();
    writer.write(schema, modelRoute);
  }

  private void patchUsers(EList<StructuralVariation> variations)
  {
    for (StructuralVariation sv : variations)
    {
      Property rmUpVotes = null;
      Property rmDownVotes = null;

      for (Property p : sv.getProperties())
        if (p.getName().equals("AccountId") || p.getName().equals("Age") || p.getName().equals("Reputation"))
          ((Attribute)p).setType(createPrimitiveType("Number"));
        else if (p.getName().equals("UpVotes"))
          rmUpVotes = p;
        else if (p.getName().equals("DownVotes"))
          rmDownVotes = p;

      if (rmUpVotes != null)
      {
        sv.getProperties().remove(rmUpVotes);
        sv.getProperties().add(createAttribute("UpVotes", "Number"));
      }
      if (rmDownVotes != null)
      {
        sv.getProperties().remove(rmDownVotes);
        sv.getProperties().add(createAttribute("DownVotes", "Number"));
      }
    }
  }

  private void patchBadges(EList<StructuralVariation> variations)
  {
    for (StructuralVariation sv : variations)
    {
      Property rmTagBased = null;

      for (Property p : sv.getProperties())
        if (p.getName().equals("Class"))
          ((Attribute)p).setType(createPrimitiveType("Number"));
        else if (p.getName().equals("TagBased"))
          rmTagBased = p;

      if (rmTagBased != null)
      {
        sv.getProperties().remove(rmTagBased);
        sv.getProperties().add(createAttribute("TagBased", "String"));
      }
    }
  }

  private void patchTags(EList<StructuralVariation> variations)
  {
    for (StructuralVariation sv : variations)
    {
      Property rmTagName = null;

      for (Property p : sv.getProperties())
        if (p.getName().equals("Count"))
          ((Attribute)p).setType(createPrimitiveType("Number"));
        else if (p.getName().equals("TagName"))
          rmTagName = p;

      if (rmTagName != null)
      {
        sv.getProperties().remove(rmTagName);
        sv.getProperties().add(createAttribute("TagName", "String"));
      }
    }
  }

  private void patchPostlinks(EList<StructuralVariation> variations)
  {
    for (StructuralVariation sv : variations)
      for (Property p : sv.getProperties())
        if (p.getName().equals("LinkTypeId"))
          ((Attribute)p).setType(createPrimitiveType("Number"));
  }

  private void patchVotes(EList<StructuralVariation> variations)
  {
    for (StructuralVariation sv : variations)
    {
      Property rmVoteTypeId = null;

      for (Property p : sv.getProperties())
        if (p.getName().equals("BountyAmount"))
          ((Attribute)p).setType(createPrimitiveType("Number"));
        else if (p.getName().equals("VoteTypeId"))
          rmVoteTypeId = p;

      if (rmVoteTypeId != null)
      {
        sv.getProperties().remove(rmVoteTypeId);
        sv.getProperties().add(createAttribute("VoteTypeId", "Number"));
      }
    }
  }

  private void patchComments(EList<StructuralVariation> variations)
  {
    for (StructuralVariation sv : variations)
    {
      Property rmUserDisplayName = null;

      for (Property p : sv.getProperties())
        if (p.getName().equals("Score"))
          ((Attribute)p).setType(createPrimitiveType("Number"));
        else if (p.getName().equals("UserDisplayName"))
          rmUserDisplayName = p;

      if (rmUserDisplayName != null)
      {
        sv.getProperties().remove(rmUserDisplayName);
        sv.getProperties().add(createAttribute("UserDisplayName", "String"));
      }
    }
  }

  private void patchPosts(EList<StructuralVariation> variations)
  {
    for (StructuralVariation sv : variations)
    {
      Property rmPostTypeId = null;

      for (Property p : sv.getProperties())
        if (p.getName().equals("AnswerCount") || p.getName().equals("Score"))
          ((Attribute)p).setType(createPrimitiveType("Number"));
        else if (p.getName().equals("PostTypeId"))
          rmPostTypeId = p;

      if (rmPostTypeId != null)
      {
        sv.getProperties().remove(rmPostTypeId);
        sv.getProperties().add(createAttribute("PostTypeId", "Number"));
      }
    }
  }

  private Attribute createAttribute(String name, String type)
  {
    Attribute theAttribute = NoSQLSchemaFactory.eINSTANCE.createAttribute();
    theAttribute.setName(name);
    theAttribute.setType(createPrimitiveType(type));

    return theAttribute;
  }

  private PrimitiveType createPrimitiveType(String type)
  {
    PrimitiveType theType = NoSQLSchemaFactory.eINSTANCE.createPrimitiveType();
    theType.setName(type);

    return theType;
  }
}
