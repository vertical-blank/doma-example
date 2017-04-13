package boilerplate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import javax.sql.DataSource;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.SqlProcessor;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.PreparedSql;
import org.seasar.doma.jdbc.builder.SelectBuilder;

import boilerplate.AppConfig;
import boilerplate.Column;
import boilerplate.entity.ComplexUnion;
import boilerplate.entity.Sequential;
import boilerplate.query.Node;

@Dao(config = AppConfig.class)
public interface SequentialDao {

    @Select
    List<Sequential> selectAll(List<String> suffixes, List<Node> nodes);

    default List<Sequential> selectAll(List<String> suffixes) {
        return selectAll(suffixes, Collections.emptyList());
    }

    @SqlProcessor
    <R> R selectAll(List<String> suffixes, List<Node> nodes, BiFunction<Config, PreparedSql, R> handler);

    default List<Sequential> selectProc(List<String> suffixes, List<Node> nodes) {
        return this.<List<Sequential>>selectAll(suffixes, nodes, (conf, preparedSql) -> {
            return SelectBuilder.newInstance(conf)
                .sql(preparedSql.getFormattedSql())
                .getEntityResultList(Sequential.class);
        });
    }

    @Select
    List<ComplexUnion> complexUnion(List<String> table1suffixes,
            List<Column> table1cols, List<String> table2suffixes,
            List<Column> table2cols);

    @Select
    List<ComplexUnion> complexUnion2(List<SelectInfo> selectInfos);

    public static class SelectInfo {
        public final List<String> suffixes;
        public final List<Column> cols;

        public SelectInfo(List<String> suffixes, List<Column> cols) {
            this.suffixes = suffixes;
            this.cols = cols;
        }
    }

}
