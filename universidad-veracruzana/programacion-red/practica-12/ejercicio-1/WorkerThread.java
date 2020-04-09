class WorkerThread implements Runnable {
	private static final ConcurrentHashMap<String, Float> map;

    static{
        map = new ConcurrentHashMap<>();
        map.put("Axle", 238.57f);
        map.put("Gear", 45.57f);
        map.put("Wheel", 23.57f);
        map.put("Rotor", 56.57f);
    }

    public WorkerThread(Socket socket){
        this.clientSocket=socket;
    }    

    @override
    public void run(){
        System.ou.println("Worker thread started");
        try(BufferedReader buffer = new BufferedReader(new InpuntStreamReader(clientSocket.getInputStream()))){
            PrintStream outputStream  = new PrintStream (clientSocket.getOutputStream);
            String partName = buffer.readLine();
            float price = map.get(partName);

            outputStream.println(price);
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
            System.out.println("Request for " + partName + " and returned a price of " + numberFormat.format(price));

            clientSocket.close();
            System.out.println("Client connection terminated");
        } catch(IOException ex) {
            Logger.getLogger(SimpleMultiThreaderServer.class.getName()).log(Level.SERVE, null, ex);
        }
        System.out.println("Worker Thread Terminated");
    }
}