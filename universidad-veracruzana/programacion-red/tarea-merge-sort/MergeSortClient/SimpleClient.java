import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String args[]){
        System.out.println("Client started");
        
        try{
            Socket socket = new Socket ("192.168.1.114", 5000);
            System.out.println("Conected to a server");

            PrintStream outputStream = new PrintStream(socket.getOutputStream());
            InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(inputStream);

            System.out.println("Waiting array");

            String response = reader.readLine();

            System.out.println("Array received!");

            String[] rawArray = response.split(",");

            int[] newArray = new int[rawArray.length];

            for (int i = 0; i < rawArray.length; i++) {
                newArray[i] = Integer.parseInt(rawArray[i]);
            }

            System.out.println("Sorting array...");

            sort(newArray, 0, newArray.length - 1);

            System.out.println("Array sorted!");

            StringBuilder sortedResponse = new StringBuilder();

            for (int i = 0; i < newArray.length; i++) {
                if (i > 0) sortedResponse.append(",");
                sortedResponse.append(newArray[i]);
            } 

            outputStream.println(sortedResponse.toString());

            System.out.println("Array sent!");

            socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        System.out.println("Client terminated");
    }

    public static void merge(int arr[], int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;
 
        int L[] = new int [n1];
        int R[] = new int [n2];
 
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];
 
        int i = 0, j = 0;
 
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }
 
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    
    public static void sort(int arr[], int l, int r)
    {
        if (l < r)
        {
            int m = (l+r)/2;
 
            sort(arr, l, m);
            sort(arr , m+1, r);

            merge(arr, l, m, r);
        }
    }
}