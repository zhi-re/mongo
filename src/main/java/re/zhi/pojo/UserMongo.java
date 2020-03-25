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
@Document
@CompoundIndexes(@CompoundIndex(name="id_1_name_1_age",def="{'id':1,'name':1,'age':-1}"))
public class UserMongo {

    private String id;
    private String name;
    private Integer age;
    private JSONObject content;

    /**
     * 全文索引字段
     */
    @TextIndexed
    private String text;

}
