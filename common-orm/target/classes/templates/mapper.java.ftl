
package ${package.Mapper};

import java.util.List;
import ${package.Entity}.${entity};
import ${superMapperClassPackage};
<#if generateKeyXml??>
import org.apache.ibatis.annotations.Param;
</#if>
/**
* ${table.comment} Mapper接口
* @Author ${author}
* @Date ${date}
*/
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {


}
