public class SimpleMultiThreadedPCServer implements Runnable{
    private static ConcurrentHashMap<String, Float> map;
    private Socket clientSocket;

    static{
        map = new ConcurrentHashMap<>();
        map.put("Axle", 238.57f);
        map.put("Gear", 45.57f);
        map.put("Wheel", 23.57f);
        map.put("Rotor", 56.57f);
    }

    public SimpleMultiThreadedPCServer(socket socket){
        this.clientSocket = socket;
    }

    @Override
    public void run(){
        System.out.println("Client thread started");
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            PrintStream outputStream = new PrintStream(clientSocket.getOutPutStream());

            while(true){
                String partName = buffer.readLine();
                if("quit".equalsIgnoreCase(partName)){
                    break;
                }
                float price = map.get(partName);
                outputStream.println(price);
                NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
                System.out.println("Request for:" + partName + " and returned a price of "+ numberFormat.format(price));
            }
            clientSocket.close();
            System.out.println("Client connection terminated");        
        
        } catch(IOException ex){
            Logger.getLogger(SimpleMultiThreadedPCServer.class.getName()).log(Level.SERVE, null, ex);
        }
        System.out.println("Client terminated");
    }

    public static void main(String[] args){
        System.out.println("Multi-Threaded Per Conection server started");
        try{
            ServerSocket serserverSocket = new ServerSocket(5000);
                while(true){
                    System.out.println("Listening for a client connection");
                    Socket socket = serverSocket.accept();
                    System.out.println("Connected to a client");
                    new Thread(new SimpleMultiThreadedPCServer(socket)).start();
                }
        } catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Multi-Threaded server terminated");
    }
}