package graphics.controllers;

public class NumParser {
  public static boolean tryParseInt(String num){
    try {
      int check = Integer.parseInt(num);
      return check > 0;
    }
    catch (NumberFormatException e){
      return(false);
    }
  }

  public static boolean tryParseDouble(String num){
    try {
      double check = Double.parseDouble(num);
      return check > 0;
    }
    catch (NumberFormatException e){
      return(false);
    }
  }
}
