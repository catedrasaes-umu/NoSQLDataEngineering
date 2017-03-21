package es.um.nosql.schemainference.decisiontree.gen.js;

import com.google.common.base.Objects;
import es.um.nosql.schemainference.NoSQLSchema.Aggregate;
import es.um.nosql.schemainference.NoSQLSchema.Attribute;
import es.um.nosql.schemainference.NoSQLSchema.Entity;
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.schemainference.NoSQLSchema.PrimitiveType;
import es.um.nosql.schemainference.NoSQLSchema.Property;
import es.um.nosql.schemainference.NoSQLSchema.Reference;
import es.um.nosql.schemainference.NoSQLSchema.Tuple;
import es.um.nosql.schemainference.NoSQLSchema.Type;
import es.um.nosql.schemainference.decisiontree.DecisionTrees;
import es.um.nosql.schemainference.decisiontree.DecisiontreePackage;
import es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec;
import es.um.nosql.schemainference.entitydifferentiation.EntityVersionProp;
import es.um.nosql.schemainference.entitydifferentiation.PropertySpec;
import es.um.nosql.schemainference.util.emf.ResourceManager;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class DecisionTreeToJS {
  private String modelName = "";
  
  private final String SPECIAL_TYPE_IDENTIFIER = "type";
  
  public static void main(final String[] args) {
    try {
      int _length = args.length;
      boolean _lessThan = (_length < 1);
      if (_lessThan) {
        System.out.println("Usage: DiffToJS model [outdir]");
        System.exit((-1));
      }
      String _head = IterableExtensions.<String>head(((Iterable<String>)Conversions.doWrapArray(args)));
      final File inputModel = new File(_head);
      final ResourceManager rm = new ResourceManager(DecisiontreePackage.eINSTANCE, 
        NoSQLSchemaPackage.eINSTANCE);
      String _path = inputModel.getPath();
      rm.loadResourcesAsStrings(_path);
      Iterable<Resource> _resources = rm.getResources();
      Resource _head_1 = IterableExtensions.<Resource>head(_resources);
      EList<EObject> _contents = _head_1.getContents();
      EObject _head_2 = IterableExtensions.<EObject>head(_contents);
      final DecisionTrees trees = ((DecisionTrees) _head_2);
      String _xifexpression = null;
      int _length_1 = args.length;
      boolean _greaterThan = (_length_1 > 1);
      if (_greaterThan) {
        _xifexpression = args[1];
      } else {
        _xifexpression = ".";
      }
      File _file = new File(_xifexpression);
      Path _path_1 = _file.toPath();
      String _name = trees.getName();
      Path _resolve = _path_1.resolve(_name);
      final File outputDir = _resolve.toFile();
      outputDir.mkdirs();
      String _path_2 = inputModel.getPath();
      String _plus = ("Generating Javascript for " + _path_2);
      String _plus_1 = (_plus + " in ");
      String _path_3 = outputDir.getPath();
      String _plus_2 = (_plus_1 + _path_3);
      System.out.println(_plus_2);
      final DecisionTreeToJS dt_to_js = new DecisionTreeToJS();
      Path _path_4 = outputDir.toPath();
      String _name_1 = trees.getName();
      String _plus_3 = (_name_1 + ".js");
      Path _resolve_1 = _path_4.resolve(_plus_3);
      final File outFile = _resolve_1.toFile();
      final PrintStream outFileWriter = new PrintStream(outFile);
      CharSequence _generate = dt_to_js.generate(trees);
      outFileWriter.println(_generate);
      outFileWriter.close();
      System.exit(0);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Method used to generate an Inclusive/Exclusive differences file for a NoSQLDifferences object.
   */
  public CharSequence generate(final DecisionTrees dt) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field diff is undefined"
      + "\nentityDiffSpecs cannot be resolved");
  }
  
  public CharSequence genSpecs(final List<EntityDiffSpec> list) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _hasElements = false;
      for(final EntityDiffSpec de : list) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "");
        }
        CharSequence _genEntityDiffs = this.genEntityDiffs(de);
        _builder.append(_genEntityDiffs, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence genEntityDiffs(final EntityDiffSpec spec) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<EntityVersionProp> _entityVersionProps = spec.getEntityVersionProps();
      boolean _hasElements = false;
      for(final EntityVersionProp evp : _entityVersionProps) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "");
        }
        CharSequence _genEntityVersionDiff = this.genEntityVersionDiff(evp, spec);
        _builder.append(_genEntityVersionDiff, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence genEntityVersionDiff(final EntityVersionProp evp, final EntityDiffSpec spec) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field DUCK_TYPE is undefined");
  }
  
  public CharSequence generateHints(final EntityVersionProp evp, final EntityDiffSpec spec, final boolean exact) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field p is undefined"
      + "\nThe method or field p is undefined");
  }
  
  public CharSequence genProp(final PropertySpec p) {
    CharSequence _xifexpression = null;
    boolean _isNeedsTypeCheck = p.isNeedsTypeCheck();
    if (_isNeedsTypeCheck) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("(\"");
      Property _property = p.getProperty();
      String _name = _property.getName();
      _builder.append(_name, "");
      _builder.append("\" in obj) && ");
      Property _property_1 = p.getProperty();
      CharSequence _genTypeCheck = this.genTypeCheck(_property_1);
      _builder.append(_genTypeCheck, "");
      _xifexpression = _builder;
    } else {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("(\"");
      Property _property_2 = p.getProperty();
      String _name_1 = _property_2.getName();
      _builder_1.append(_name_1, "");
      _builder_1.append("\" in obj)");
      _xifexpression = _builder_1;
    }
    return _xifexpression;
  }
  
  public CharSequence genNotPropForPropName(final String p) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("!(\"");
    _builder.append(p, "");
    _builder.append("\" in obj)");
    return _builder;
  }
  
  protected CharSequence _genTypeCheck(final Property p) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  protected CharSequence _genTypeCheck(final Aggregate a) {
    StringConcatenation _builder = new StringConcatenation();
    {
      int _lowerBound = a.getLowerBound();
      boolean _equals = (_lowerBound == 0);
      if (_equals) {
        _builder.append("(obj.");
        String _name = a.getName();
        _builder.append(_name, "");
        _builder.append(".constructor === Array) &&");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("obj.");
        String _name_1 = a.getName();
        _builder.append(_name_1, "    ");
        _builder.append(".every(function(e)");
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("{ return (typeof e === \'object\') && !(e.constructor === Array)");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("&& (");
        {
          EList<EntityVersion> _refTo = a.getRefTo();
          boolean _hasElements = false;
          for(final EntityVersion rt : _refTo) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(" || ", "            ");
            }
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append(this.modelName, "            ");
            _builder.append(".");
            EObject _eContainer = rt.eContainer();
            String _name_2 = ((Entity) _eContainer).getName();
            _builder.append(_name_2, "            ");
            _builder.append("_");
            int _versionId = rt.getVersionId();
            _builder.append(_versionId, "            ");
            _builder.append(".isOfExactType(e)");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
          }
        }
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("})");
        _builder.newLine();
      } else {
        EList<EntityVersion> _refTo_1 = a.getRefTo();
        EntityVersion refToEV = _refTo_1.get(0);
        _builder.newLineIfNotEmpty();
        _builder.append("(typeof obj.");
        String _name_3 = a.getName();
        _builder.append(_name_3, "");
        _builder.append(" === \'object\') && !(obj.");
        String _name_4 = a.getName();
        _builder.append(_name_4, "");
        _builder.append(".constructor === Array)");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("&& ");
        _builder.append(this.modelName, "    ");
        _builder.append(".");
        EObject _eContainer_1 = refToEV.eContainer();
        String _name_5 = ((Entity) _eContainer_1).getName();
        _builder.append(_name_5, "    ");
        _builder.append("_");
        int _versionId_1 = refToEV.getVersionId();
        _builder.append(_versionId_1, "    ");
        _builder.append(".isOfExactType(obj.");
        String _name_6 = a.getName();
        _builder.append(_name_6, "    ");
        _builder.append(")");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  protected CharSequence _genTypeCheck(final Reference r) {
    StringConcatenation _builder = new StringConcatenation();
    {
      int _lowerBound = r.getLowerBound();
      boolean _equals = (_lowerBound == 0);
      if (_equals) {
        _builder.append("(obj.");
        String _name = r.getName();
        _builder.append(_name, "");
        _builder.append(".constructor === Array) &&");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("(obj.");
        String _name_1 = r.getName();
        _builder.append(_name_1, "    ");
        _builder.append(".every(function(e) { return typeof e === \"number\";})");
        _builder.newLineIfNotEmpty();
        _builder.append("     ");
        _builder.append("||");
        _builder.newLine();
        _builder.append("     ");
        _builder.append("obj.");
        String _name_2 = r.getName();
        _builder.append(_name_2, "     ");
        _builder.append(".every(function(e) { return typeof e === \"string\";}))");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("((typeof obj.");
        String _name_3 = r.getName();
        _builder.append(_name_3, "");
        _builder.append(" === \"number\") || (typeof obj.");
        String _name_4 = r.getName();
        _builder.append(_name_4, "");
        _builder.append(" === \"string\"))");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  protected CharSequence _genTypeCheck(final Attribute a) {
    Type _type = a.getType();
    String _name = a.getName();
    String _plus = ("obj." + _name);
    return this.genTypeCheckLowLevel(_type, _plus);
  }
  
  protected CharSequence _genTypeCheckLowLevel(final Type type, final String name) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  private boolean isInt(final String type) {
    return Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("int", "integer", "number")).contains(type);
  }
  
  private boolean isFloat(final String type) {
    return Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("float", "double")).contains(type);
  }
  
  private boolean isBoolean(final String type) {
    return Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("boolean", "bool")).contains(type);
  }
  
  protected CharSequence _genTypeCheckLowLevel(final PrimitiveType type, final String name) {
    CharSequence _switchResult = null;
    String _name = type.getName();
    String _lowerCase = _name.toLowerCase();
    final String typeName = _lowerCase;
    boolean _matched = false;
    if (Objects.equal(typeName, "string")) {
      _matched=true;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("(typeof ");
      _builder.append(name, "");
      _builder.append(" === \"string\")");
      _switchResult = _builder;
    }
    if (!_matched) {
      boolean _isInt = this.isInt(typeName);
      if (_isInt) {
        _matched=true;
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("(typeof ");
        _builder_1.append(name, "");
        _builder_1.append(" === \"number\") && (");
        _builder_1.append(name, "");
        _builder_1.append(" % 1 === 0)");
        _switchResult = _builder_1;
      }
    }
    if (!_matched) {
      boolean _isFloat = this.isFloat(typeName);
      if (_isFloat) {
        _matched=true;
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("(typeof ");
        _builder_2.append(name, "");
        _builder_2.append(" === \"number\") && !(");
        _builder_2.append(name, "");
        _builder_2.append(" % 1 === 0)");
        _switchResult = _builder_2;
      }
    }
    if (!_matched) {
      boolean _isBoolean = this.isBoolean(typeName);
      if (_isBoolean) {
        _matched=true;
        StringConcatenation _builder_3 = new StringConcatenation();
        _builder_3.append("(typeof ");
        _builder_3.append(name, "");
        _builder_3.append(" === \"boolean\")");
        _switchResult = _builder_3;
      }
    }
    if (!_matched) {
      StringConcatenation _builder_4 = new StringConcatenation();
      _switchResult = _builder_4;
    }
    return _switchResult;
  }
  
  protected CharSequence _genTypeCheckLowLevel(final Tuple type, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("(");
    _builder.append(name, "");
    _builder.append(".constructor === Array) && (");
    _builder.append(name, "");
    _builder.append(".length === ");
    EList<Type> _elements = type.getElements();
    int _size = _elements.size();
    _builder.append(_size, "");
    _builder.append(")");
    _builder.newLineIfNotEmpty();
    {
      EList<Type> _elements_1 = type.getElements();
      int _size_1 = _elements_1.size();
      boolean _notEquals = (_size_1 != 0);
      if (_notEquals) {
        _builder.append("\t    ");
        _builder.append("&&");
        _builder.newLine();
        _builder.append("\t    ");
        int i = 0;
        _builder.newLineIfNotEmpty();
        {
          EList<Type> _elements_2 = type.getElements();
          boolean _hasElements = false;
          for(final Type t : _elements_2) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(" && ", "\t    ");
            }
            _builder.append("\t    ");
            int _plusPlus = i++;
            String _plus = ((name + "[") + Integer.valueOf(_plusPlus));
            String _plus_1 = (_plus + "]");
            CharSequence _genTypeCheckLowLevel = this.genTypeCheckLowLevel(t, _plus_1);
            _builder.append(_genTypeCheckLowLevel, "\t    ");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t    ");
      }
    }
    return _builder;
  }
  
  public CharSequence genTypeCheck(final Property a) {
    if (a instanceof Aggregate) {
      return _genTypeCheck((Aggregate)a);
    } else if (a instanceof Reference) {
      return _genTypeCheck((Reference)a);
    } else if (a instanceof Attribute) {
      return _genTypeCheck((Attribute)a);
    } else if (a != null) {
      return _genTypeCheck(a);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(a).toString());
    }
  }
  
  public CharSequence genTypeCheckLowLevel(final Type type, final String name) {
    if (type instanceof PrimitiveType) {
      return _genTypeCheckLowLevel((PrimitiveType)type, name);
    } else if (type instanceof Tuple) {
      return _genTypeCheckLowLevel((Tuple)type, name);
    } else if (type != null) {
      return _genTypeCheckLowLevel(type, name);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(type, name).toString());
    }
  }
}
