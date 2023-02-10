
package ${package.ServiceImpl};

import ${superServiceImplClassPackage};
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* ${table.comment}service实现
* @Author ${author}
* @Date ${date}
*/
@Service
@AllArgsConstructor
@Slf4j
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName}{


}
