package com.redis.repository;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.redis.core.Utils;
import com.redis.repository.StringKVCrudRepositoryImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-redis-context.xml" })
public class StringKVCrudRepositoryImplTestIT {

    @Inject
    private StringKVCrudRepositoryImpl crudRepositoryImpl;

    private final String OK = "OK";

    @Test
    public void testSaveUpdate() {
        String status = crudRepositoryImpl.saveUpdate("919971798501", "1");
        assertTrue(status != null);
        assertTrue(status.equals(OK));
    }

    @Test
    public void testSaveUpdateWithExpireTime() {
        String status = crudRepositoryImpl.saveUpdate("919971798502", "1", Utils.getMilliSeconds(5, TimeUnit.MINUTES));
        assertTrue(status != null);
        assertTrue(status.equals(OK));
    }

    @Test
    public void testUpdate() {
        // add
        System.out.println(new Date());
        String status = crudRepositoryImpl.saveUpdate("919971798508", "1", Utils.getMilliSeconds(15, TimeUnit.SECONDS));
        assertTrue(status != null);
        assertTrue(status.equals(OK));
        // update
        String newValue = crudRepositoryImpl.getValue("919971798508");
        assertTrue(newValue != null);
        assertTrue(newValue.equals("1"));
        long newIncrmntValue = crudRepositoryImpl.increment("919971798508", 1);
        assertTrue(newIncrmntValue == 2);
        // get new value
        newValue = crudRepositoryImpl.getValue("919971798508");
        assertTrue(newValue != null);
        assertTrue(newValue.equals("2"));
        // check after 10 seconds
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        newValue = crudRepositoryImpl.getValue("919971798508");
        assertTrue(newValue == null);
        // check after 15 seconds
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        newValue = crudRepositoryImpl.getValue("919971798508");
        assertTrue(newValue == null);

    }

    @Test
    public void testDelete() {
        // add
        String status = crudRepositoryImpl.saveUpdate("919971798502", "1", Utils.getMilliSeconds(5, TimeUnit.MINUTES));
        assertTrue(status != null);
        assertTrue(status.equals(OK));
        // delete
        long del = crudRepositoryImpl.delete("919971798502");
        assertTrue(del == 1);
        // again delete
        del = crudRepositoryImpl.delete("919971798502");
        assertTrue(del == 0);
    }

    @Test
    public void testGetValue() {
        // add
        String status = crudRepositoryImpl.saveUpdate("919971798502", "1", Utils.getMilliSeconds(5, TimeUnit.MINUTES));
        assertTrue(status != null);
        assertTrue(status.equals(OK));
        // get saved value
        String value = crudRepositoryImpl.getValue("919971798502");
        assertTrue(value != null);
        assertTrue(value.equals("1"));
    }

    @Test
    public void testGetValueAfterExpire() {
        // add
        String status = crudRepositoryImpl.saveUpdate("919971798503", "1", Utils.getMilliSeconds(5, TimeUnit.SECONDS));
        assertTrue(status != null);
        assertTrue(status.equals(OK));
        // get saved value after 5 seconds
        try {
            Thread.sleep(6 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String value = crudRepositoryImpl.getValue("919971798503");
        assertTrue(value == null);
    }

    @Test
    public void testIncrement() {
        // add
        String status = crudRepositoryImpl.saveUpdate("919971798502", "1", Utils.getMilliSeconds(5, TimeUnit.MINUTES));
        assertTrue(status != null);
        assertTrue(status.equals(OK));
        // increment saved value
        long newValue = crudRepositoryImpl.increment("919971798502", 1);
        assertTrue(newValue == 2);
    }

    @Test
    public void testDecrement() {
        // add
        String status = crudRepositoryImpl.saveUpdate("919971798502", "1", Utils.getMilliSeconds(5, TimeUnit.MINUTES));
        assertTrue(status != null);
        assertTrue(status.equals(OK));
        // decrement value
        long newValue = crudRepositoryImpl.decrement("919971798502", 1);
        assertTrue(newValue == 0);
    }

}
