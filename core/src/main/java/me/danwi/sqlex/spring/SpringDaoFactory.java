package me.danwi.sqlex.spring;

import me.danwi.sqlex.core.DaoFactory;
import me.danwi.sqlex.core.RepositoryLike;

import javax.sql.DataSource;

public class SpringDaoFactory extends DaoFactory {
    public SpringDaoFactory(DataSource dataSource, Class<? extends RepositoryLike> repository) throws Exception {
        super(new SpringManagedTransactionManager(dataSource), repository);
    }
}
