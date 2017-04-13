package boilerplate.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.*;
import java.util.concurrent.atomic.AtomicReference;

import org.seasar.doma.*;

import boilerplate.AppConfig;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.collector.Collectors2;

@Dao(config = AppConfig.class)
public interface TblDao {

    @Select
    List<Map<String, Object>> selectRanked();

    @Insert(sqlFile = true)
    int temp(List<String> columns);

    @Select
    List<Map<String, Object>> selectFromTemp();

    @Select
    List<Map<String, Object>> over();

    @Select(strategy = SelectType.COLLECT, ensureResult = true)
    <RESULT> RESULT selectCollect(Collector<Map<String, Object>, ?, RESULT> collector);

    default ImmutableList<Map<String, Object>> selectList() {
        return selectCollect(Collectors2.toImmutableList());
    }

    default Optional<Map<String, Object>> selectSingle() {
        return selectCollect(toOptional());
    }

    public static <T> Collector<T, ?, Optional<T>> toOptional() {
        return Collector.<T, AtomicReference<T>, Optional<T>>of(
            AtomicReference::new,
            (acc, t) -> acc.compareAndSet(null, t),
            (a, b)   -> {a.compareAndSet(null, b.get()); return a;},
            acc      -> Optional.ofNullable(acc.get()),
            Collector.Characteristics.CONCURRENT
        );
    }

}
