package boilerplate.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.*;
import java.util.concurrent.atomic.AtomicReference;

import org.seasar.doma.*;

import boilerplate.AppConfig;


@Dao(config = AppConfig.class)
public interface Tbl2Dao {

    @Select
    List<Map<String, Object>> selectAll();

    @Insert(sqlFile = true)
    int insert(String name);

}
