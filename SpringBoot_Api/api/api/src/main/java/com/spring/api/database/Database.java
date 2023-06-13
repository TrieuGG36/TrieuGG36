package com.spring.api.database;

import com.spring.api.model.Product;
import com.spring.api.reposirory.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
docker run -d --rm --name mysql-spring-boot \
-e MYSQL_ROOT_PASSWORD=Trieu050402. \
-e MYSQL_USER=trieuldh \
-e MYSQL_PASSWORD=Trieu050402. \
-e MYSQL_DATABASE=test_db \
-p 3309:3306 \
-- volume mysql-spring-boot-volume:/var/lib/mysql \
mysql:latest

mysql -h localhost -P 3309 --protocol=tcp -u trieuldh -p


* */


@Configuration
public class Database {
    //loger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Product productA = new Product("Macbook pro 16 inch", 2020, 2400.0, "");
//                Product productB = new Product("iPad air green", 2021, 599.0, "");
//                logger.info("insert data:" +repository.save(productA));
//                logger.info("insert data:" + repository.save(productB));
            }
        };
    }
}
