﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace WcfService1
{
    // NOTA: puede usar el comando "Rename" del men� "Refactorizar" para cambiar el nombre de clase "Service1" en el c�digo, en svc y en el archivo de configuraci�n.
    // NOTE: para iniciar el Cliente de prueba WCF para probar este servicio, seleccione Service1.svc o Service1.svc.cs en el Explorador de soluciones e inicie la depuraci�n.


    public class WSCalculadora : IWSCalculadora
    {
        public string dividir(string p1, string p2)
        {
            int resultado = 0;
            int param1 = Int32.Parse(p1);
            int param2 = Int32.Parse(p2);

            resultado = param1 / param2;

            return resultado.ToString();
        }

        public string exponente(string p1, string p2)
        {
            double resultado;
            double param1 = Double.Parse(p1);
            double param2 = Double.Parse(p2);
            resultado = Math.Pow(param1, param2);

            return resultado.ToString();

        }

        public string multiplicar(string p1, string b2)
        {
            int resultado = 0;
            int param1 = Int32.Parse(p1);
            int param2 = Int32.Parse(b2);

            resultado = param1 * param2;

            return resultado.ToString();
        }

        public string raiz(string p1)
        {
            double resultado;
            double param1 = Double.Parse(p1);
            resultado = Math.Sqrt(param1);

            return resultado.ToString();
        }

        public string resta(string p1, string b2)
        {
            int resultado = 0;
            int param1 = Int32.Parse(p1);
            int param2 = Int32.Parse(b2);

            resultado = param1 - param2;

            return resultado.ToString();
        }

        public string suma(string p1, string p2)
        {
            int resultado = 0;
            int param1 = Int32.Parse(p1);
            int param2 = Int32.Parse(p2);

            resultado = param1 + param2;

            return resultado.ToString();
        }
    }
}

