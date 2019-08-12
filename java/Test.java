class Test {

    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        char[] location = new char[100];
        int count = 100;
        ControlThread threadA = new ControlThread(true, 'A', location, count, a, c);
        ControlThread threadB = new ControlThread(false, 'B', location, count, b, a);
        ControlThread threadC = new ControlThread(false, 'C', location, count, c, b);
        new Thread(threadA).start();
        new Thread(threadB).start();
        new Thread(threadC).start();
    }

}

class ControlThread implements Runnable{
    
    private char[] location;
    private char character;
    private int count;
    private Object current;
    private Object prev;
    private boolean isControl;

    public ControlThread(boolean isControl, char character, char[] location, int count, Object current, Object prev){
        this.isControl = isControl;
        this.character = character;
        this.location = location;
        this.count = count;
        this.current = current;
        this.prev = prev;
    }



    @Override
    public void run() {
        while(count > 0){
            synchronized(this.prev){
                synchronized(this.current){
                    System.out.println(this.character);
                    count--;
                    current.notifyAll();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
            // try {
            //     Thread.sleep(10);
            // } catch (InterruptedException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            // }
            // try {
            //     this.wait();
            // } catch (InterruptedException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            // }
        }
        
        
    }
    
}

class SubThread implements Runnable{

    private char[] location;
    private char character;
    private Object current;
    private Object prev;
    public SubThread(char character, char[] location, Object current, Object prev){
        this.character = character;
        this.location = location;
        this.current = current;
        this.prev = prev;
    }
    @Override
    public void run() {
        synchronized(this){
            synchronized(this.prev){
                System.out.println(this.character);
            }
        }
    }

}
