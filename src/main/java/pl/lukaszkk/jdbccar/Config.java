package pl.lukaszkk.jdbccar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class Config {

    @Value("${password}")
    private String password;

    @Bean
    public DataSource getDataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:db2://good:60000/GDPR5TAB");
        dataSourceBuilder.username("gbs1");
        dataSourceBuilder.password(password);
        dataSourceBuilder.driverClassName("com.ibm.db2.jcc.DB2Driver");

        return dataSourceBuilder.build();
    }

    /// jdbc template
    @Bean
    public JdbcTemplate getJdbcTemplate()
    {
        return new JdbcTemplate(getDataSource());
    }



}
