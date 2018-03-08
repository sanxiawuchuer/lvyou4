package com.js.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.js.constant.ResponseCode;
import com.js.manage.mapper.base.mapper.SysMapper;
import com.js.manage.pojo.orderserver.OrderServer;

public abstract class BaseService<T> {

    @Autowired
    private SysMapper<T> sysMapper;
    
    //传统写法，Spring4以下版本
//    public abstract SysMapper<T> getSysMapper();
    public Map createMessage(ResponseCode responseCode) {
  	  return createMessage(responseCode,"");
    }

  public Map createMessage(ResponseCode responseCode, String msg) {
  	Map map=Maps.newHashMap();
  	map.put("code",responseCode.getCode());
  	if(StringUtils.isEmpty(msg)) {
  		map.put("msg",responseCode.getDesc());
  	}else {
  		map.put("msg", msg);
  	}
  	return map;
  }
  public Map<String, Object> convertToMap(T t, String[] showPropertyArray) {
      return this.convertToMap(t, showPropertyArray, (Integer)null);
  }

  public List<Map<String, Object>> convertToMapList(List<T> list, String[] showPropertyArray) {
      return this.convertToMapList(list, showPropertyArray, (Integer)null);
  }
  public List<Map<String, Object>> convertToMapList(List<T> list, String[] showPropertyArray, Integer numberDegree) {
      List<Map<String, Object>> resultList = new ArrayList();
      if (list != null && list.size() > 0) {
          Iterator var5 = list.iterator();

          while(var5.hasNext()) {
              T t = (T)var5.next();
              resultList.add(this.convertToMap(t, showPropertyArray, numberDegree));
          }
      }

      return resultList;
  }
  public Map<String, Object> convertToMap(T t, String[] showPropertyArray, Integer numberDegree) {
      Map<String, Object> oneAdd = new HashMap();
      boolean formatNum = numberDegree != null;
      String[] var6 = showPropertyArray;
      int var7 = showPropertyArray.length;

      for(int var8 = 0; var8 < var7; ++var8) {
          String property = var6[var8];
          Object value = EntityUtil.getTer(t, property);
          if (!formatNum || value == null || !(value instanceof Double) && !(value instanceof Float)) {
              oneAdd.put(property, StringUtils.ifNullReturnEmpty(value));
          } else {
              oneAdd.put(property, NumberUtils.numberFormat(new Double(value.toString()), numberDegree));
          }
      }

      return oneAdd;
  }
  
  /**
   * 输出 成功数组 分页数组
   *
   * @param map
   * @return
   */
  public Map createMessageList(List<Map<String, Object>> map, int isLastpage) {
      Map m = createMessage(ResponseCode.SUCCESS);
      if (map == null)
          m.put("list", new ArrayList<Map<String, Object>>());
      else
          m.put("list", map);
      m.put("isLastPage", isLastpage);
      return m;
  }
    /**
     * 根据主键查询数据
     * 
     * @param id
     * @return
     */
    public T queryById(Object id) {
        return this.sysMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据条件查询，多条件之间是 and 关系
     * 
     * @param t
     * @return
     */
    public List<T> queryListByWhere(T t) {
        return this.sysMapper.select(t);
    }

    /**
     * 根据条件查询单条数据
     * 
     * @param t
     * @return
     */
    public T queryByWhere(T t) {
        List<T> list = queryListByWhere(t);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 查询所有数据
     * 
     * @return
     */
    public List<T> queryAll() {
        return this.sysMapper.select(null);
    }

    /**
     * 新增数据，使用全部字段
     * 
     * @param t
     */
    public void save(T t) {
        this.sysMapper.insert(t);
    }

    /**
     * 新增数据，使用不为null的字段
     * 
     * @param t
     */
    public void saveSelective(T t) {
        this.sysMapper.insertSelective(t);
    }

    /**
     * 根据id删除
     * 
     * @param id
     * @return
     */
    public Integer deleteById(Object id) {
        return this.sysMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据ids删除
     * 
     * @param ids
     * @return
     */
    public Integer deleteByIds(Object[] ids) {
        return this.sysMapper.deleteByIDS(ids);
    }

    /**
     * 根据条件删除
     * 
     * @param t
     */
    public Integer deleteByWhere(T t) {
        return this.sysMapper.delete(t);
    }

    /**
     * 根据主键id更新数据
     * 
     * @param t
     */
    public Integer update(T t) {
        return this.sysMapper.updateByPrimaryKey(t);
    }
    
    /**
     * 根据主键id更新数据
     * 
     * @param t
     */
    public Integer updateSelective(T t) {
        return this.sysMapper.updateByPrimaryKeySelective(t);
    }

}
