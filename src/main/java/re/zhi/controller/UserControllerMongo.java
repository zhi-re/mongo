package re.zhi.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import re.zhi.pojo.UserMongo;
import re.zhi.service.IUser;

import java.util.*;

/**
 * @Author: chenqi
 * @Date: 2020.3.25 18:03
 */
@RestController
@RequestMapping("/test")
public class UserControllerMongo {

    @Autowired
    private IUser iUser;

    /**
     * 插入
     *
     * @param map
     * @return
     */
    @RequestMapping("insert")
    public String insert(@RequestBody() Map map) {
        iUser.insert(map);
        return JSON.toJSONString(map);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping("findAll")
    public List findAll() {
        List<UserMongo> list = iUser.findAll();
        return list;
    }

    /**
     * 按条件查询
     *
     * @param map
     * @return
     */
    @RequestMapping("findByConditions")
    public List findByConditions(@RequestBody() Map map) {
        List<UserMongo> list = iUser.findByConditions(map);
        return list;
    }

    /**
     * 文本分词查询
     *
     * @param map
     * @return
     */
    @RequestMapping("textSearch")
    public List textSearch(@RequestBody() Map map) {
        List<UserMongo> list = iUser.textSearch(map);
        return list;
    }


    /**
     * 更新
     *
     * @param map
     * @return
     */
    @RequestMapping("update")
    public boolean update(@RequestBody() Map map) {
        boolean b = iUser.update(map);
        return b;
    }

    /**
     * 删除
     *
     * @param map
     * @return
     */
    @RequestMapping("delete")
    public boolean delete(@RequestBody() Map map) {
        boolean b = iUser.delete(map);
        return b;
    }

    /**
     * 聚合查询
     *
     * @param map
     * @return
     */
    @RequestMapping("findByAggregation")
    public String findByAggregation(@RequestBody() Map map) {
        iUser.findByAggregation(map);
        return "OK";
    }
}
