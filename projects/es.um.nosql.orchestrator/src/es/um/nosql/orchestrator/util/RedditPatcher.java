package es.um.nosql.orchestrator.util;

import java.io.File;

import org.eclipse.emf.common.util.EList;

import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.util.ModelLoader;
import es.um.nosql.s13e.util.NoSQLSchemaWriter;

public class RedditPatcher
{
  public static void main(String[] args)
  {
    RedditPatcher patcher = new RedditPatcher();
    patcher.patch("../es.um.nosql.models/reddit/reddit.xmi");
  }

  public void patch(String modelRoute)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File(modelRoute), NoSQLSchema.class);

    for (EntityType e : schema.getEntities())
      switch (e.getName())
      {
        case "Comments": {patchComments(e.getVariations()); break;}
        case "Authors": {patchAuthors(e.getVariations()); break;}
        case "Subreddits": {patchSubreddits(e.getVariations()); break;}
        case "Moderators": {patchModerators(e.getVariations()); break;}
      }

    NoSQLSchemaWriter writer = new NoSQLSchemaWriter();
    writer.write(schema, modelRoute);
  }

  private void patchComments(EList<StructuralVariation> variations)
  {
    for (StructuralVariation sv : variations)
    {
      Property rmSubreddit = null;
      Property rmAuthor_flair_css_class = null;
      Property rmAuthor_flair_text = null;

      for (Property p : sv.getProperties())
        if (p.getName().equals("subreddit"))
          rmSubreddit = p;
        else if (p.getName().equals("author_flair_css_class"))
          rmAuthor_flair_css_class = p;
        else if (p.getName().equals("author_flair_text"))
          rmAuthor_flair_text = p;

      if (rmSubreddit != null)
      {
        sv.getProperties().remove(rmSubreddit);
        sv.getProperties().add(createAttribute("subreddit", "String", rmSubreddit.isOptional()));
      }
      if (rmAuthor_flair_css_class != null)
      {
        sv.getProperties().remove(rmAuthor_flair_css_class);
        sv.getProperties().add(createAttribute("author_flair_css_class", "String", rmAuthor_flair_css_class.isOptional()));
      }
      if (rmAuthor_flair_text != null)
      {
        sv.getProperties().remove(rmAuthor_flair_text);
        sv.getProperties().add(createAttribute("author_flair_text", "String", rmAuthor_flair_text.isOptional()));
      }
    }
  }

  private void patchAuthors(EList<StructuralVariation> variations)
  {
    for (StructuralVariation sv : variations)
    {
      Property rmCommentKarma = null;

      for (Property p : sv.getProperties())
        if (p.getName().equals("comment_karma"))
          rmCommentKarma = p;

      if (rmCommentKarma != null)
      {
        sv.getProperties().remove(rmCommentKarma);
        sv.getProperties().add(createAttribute("comment_karma", "Number", rmCommentKarma.isOptional()));
      }
    }
  }

  private void patchSubreddits(EList<StructuralVariation> variations)
  {
    for (StructuralVariation sv : variations)
    {
      Property rmSubredditType = null;
      Property rmHidden = null;

      for (Property p : sv.getProperties())
        if (p.getName().equals("subreddit_type"))
          rmSubredditType = p;
        else if (p.getName().equals("comment_score_hide_mins"))
          rmHidden = p;

      if (rmSubredditType != null)
      {
        sv.getProperties().remove(rmSubredditType);
        sv.getProperties().add(createAttribute("subreddit_type", "String", rmSubredditType.isOptional()));
      }
      if (rmHidden != null)
      {
        sv.getProperties().remove(rmHidden);
        sv.getProperties().add(createAttribute("comment_score_hide_mins", "String", rmHidden.isOptional()));
      }
    }
  }

  private void patchModerators(EList<StructuralVariation> variations)
  {
    for (StructuralVariation sv : variations)
    {
      Property rmSubredditName = null;
      Property rmSubredditType = null;
      Property rmUserData = null;

      for (Property p : sv.getProperties())
        if (p.getName().equals("subreddit_name"))
          rmSubredditName = p;
        else if (p.getName().equals("subreddit_type"))
          rmSubredditType = p;
        else if (p.getName().equals("user_data"))
          rmUserData = p;

      if (rmSubredditName != null)
      {
        sv.getProperties().remove(rmSubredditName);
        sv.getProperties().add(createAttribute("subreddit_name", "String", rmSubredditName.isOptional()));
      }
      if (rmSubredditType != null)
      {
        sv.getProperties().remove(rmSubredditType);
        sv.getProperties().add(createAttribute("subreddit_type", "String", rmSubredditType.isOptional()));
      }
      if (rmUserData != null)
      {
        sv.getProperties().remove(rmUserData);
        Reference ref = NoSQLSchemaFactory.eINSTANCE.createReference();
        ref.setName("user_data");
        ref.setLowerBound(1);
        ref.setUpperBound(-1);
        ref.setOriginalType("String");
        EntityType eT = ((NoSQLSchema)sv.getContainer().eContainer()).getEntities().stream().filter(entity -> entity.getName().equals("Authors")).findFirst().get();
        ref.setRefsTo(eT);
        sv.getProperties().add(ref);
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
