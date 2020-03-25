package re.zhi.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.WriteResult;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import re.zhi.pojo.UserMongo;


import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenqi
 * @Date: 2020.3.25 18:04
 */
@Service
public class IUserServiceImpl implements IUser {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Resource
    private IndexAnalysis indexAnalysis;
    @Autowired
    private ToAnalysis toAnalysis;

    /**
     * 先分词，再插入操作
     *
     * @param map
     */
    @Override
    public void insert(Map map) {
        String id = map.get("id").toString();
        String name = map.get("name").toString();
        Integer age = (Integer) map.get("age");
        Map contentMap = (Map) map.get("content");
        String jsonString = JSON.toJSONString(contentMap);
        JSONObject jsonObject = JSON.parseObject(jsonString);
        UserMongo user = new UserMongo();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        user.setContent(jsonObject);
        setSearchText(user);
        mongoTemplate.insert(user);
    }

    /**
     * 查询全部
     *
     * @return
     */
    @Override
    public List<UserMongo> findAll() {
        return mongoTemplate.findAll(UserMongo.class);
    }

    /**
     * 按条件查询
     *
     * @param map
     * @return
     */
    @Override
    public List<UserMongo> findByConditions(Map map) {

        // 单个查询
        // Query query=new Query(Criteria.where("name").is(name));
        // User user =  mongoTemplate.findOne(query , User.class);
        // return user;

        Query query = new Query();
        query.skip(1); // 页数
        query.limit(2); // 每页显示条数
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        query.with(sort);
        query.addCriteria(Criteria.where("name").is(map.get("name").toString()));
        List<UserMongo> list = mongoTemplate.find(query, UserMongo.class);
        return list;
    }

    /**
     * 文本分词查询
     *
     * @param map
     * @return
     */
    @Override
    public List<UserMongo> textSearch(Map map) {
        Query query = new Query();
        String analysisedContent = toAnalysis.parseStr(map.get("content").toString()).toStringWithOutNature(" ");
        System.out.println(analysisedContent);
        query.addCriteria((new TextCriteria()).matching(analysisedContent));
        List<UserMongo> userMongoList = mongoTemplate.find(query, UserMongo.class, "userMongo");
        System.out.println(userMongoList);
        return userMongoList;
    }

    /**
     * 更新
     *
     * @param map
     * @return
     */
    @Override
    public boolean update(Map map) {
        Query query = new Query(Criteria.where("id").is(map.get("id")));
        Update update = new Update().set("name", map.get("name")).set("age", map.get("age"));

        // 更新查询返回结果集的第一条
        // mongoTemplate.updateFirst(query, update, UserMongo.class);

        // 更新查询返回结果集的所有
        mongoTemplate.updateMulti(query, update, UserMongo.class);
        return true;

    }

    /**
     * 删除
     *
     * @param map
     * @return
     */
    @Override
    public boolean delete(Map map) {
        Query query = new Query(Criteria.where("id").is(map.get("id")));
        mongoTemplate.remove(query, UserMongo.class);
        return true;

    }

    /**
     * 设置全文索引内容
     */
    private void setSearchText(UserMongo userMongo) {
        String content = userMongo.getContent().getString("content");
        String analysisedContent = indexAnalysis.parseStr(content).toStringWithOutNature(" ");
        System.out.println(analysisedContent);
        userMongo.setText(analysisedContent);
    }

}
