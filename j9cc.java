import java.util.regex.Pattern;
import java.util.regex.Matcher;

class j9cc{
  public static void main(String[] args){
    if (args.length != 1){
      System.err.println("Usage: j9cc <code>");
      return;
    }

    String str = args[0];
    String[] num = str.split("[+-]");
    String[] op = str.split("[0-9]+");

    System.out.println(".intel_syntax noprefix");
    System.out.println(".global main");
    System.out.println("main:");
    System.out.println("  mov rax, " + num[0]);
    
    // op配列の0に空白が入ってしまっているため要修正
    int num_i = 0;
    for (int i = 1; i < op.length; i++){
      if (op[i].equals("+")){
        num_i++;
        System.out.println("  add rax, " + num[num_i]);
        continue;
      }

      if (op[i].equals("-")){
        num_i++;
        System.out.println("  sub rax, " + num[num_i]);
        continue;
      }

      System.err.println("unexpected character: " + op[i]);
      return;
    }

    System.out.println("  ret");
    return;
  }
}
