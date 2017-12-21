import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

/**
 * Created by Elkamel on 9/25/17.
 */
public aspect IncreaseSpeed
    {
    @Before("execution(* Proxy.increaseSpeed(..))")
    public void logBefore(JoinPoint joinPoint)
        {
        System.out.println("log before Speed increase");
        Context.ER.RecordSpeedIncreasedBefore();
        }

    @After("execution(* Proxy.increaseSpeed(..))")
    public void logAfter(JoinPoint joinPoint)
        {
        System.out.println("log after Speed increase");
        Context.ER.RecordSpeedIncreasedAfter();
        }
    }
