class j9cc{
  public static void main(String[] args){
    if (args.length != 1){
      System.err.println("Usage: j9cc <code>");
      return;
    }

    System.out.println(".intel_syntax noprefix");
    System.out.println(".global main");
    System.out.println("main:");
    System.out.println("  mov rax, " + args[0]);
    System.out.println("  ret");
    return;
  }
}
