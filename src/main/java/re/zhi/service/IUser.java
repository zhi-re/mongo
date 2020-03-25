package re.zhi.service;

import org.springframework.stereotype.Service;
import re.zhi.pojo.UserMongo;

import java.util.List;
import java.util.Map;

/**
 * @Author: chenqi
 * @Date: 2020.3.25 18:04
 */

public interface IUser {
    void insert(Map map);

    List<UserMongo> findAll();

    List<UserMongo> findByConditions(Map map);

    List<UserMongo> textSearch(Map map);

    boolean update(Map map);

    boolean delete(Map map);
}
