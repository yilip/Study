package lip.thread;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lip on 2016-11-28 18:27
 */
public class FixedSingleThread {
    public static void main(String[] args) {
        final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd hh;MM;ss");
        ScheduledExecutorService service= Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000l);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"-"+dateFormat.format(new Date()));
            }
        }, 1l,2l, TimeUnit.SECONDS);
    }
}
