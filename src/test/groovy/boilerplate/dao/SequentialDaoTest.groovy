package boilerplate.dao;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager

import boilerplate.AppConfig;
import boilerplate.Column
import boilerplate.DbResource;
import boilerplate.dao.SequentialDao.SelectInfo
import boilerplate.entity.ComplexUnion

public class SequentialDaoTest {

    @Rule
    public final DbResource dbResource = new DbResource();

    private final SequentialDao dao = new SequentialDaoImpl();

    TransactionManager tm = AppConfig.singleton().getTransactionManager()

    /*
     @Test
     public void testSelectAll() {
     tm.required {
     List<Sequential> recs = dao.selectAll(["02", "03"]);
     for (Sequential rec : recs) {
     println String.format("id: %d, dat01: %d, dat02: %d", rec.id, rec.dat01, rec.dat02);
     }
     }
     }
     */

    @Test
    public void test_complexUnion() {
        tm.required {
            ComplexUnion[] recs = dao.complexUnion(
                    ['01', '02', '03'],
                    [
                        new Column("dat01"),
                        new Column("dat02")
                    ],
                    ['101', '102'],
                    [
                        new Column("CAST(dat01 as int)", "dat01"),
                        new Column("CAST(dat02 as int)", "dat02")
                    ]);

            recs.collect {
                [
                    dat01: it.dat01,
                    dat02: it.dat02
                ]
            }.each { println it }
        }
    }

    @Test
    public void test_complexUnion2() {
        tm.required {
            ComplexUnion[] recs = dao.complexUnion2(
                    [
                        new SelectInfo(
                        ['01', '02', '03'],
                        [
                            new Column("dat01"),
                            new Column("dat02")
                        ]),
                        new SelectInfo(
                        ['101', '102'],
                        [
                            new Column("CAST(dat01 as int)", "dat01"),
                            new Column("CAST(dat02 as int)", "dat02")
                        ])
                    ]);

            recs.collect {
                [
                    dat01: it.dat01,
                    dat02: it.dat02
                ]
            }.each { println it
            } }
    }
}
