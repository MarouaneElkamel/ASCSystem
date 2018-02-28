/**
 * Created by Elkamel on 9/25/17.
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

public aspect ASC
    {
    @Before("execution(* Proxy.ASC(..))")
    public void logBefore(JoinPoint joinPoint)
        {
        System.out.println(" log before ASC activation");
        if (Context.getMyController().plusMinusTile.getDescription().equals("System ASC Inactive") && 
        Context.getMyController().speedGauge.getValue() > 0)
            {
            Context.ER.RecordASCActivationBefore();
            }
        }

    @After("execution(* Proxy.ASC(..))")
    public void logAfter(JoinPoint joinPoint)
        {
        System.out.println(" log After ASC activation");
        Context.ER.RecordASCActivationAfter();
        }
    }
