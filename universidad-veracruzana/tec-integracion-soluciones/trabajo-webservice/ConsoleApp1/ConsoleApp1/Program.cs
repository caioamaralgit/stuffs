using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int dolar;
            Console.Write("Digite el valor (or 'quit' si quieres salir): ");
            String text = Console.ReadLine();

            while (text != "quit")
            {
                dolar = Convert.ToInt32(text);
                
                String url = "http://10.50.13.46/php-service/service.php?d=" + dolar;

                WebRequest solicitud = WebRequest.Create(url);
                solicitud.Method = "GET";

                WebResponse respuesta = solicitud.GetResponse();
                Stream ReceiveStream = respuesta.GetResponseStream();

                Encoding encode = System.Text.Encoding.GetEncoding("utf-8");
                StreamReader leerSt = new StreamReader(ReceiveStream, encode);
                
                Console.WriteLine("Respuesta: " + leerSt.ReadLine() + "\n");

                leerSt.Close();

                Console.Write("Digite el valor (or 'quit' si quieres salir): ");
                text = Console.ReadLine();
            }
        }
    }
}
