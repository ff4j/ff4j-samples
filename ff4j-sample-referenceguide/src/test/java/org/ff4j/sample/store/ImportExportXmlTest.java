package org.ff4j.sample.store;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import org.ff4j.FF4j;
import org.ff4j.conf.XmlParser;
import org.ff4j.core.Feature;
import org.junit.Test;

public class ImportExportXmlTest {

    @Test
    public void testImport() throws FileNotFoundException {

        // Given
        FF4j ff4j = new FF4j();

        // When
        FileInputStream fis = new FileInputStream(new File("src/test/resources/ff4j.xml"));
        Map<String, Feature> mapsOfFeat = new XmlParser().parseConfigurationFile(fis).getFeatures();
        for (Entry<String, Feature> feature : mapsOfFeat.entrySet()) {
            if (ff4j.exist(feature.getKey())) {
                ff4j.getFeatureStore().update(feature.getValue());
            } else {
                ff4j.getFeatureStore().create(feature.getValue());
            }
        }

        // Then
        assertEquals(2, ff4j.getFeatures().size());
    }
    
    
    @Test
    public void testExport() throws IOException {
        FF4j ff4j = new FF4j("ff4j.xml");
        
        InputStream in = ff4j.exportFeatures();

        // Write into console
        byte[] bbuf = new byte[4096];
        int length = 0;
        while ((in != null) && (length != -1)) {
            length = in.read(bbuf);
        }
        System.out.print(new String(bbuf));
    }
}
