package re.zhi.pojo;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document // 标注在实体类上，类似于hibernate的entity注解，标明由mongo来维护该表。
@CompoundIndexes(@CompoundIndex(name="id_1_name_1_age",def="{'id':1,'name':1,'age':-1}")) // 复合索引，加复合索引后通过复合索引字段查询将提高速度 1为正序，-1为倒序
public class UserMongo {

    private String id;
    private String name;
    private Integer age;
    private Integer source;
    private JSONObject content;

    /**
     * 全文索引字段
     */
    @TextIndexed
    private String text;

}
