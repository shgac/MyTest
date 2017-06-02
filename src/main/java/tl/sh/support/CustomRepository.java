package tl.sh.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by root on 2017/4/16.
 */
@NoRepositoryBean
//@NoRepositoryBean指明当前这个接口不是我们领域类的接口（如 PersonRepository）
public interface CustomRepository<T, ID extends Serializable> extends JpaRepository<T,ID>,JpaSpecificationExecutor<T>{
    //接口继承JpaRepository，具备了JpaRepository所提供的方法
    Page<T> findByAuto(T example, Pageable pageable);
}
