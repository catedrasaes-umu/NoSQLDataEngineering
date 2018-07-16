package es.um.nosql.s13e.db.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EPolDownloader
{
	private final String COUNTRIES_ROUTE = "json/everyPolitician/countries.json";

	private final String DOWNLOAD_ROUTE = "json/everyPolitician/countries/";

	public void start()
	{
		try
		{
			JsonNode array = new ObjectMapper().readTree(new File(COUNTRIES_ROUTE));

			for (JsonNode obj : array)
			{
				String name = obj.get("name").asText();
				JsonNode legislatures = obj.get("legislatures");

				for (JsonNode leg : legislatures)
				{
					String fileName = DOWNLOAD_ROUTE + (name + "_" + leg.get("name").asText() + ".json").replace(" ", "_");
					URL urlFile = new URL(leg.get("popolo_url").asText());
					FileUtils.copyURLToFile(urlFile, new File(fileName));
					System.out.println(fileName + " downloaded");
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		EPolDownloader downloader = new EPolDownloader();
		downloader.start();
	}
}
