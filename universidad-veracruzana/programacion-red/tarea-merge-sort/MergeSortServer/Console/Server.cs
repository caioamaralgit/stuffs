using System;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace MergeSortServer
{
    class Server
    {
        private const int ARR_SIZE = 10000;
        private const int CLIENT_NUM = 2;
        private const int PORT = 5000;

        private static int connectedClients = 0;
        private static int[] array = new int[ARR_SIZE];
        private static TcpListener _server;

        static void Main(string[] args)
        {
            Console.WriteLine("* Starting Merge Sort Server");

            CreateArray();

            _server = new TcpListener(PORT);
            _server.Start();
            Console.WriteLine("\n* Server started!");

            while (connectedClients < CLIENT_NUM)
            {
                TcpClient newClient = _server.AcceptTcpClient();
                Console.WriteLine("* Client connected!");
                connectedClients++;

                Thread t = new Thread(() => HandleClient(newClient, connectedClients));
                t.Start();
            }

            MergeSort(array, 0, array.Length - 1);

            Console.WriteLine("Array sorted!");

            for (int i = 0; i < array.Length; i++)
            {
                Console.WriteLine("Array[" + i + "] = " + array[i]);
            }
        }

        static void CreateArray()
        {
            Console.WriteLine("* Generating random values between 0 and 1000...");

            Random r = new Random();

            Console.WriteLine("* Original array:\n*");

            for (int i = 0; i < array.Length; i++)
            {
                array[i] = r.Next(0, 1000);
                Console.WriteLine("** Array[" + i + "] = " + array[i]);
            }
        }

        public static void HandleClient(object obj, int clientNum)
        {
            Console.WriteLine("* Client started!");
            TcpClient client = (TcpClient)obj;
            
            StreamWriter sWriter = new StreamWriter(client.GetStream(), Encoding.UTF8);
            StreamReader sReader = new StreamReader(client.GetStream(), Encoding.UTF8);

            int arraySize = (array.Length / CLIENT_NUM) / 2;
            int startIndex = clientNum * (array.Length / CLIENT_NUM) - (array.Length / CLIENT_NUM);
            int limitIndex = startIndex + arraySize;

            StringBuilder rawArray = new StringBuilder();

            while (startIndex < limitIndex)
            {
                if (startIndex > clientNum * (array.Length / CLIENT_NUM) - (array.Length / CLIENT_NUM)) rawArray.Append(",");
                rawArray.Append(array[startIndex]);

                startIndex++;
            }

            Console.WriteLine("* Senting array to client #" + clientNum + "!");

            sWriter.WriteLine(rawArray.ToString());

            Console.WriteLine("* Array sent");

            Thread t = new Thread(() => MergeSort(array, startIndex, startIndex + arraySize));
            t.Start();

            startIndex -= arraySize;

            String response = sReader.ReadLine();
            String[] newArray = response.Split(",");

            for (int i = 0; i < newArray.Length; i++)
            {
                array[i + startIndex] = Convert.ToInt32(newArray[i]);
            }

            MergeSort(array, startIndex, startIndex + arraySize * 2);
        }

        public static void MergeSort(int[] input, int low, int high)
        {
            if (low < high)
            {
                int middle = (low / 2) + (high / 2);
                MergeSort(input, low, middle);
                MergeSort(input, middle + 1, high);
                Merge(input, low, middle, high);
            }
        }

        private static void Merge(int[] input, int low, int middle, int high)
        {

            int left = low;
            int right = middle + 1;
            int[] tmp = new int[(high - low) + 1];
            int tmpIndex = 0;

            while ((left <= middle) && (right <= high))
            {
                if (input[left] < input[right])
                {
                    tmp[tmpIndex] = input[left];
                    left = left + 1;
                }
                else
                {
                    tmp[tmpIndex] = input[right];
                    right = right + 1;
                }
                tmpIndex = tmpIndex + 1;
            }

            if (left <= middle)
            {
                while (left <= middle)
                {
                    tmp[tmpIndex] = input[left];
                    left = left + 1;
                    tmpIndex = tmpIndex + 1;
                }
            }

            if (right <= high)
            {
                while (right <= high)
                {
                    tmp[tmpIndex] = input[right];
                    right = right + 1;
                    tmpIndex = tmpIndex + 1;
                }
            }

            for (int i = 0; i < tmp.Length; i++)
            {
                input[low + i] = tmp[i];
            }

        }
    }
}
