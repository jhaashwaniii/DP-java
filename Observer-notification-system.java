
import java.util.*;

interface NotificationObserver{
    void update(String data);
}
interface NotificationSubject{
    void addObserver(NotificationObserver no);
    void removeObserver(NotificationObserver ro);
    void notifyObservers(String msgs);
}

class EmailNotification implements NotificationObserver{
    
    public void update(String data)
    {
        System.out.println("Email Notification Sent"+ data);
    }
}
class SMSNotification implements NotificationObserver{
    public void update(String data)
    {
        System.out.println("SMS Notification Sent"+ data);
    }
}

class NotificationService implements NotificationSubject{
    private List<NotificationObserver> obs=new ArrayList<>();
    
    public void addObserver(NotificationObserver no)
    {
        obs.add(no);
    }
    public void removeObserver(NotificationObserver no)
    {
        obs.remove(no);
    }
    
    public void notifyObservers(String data)
    {
        for(NotificationObserver no: obs)
        {
            no.update(data);
        }
    }
    
    public void update(String data)
    {
        notifyObservers(data);
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println("Try Observer Pattern");
        
        NotificationService ns=new NotificationService();
        
        NotificationObserver no1=new EmailNotification();
        NotificationObserver no2=new SMSNotification();
        
        ns.addObserver(no1);
        ns.addObserver(no2);
        
        ns.update("Police has arrested the criminal");
        
        
    }
}
