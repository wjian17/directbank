package com.qhh.bank;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = DirectbankDBApplication.class)
@RunWith(SpringRunner.class)
public class AppTest
{

    @Resource(name = "dataSource")
    private DataSource dataSource;

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        try {
            dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertTrue( true );
    }
}
