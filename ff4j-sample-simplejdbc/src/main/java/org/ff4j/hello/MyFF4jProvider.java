package org.ff4j.hello;

import org.apache.commons.dbcp2.BasicDataSource;
import org.ff4j.FF4j;
import org.ff4j.audit.repository.JdbcEventRepository;
import org.ff4j.property.store.JdbcPropertyStore;
import org.ff4j.store.JdbcFeatureStore;
import org.ff4j.web.FF4jProvider;

/**
 * Providing FF4j instance (is not Spring bean and autowiring)
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
public class MyFF4jProvider implements FF4jProvider {

    /** Instance of FF4j. */
    private static FF4j ff4j;

    /** {@inheritDoc} */
    public FF4j getFF4j() {
        if (ff4j == null) {
            // Initialize with CORE JDBC
            ff4j = new FF4j("ff4j.xml");
           
            // MySQL DATASOURCE AS A SAMPLE
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/ff4j_v1");
            dataSource.setUsername("root");
            dataSource.setPassword("docu1");
            dataSource.setConnectionProperties("useUnicode=yes;characterEncoding=UTF-8;");

            ff4j.setFeatureStore(new JdbcFeatureStore(dataSource));
            ff4j.setPropertiesStore(new JdbcPropertyStore(dataSource));
            ff4j.setEventRepository(new JdbcEventRepository(dataSource));
            
            ff4j.audit(true);
            ff4j.autoCreate(true);
            ff4j.enable("feature_Y");
            ff4j.enable("feature_X");
            for (int i = 0; i < 10; i++) {
                ff4j.check("feature_X");
                if (i%2 == 0) ff4j.check("feature_Y");
            }
        }
        return ff4j;
    }

}
