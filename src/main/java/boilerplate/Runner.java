package boilerplate;

import org.seasar.doma.jdbc.SqlExecutionException;
import org.seasar.doma.jdbc.tx.TransactionManager;

import boilerplate.dao.*;
import boilerplate.entity.Sequential;
import boilerplate.query.Node.Parameter;
import boilerplate.query.Node.Statement;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.collections.impl.collector.Collectors2;

public class Runner {

    public static void main(String ... args) {
        System.out.println("Start");
        
        TransactionManager tm = AppConfig.singleton().getTransactionManager();

        AppDao dao = new AppDaoImpl();

        tm.required(() -> {
            dao.create();
        });

/*
        AppDao dao = new AppDaoImpl();

        SequentialDao seqDao = new SequentialDaoImpl();
        Tbl2Dao tbl2Dao = new Tbl2DaoImpl();

        tm.required(() -> {
            dao.create();
        });

        tm.required(() -> {
            seqDao.selectAll(
                    Arrays.asList("02", "03"), 
                    Arrays.asList(Statement.of("id ="), Parameter.of("1", Integer.class))).forEach(rec ->
                System.out.println(String.format("id: %d, dat01: %d, dat02: %d", rec.id, rec.dat01, rec.dat02))
            );

            seqDao.selectAll(
                    Arrays.asList("02", "03"), 
                    Arrays.asList(
                        Statement.of("id ="), Parameter.of(1),
                        Statement.of("AND dat02 BETWEEN"), Parameter.of(211),
                        Statement.of("AND "), Parameter.of(311)
                    )).forEach(rec ->
                System.out.println(String.format("id: %d, dat01: %d, dat02: %d", rec.id, rec.dat01, rec.dat02))
            );

            TblDao tblDao = new TblDaoImpl();
            tblDao.selectRanked().forEach(System.out::println);

            tblDao.temp(Arrays.asList("v1", "v2"));
            tblDao.selectFromTemp().forEach(System.out::println);

            tblDao.over().forEach(System.out::println);
            tblDao.selectList().forEach(System.out::println);
            tblDao.selectSingle().ifPresent(System.out::println);
        });

        tm.required(() -> {
            tbl2Dao.selectAll().forEach(System.out::println);
            tbl2Dao.insert("should be rollbacked");

            tm.requiresNew(() -> {
                tbl2Dao.insert("commit locally");
            });

            tbl2Dao.selectAll().forEach(System.out::println);
            tm.setRollbackOnly();
        });

        tm.required(() -> {
            System.out.println("################ final records. ################");
            tbl2Dao.selectAll().forEach(System.out::println);
        });

        tm.required(() -> {
            dao.drop();
        });
   */
    }

}
