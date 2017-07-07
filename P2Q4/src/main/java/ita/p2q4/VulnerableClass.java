package ita.p2q4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/** Classe VulnerableClass. */
public class VulnerableClass {
  /** Método principal. */
  public void vulnerableMethod(String filename) throws Exception {
    Pattern pattern = Pattern.compile("[^A-Za-z0-9._]");
    Matcher matcher = pattern.matcher(filename);
    if (matcher.find()) {
      throw new Exception("Bad file name.");
    }
    Path root = Paths.get(System.getProperty("user.dir"),"ArquivosDeTeste");
    Path file = root.resolve(filename);
    boolean repeat = true; 
    Scanner console = new Scanner(System.in);
    while (repeat) {
      System.out.print("Digite a operacao desejada para realizar "
          + "no arquivo <R para ler um arquivo, "
          + "W para escrever em um arquivo>? ");
      String opr = console.nextLine();
      if (opr.equals("R")) {
        try {
          BufferedReader br = new BufferedReader(new FileReader(file.toFile()));
          String currentLine;
          while ((currentLine = br.readLine()) != null) {
            System.out.println(currentLine);
          }
          br.close();
        } catch (FileNotFoundException e) {
          console.close();
          throw(e);
        } catch (IOException e) {
          console.close();
          throw(e);
        } 
      } else if (opr.equals("W")) {
        try {
          BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file.toFile(),true));
          System.out.println("Escreva algo: ");
          String linha = console.nextLine();
          buffWrite.append(linha);
          buffWrite.newLine();
          buffWrite.close();
        } catch (IOException e) {
          console.close();
          throw(e);
        }
      }
      System.out.print("Deseja continuar(s/n)?");
      String again = console.nextLine();
      if (again.equals("s")) {
        repeat = true;
      } else if (again.equals("n")) {
        repeat = false;
      } else {
        console.close();
        throw new Exception("Invalid answer.");
      }
    }
    console.close();
  }
}