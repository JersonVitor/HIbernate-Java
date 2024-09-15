package com.jcg.hibernate.crud.operations.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Util {
    public static Scanner sc = new Scanner(System.in);
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public static int ReadInt(String str, int inf, int sup){
        String s;
        int resp = 0;
        boolean erro;
        do {
            System.out.print(str);
           try {
              s = sc.nextLine();
              resp = Integer.parseInt(s);
              erro = resp>sup || resp<inf;
              if (erro) System.out.println("\n-Valor Inválido-");
           }catch (NumberFormatException e){
               System.out.println("\n-Entrada Invalida!!");
               erro = true;
           }

        }while (erro);
        return resp;
    }

    public static String ReadString(String str){
        String s;
        boolean erro;
        do {
            System.out.print(str);
                s = sc.nextLine();
                erro = s == null || s.length() < 1;
            if (erro) System.out.println("\n-Não é aceita entrada vazia!!");
        }while (erro);
        return s;
    }

    public static Date ReadDate(String str){
        String s;
        Date resp = new Date();
        boolean erro;
        do {
            erro= false;
            System.out.print(str);
            try {
                s = sc.nextLine();
                resp = sdf.parse(s);
            }catch (ParseException e) {
                erro = true;
            }
            if (erro) System.out.println("\n-Valor Inválido!!");
        }while (erro);
        return resp;
    }

    public static double ReadDouble(String str, int inf, int sup) {
        String s;
        double resp = 0;
        boolean erro;
        do {
            System.out.print(str);
            try {
                s = sc.nextLine();
                resp = Double.parseDouble(s);
                erro = resp>sup || resp<inf;
            }catch (NumberFormatException e){
                System.out.println("\n-Entrada Invalida!!");
                erro = true;
            }
            if (erro) System.out.println("\n-Valor Inválido-");
        }while (erro);
        return resp;
    }
    public static boolean ReadBoolean(String str){
        int op = ReadInt(str,0,1);
        return op == 1;
    }


}
