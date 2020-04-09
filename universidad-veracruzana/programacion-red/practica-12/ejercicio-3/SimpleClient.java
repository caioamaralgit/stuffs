public class SimpleClient {
    public static void main(String args[]){
        System.out.printl("Client started");

        try{
            Socket socket = new Socket ("127.0.0.1", 5000);
            System.out.println("Conected to a server");
            PrintStream outputStream = new PrintStream(socket.getOutputStream());

            InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(inputStream);

            String partName = "Axie";

            outputStream.println(partName);
            System.out.println(partName + "request sent");

            System.out.println("Response: "+ reader.readLine());
            socket.close();
        }catch (IOException exception){
            exception.printStackTrace();
        }
        System.out.println("client terminated");
    }
}