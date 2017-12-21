import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

/**
 * Created by Elkamel on 9/25/17.
 */
public aspect IncreaseASC
    {
    @Before("execution(* Proxy.increaseASC(..))")
    public void logBefore(JoinPoint joinPoint)
        {
        System.out.println("log before ASC increase");
        Context.ER.RecordASCIncreaseBefore();
        }

    @After("execution(* Proxy.increaseASC(..))")
    public void logAfter(JoinPoint joinPoint)
        {
        System.out.println("log after ASC increase");
        Context.ER.RecordASCIncreaseAfter();
        }
    }
