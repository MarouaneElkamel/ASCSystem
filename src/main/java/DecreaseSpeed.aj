import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

/**
 * Created by Elkamel on 9/25/17.
 */
public aspect DecreaseSpeed
    {
    @Before("execution(* Proxy.decreaseSpeed(..))")
    public void logBefore(JoinPoint joinPoint)
        {
        System.out.println("log before ASC desactivation");
        Context.ER.RecordASCDesactivationBefore();
        }

    @After("execution(* Proxy.decreaseSpeed(..))")
    public void logAfter(JoinPoint joinPoint)
        {
        System.out.println("log after ASC desactivation");
        Context.ER.RecordASCDesactivationAfter();
        }
    }
