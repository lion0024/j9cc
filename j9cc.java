import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Token{
  String ty;
  String val;
}

class j9cc{
  private static final String TK_NUM = "TK_NUM";
  private static final String TK_EOF = "TK_EOF";

  public static Token[] tokenize(String str){
    Token[] tokens = new Token[100];
    
    for (int i = 0; i < tokens.length; i++){
      tokens[i] = new Token();
    }
    
    int array_i = 0;
    int i = 0;
    while(i < str.length()){
      if (Character.isWhitespace(str.charAt(i))){
        i++;
        continue;
      }

      if (str.charAt(i) == '+' || str.charAt(i) == '-'){
        tokens[array_i].ty = String.valueOf(str.charAt(i));
        array_i++;
        i++;
        continue;
      }

      if (Character.isDigit(str.charAt(i))){
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(i));
        while (i+1 < str.length() && Character.isDigit(str.charAt(i+1))){
          i++;
          sb.append(str.charAt(i));
        }
        tokens[array_i].ty = TK_NUM;
        tokens[array_i].val = sb.toString();
        array_i++;
        i++;
        continue;
      }

      System.err.println("cannot tokenize: " + str.charAt(i));
      System.exit(1);
    }
    tokens[array_i].ty = TK_EOF;
    return tokens;
  }

  public static void main(String[] args){

    if (args.length != 1){
      System.err.println("Usage: j9cc <code>");
      return;
    }

    Token[] tokens = tokenize(args[0]);

    System.out.println(".intel_syntax noprefix");
    System.out.println(".global main");
    System.out.println("main:");

    if (!(TK_NUM.equals(tokens[0].ty))){
      System.err.println("unexpected token: " + tokens[0].ty);
      System.exit(1);
    }

    System.out.println("  mov rax, " + tokens[0].val);

    int i = 1;
    while (tokens[i].ty != TK_EOF){
      if ("+".equals(tokens[i].ty)){
        i++;
        if(tokens[i].ty != TK_NUM){
          System.err.println("unexpected token: " + tokens[i].ty);
          System.exit(1);
        }
        System.out.println("  add rax, " + tokens[i].val);
        i++;
        continue;
      }

      if ("-".equals(tokens[i].ty)){
        i++;
        if(tokens[i].ty != TK_NUM){
          System.err.println("unexpected token: " + tokens[i].ty);
          System.exit(1);
        }
        System.out.println("  sub rax, " + tokens[i].val);
        i++;
        continue;
      }

      System.err.println("unexpected token: " + tokens[i].ty);
      System.exit(1);
    }

    System.out.println("  ret");
    return;
  }
}
