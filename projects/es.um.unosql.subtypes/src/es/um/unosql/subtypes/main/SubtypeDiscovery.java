package es.um.unosql.subtypes.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

import es.um.unosql.subtypes.discovery.DependencyAnalyzer;
import es.um.unosql.subtypes.discovery.discriminator.DBDiscriminatorSeeker;
import es.um.unosql.subtypes.m2m.UNoSQL2SubtypeUNoSQL;
import es.um.unosql.subtypes.outliers.OutlierDetector;
import es.um.unosql.subtypes.util.SubtypeSerializer;
import es.um.unosql.subtypes.util.configuration.SubtypeDiscoveryConfig;
import es.um.unosql.uNoSQLSchema.SchemaType;
import es.um.unosql.uNoSQLSchema.UNoSQLSchemaPackage;
import es.um.unosql.uNoSQLSchema.uNoSQLSchema;
import es.um.unosql.utils.ModelLoader;
import es.um.unosql.utils.UNoSQLSchemaWriter;

public class SubtypeDiscovery
{
    /**
     * This main function is used to discover subtypes in a given UNoSQLSchema model file.
     *usage: <Subtype discovery>
       -cov,--coverage <value>                         Apply coverage outlier
                                                       detection
       -d,--discriminator <mongodb|neo4j ip db_name>   Discriminator seeker
       -e,--entity <string>                            Entity type to analyze
       -ep,--epsilon <value>                           Apply epsilon outlier
                                                       detection
       -h,--help                                       Prints help
       -i,--input <model>                              Path to the input
                                                       U-Schema model
       -o,--output <folder>                            Path to an output folder
     */
    public static void main(String[] args)
    {
      /*
        String[] args_solar_system =
        {
            "-i", "../es.um.unosql.models/solar_system/solar_system.xmi",
            "-e", "Bodies",
            "-d", "mongodb", "localhost", "solar_system",
            "-o", "solar_system_output/"
        };
    
        String[] args_athletes =
        {
            "-i", "../es.um.unosql.models/athletes/athletes.xmi",
            "-e", "Athletes",
            "-d", "mongodb", "localhost", "athletes"
        };
    
        String[] args_movieontology =
        {
            "-i", "../es.um.unosql.models/movieontology/movieontology.xmi",
            "-e", "Resource"
        };
    
        String[] args_stackoverflow =
        {
            "-i", "../es.um.unosql.models/stackoverflow/stackoverflow.xmi",
            "-e", "Posts",
            "-cov", "99"
        };
       */
      SubtypeDiscoveryConfig config = new SubtypeDiscoveryConfig(args);

      runSubtypeDiscovery(config);
    }

    private static void runSubtypeDiscovery(SubtypeDiscoveryConfig config)
    {
        Path inputPath = config.getInputPath();
        String entityName = config.getEntityName();
        OutlierDetector oDetector = config.getOutlierDetector();
        DBDiscriminatorSeeker dSeeker = config.getDiscriminatorSeeker();
        Path outputPath = config.getOutputPath();
    
        ModelLoader loader = new ModelLoader(UNoSQLSchemaPackage.eINSTANCE);
        uNoSQLSchema schema = loader.load(inputPath.toString(), uNoSQLSchema.class);
        SubtypeSerializer serializer = new SubtypeSerializer();
    
        System.out.println("> Subtype discovery process for " + inputPath + ": " + entityName);
        long initialT = System.currentTimeMillis();
    
        if (oDetector != null)
            oDetector.removeOutliers(schema);
    
        Optional<SchemaType> optSchemaType = Stream
            .concat(schema.getEntities().stream(), schema.getRelationships().stream())
            .filter(sType -> sType.getName().equals(entityName)).findAny();
    
        if (!optSchemaType.isPresent())
            throw new IllegalArgumentException("> Could not find " + entityName + " in " + inputPath.toString());
    
        DependencyAnalyzer depDetector = new DependencyAnalyzer(optSchemaType.get(), dSeeker);
        depDetector.doDependencyAnalysis();
    
        long finalT = System.currentTimeMillis();
    
        StringBuilder result = new StringBuilder();
        result.append(serializer.printPretty(depDetector));
        result.append(serializer.printPretty(depDetector.getSubtypes()));
        result.append(serializer.printPretty(depDetector.getDiscriminatorSeeker()));
    
        System.out.println(result.toString());
    
        if (outputPath != null)
        {
            System.out.println("> Writing results on");
            outputPath.toFile().mkdirs();
            Path outputFilePath = outputPath.resolve(schema.getName() + "_" + entityName + "_subtypes.txt");
      
            try
            {
                PrintWriter writer = new PrintWriter(outputFilePath.toString(), "UTF-8");
                writer.print(result.toString());
                writer.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
      
            if (dSeeker != null)
            {
                Path outputModelPath = outputPath.resolve(schema.getName() + "_hierarchy.xmi");
                uNoSQLSchema newSchema = new UNoSQL2SubtypeUNoSQL().m2m(schema, depDetector);
                new UNoSQLSchemaWriter().write(newSchema, outputModelPath.toFile());
            }
        }
    
        System.out.println("> Subtype discovery process finished in: " + (finalT - initialT) + " ms");
    }
}
