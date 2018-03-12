package com.scrm.message.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by zhb on 2017/9/29/029.
 */

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = DruidSource.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
@PropertySource("classpath:application.properties")
public class DruidSource {
    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = " com.scrm.message.mapper";
    static final String MAPPER_LOCATION = "classpath:com/scrm/message/mapper/*.xml";

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    @Value("${spring.druid.initialSize}")
    private int initialSize;
    @Value("${spring.druid.minIdle}")
    private int minIdle;
    @Value("${spring.druid.maxActive}")
    private int maxActive;
    @Value("${spring.druid.maxWait}")
    private int maxWait;
    @Value("${spring.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.druid.validationQuery}")
    private String validationQuery;
    @Value("${spring.druid.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.druid.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.druid.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.druid.filters}")
    private String filters;
    @Value("${spring.druid.connectionProperties}")
    private String connectionProperties;

    @Bean     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource()  {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }


    @Bean
    @Primary
    //配置事物管理
    public DataSourceTransactionManager masterTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("dataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DruidSource.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
