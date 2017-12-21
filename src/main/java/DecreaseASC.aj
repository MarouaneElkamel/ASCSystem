import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

/**
 * Created by Elkamel on 9/25/17.
 */
public aspect DecreaseASC
    {
    @Before("execution(* Proxy.decreaseASC(..))")
    public void logBefore(JoinPoint joinPoint)
        {
        System.out.println("log before ASC decrease");
        Context.ER.RecordASCDecreaseBefore();
        }

    @After("execution(* Proxy.decreaseASC(..))")
    public void logAfter(JoinPoint joinPoint)
        {
        System.out.println("log after ASC decrease");
        Context.ER.RecordASCDecreaseAfter();
        }
    }
