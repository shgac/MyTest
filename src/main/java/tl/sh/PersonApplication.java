package tl.sh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tl.sh.dao.PersonReposirory;
import tl.sh.support.CustomRepositoryFactoryBean;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class PersonApplication {
	@Autowired
	PersonReposirory personReposirory;

	public static void main(String[] args) {
		SpringApplication.run(PersonApplication.class, args);
	}
}
