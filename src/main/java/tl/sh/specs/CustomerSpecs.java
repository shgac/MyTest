package tl.sh.specs;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Iterables.toArray;

/**
 * Created by root on 2017/4/16.
 */
public class CustomerSpecs {
    public static <T> Specification<T> byAuto (final EntityManager entityManager,final T example){
        //定义一个返回值为Specification的方法。泛型T ，所以这个Specification可以用于任意的实体类
        final Class<T> type=(Class<T>) example.getClass();
        //获得当前实体类对象类的类型
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
               List<Predicate> predicates=new ArrayList<>();//新建Predica列表存储构造的查询条件
               EntityType<T> entity = entityManager.getMetamodel().entity(type);
               //获得实体类的EntityType，可以从中获得实体类的属性
               for(Attribute<T,?> attr: entity.getDeclaredAttributes()){//对实体类的所有属性做循环
                   Object attrValue = getValue(example,attr);
                   if(attrValue!=null){
                       if(attr.getJavaType()==String.class){//当前属性为字符类型的时候
                           if(!StringUtils.isEmpty(attrValue)){//当前字符不为空的时候
                               predicates.add(criteriaBuilder.like(root.get(attribute(entity,attr.getName(),String.class)),pattern((String)attrValue)));
                               //构造当前属性like（前后%）属性值查找条件，并添加到条件列表
                           }else {
                               predicates.add(criteriaBuilder.equal(root.get(attribute(entity,attr.getName(),attrValue.getClass())),attrValue));
                               //构造当前属性和属性值查找条件，并添加到条件列表
                           }
                       }
                   }
               }
                return predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(toArray(predicates,Predicate.class));
               //将条件列表转换成Predicate
            }

            private String pattern(String attrValue) {//构造like的查询模式，就是前后加%
                return "%"+attrValue+"%";
            }

            private <E,T> SingularAttribute<T, E> attribute(EntityType<T> entity, String name, Class<E> stringClass) {
                //获得实体类的当前属性的SingularAttribute，SingularAttribute包含的是实体类的某个单独属性
                return entity.getDeclaredSingularAttribute(name,stringClass);

            }

            private <T> Object getValue(T example, Attribute<T, ?> attr) {

                //通过反射获得实体类对象对应属性的属性值
                return org.springframework.util.ReflectionUtils.getField((Field)attr.getJavaMember(),example);
            }


        };
    }

   /* private static <T> Object getValue(T example, Attribute<T, ?> attr) {
        return org.springframework.util.ReflectionUtils.getField((Field) attr.getJavaMember(),example);
    }*/
}
