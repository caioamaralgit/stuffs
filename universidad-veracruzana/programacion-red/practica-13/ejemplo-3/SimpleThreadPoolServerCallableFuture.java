public class SimpleThreadPoolServerCallableFuture{
    public static void main(String args[]) {
        System.out.println("Thread pool Callable Future server started");

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        try{
            ServerSocket serverSocket = new ServerSocket(5000);
            while(true){
                System.ou.println("Listening for a client connection");
                Socket socket = serverSocket.accept();
                System.out.println("Connected to a client");
                WorkerThreadCallableFuture task = new WorkerThreadCallableFuture(socket);

                System.out.println("Task Created: " + task);
                executor.execute(task);
            } 
        } catch(Exception e){
            e.printStackTrace();
        } 
        executor.shutdown();
        System.out.println("Thread pool server terminated");
    }
}