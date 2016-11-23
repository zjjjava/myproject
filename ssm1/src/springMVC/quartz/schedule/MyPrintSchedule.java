package springMVC.quartz.schedule;

/**   
 * @author Zhang,Tianyou   
 * @version：2014-12-11 下午12:00:24   
 *  
 *  一个测试的打印定时任务 
 */  
  
public class MyPrintSchedule {  
	public static int TIMES = 0;
	
    public void printSomething(){  
        //内容就是打印一句话  
        System.out.println("定时任务执行了"+(++TIMES)+"次");  
    }  
}  
