import org.junit.Test;
import singleton.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by Alex Veng on 03.04.2016.
 */
public class SingletonTest {

    @Test
    public void notLazySingletonTest() {
        NotLazySingleton singletonFirst = NotLazySingleton.getInstance();
        NotLazySingleton singletonSecond = NotLazySingleton.getInstance();

        assertNotNull(singletonFirst);
        assertNotNull(singletonSecond);
        assertTrue(singletonFirst instanceof NotLazySingleton);
        assertTrue(singletonFirst == singletonSecond);
    }

    @Test
    public void staticBlockSingletonTest() {
        StaticBlockSingleton singletonFirst = StaticBlockSingleton.getInstance();
        StaticBlockSingleton singletonSecond = StaticBlockSingleton.getInstance();

        assertNotNull(singletonFirst);
        assertNotNull(singletonSecond);
        assertTrue(singletonFirst instanceof StaticBlockSingleton);
        assertTrue(singletonFirst == singletonSecond);
    }

    @Test
    public void lazySingletonTest() {
        LazySingleton singletonFirst = LazySingleton.getInstance();
        LazySingleton singletonSecond = null;

        Callable<LazySingleton> callable = LazySingleton::getInstance;
        try {
            singletonSecond = ForkJoinPool
                    .commonPool()
                    .submit(callable)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assertNotNull(singletonFirst);
        assertNotNull(singletonSecond);
        assertTrue(singletonFirst instanceof LazySingleton);
        assertTrue(singletonFirst == singletonSecond);
    }

    @Test
    public void InnerClassSingletonTest() {
        InnerClassSingleton singletonFirst = InnerClassSingleton.getInstance();
        InnerClassSingleton singletonSecond = InnerClassSingleton.getInstance();

        assertNotNull(singletonFirst);
        assertNotNull(singletonSecond);
        assertTrue(singletonFirst instanceof InnerClassSingleton);
        assertTrue(singletonFirst == singletonSecond);
    }

    @Test
    public void destroySingletonWithReflection() {
        InnerClassSingleton singletonFirst = InnerClassSingleton.getInstance();
        InnerClassSingleton singletonSecond = null;

        Constructor[] constructors = InnerClassSingleton.class.getDeclaredConstructors();
        constructors[0].setAccessible(true);
        try {
            singletonSecond = (InnerClassSingleton) constructors[0].newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        assertNotNull(singletonFirst);
        assertNotNull(singletonSecond);
        assertTrue(singletonFirst instanceof InnerClassSingleton);
        assertFalse(singletonFirst == singletonSecond);
    }

    @Test
    public void enumSingleton() {
        EnumSingleton singletonFirst = EnumSingleton.INSTANCE;
        EnumSingleton singletonSecond = EnumSingleton.INSTANCE;


        assertNotNull(singletonFirst);
        assertNotNull(singletonSecond);
        assertTrue(singletonFirst instanceof EnumSingleton);
        assertTrue(singletonFirst == singletonSecond);
    }

}
