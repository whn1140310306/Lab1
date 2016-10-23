
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ʵ��һ������ʽ��.
 */
public class Project1 {

  /**
   * �����ж������ַ������ͣ�����ָ��.
   */
  private static final int ORDERONE = 1;
  /**
   * �����ж������ַ������ͣ���ָ��.
   */
  private static final int ORDERTWO = 2;
  /**
   * �����ж������ַ������ͣ��Ƿ�ָ��.
   */
  private static final int ORDERTHREE = 3;
  /**
   * �����ж������ַ������ͣ����ʽ.
   */
  private static final int ORDERFOUR = 4;
  /**
   * �����ж������ַ������ͣ�����ָ��.
   */
  private static char charEqualTemp;
  /**
   * ���������ж���ȵı���.
   */
  private static int intEqualTemp;

  /**
   * ���������ж���ȵı���.
   */
  public int order(final String stra) {
    int result = 3;
    if (stra.length() == 0) {
      result = 3;
    } else if (stra.substring(0, 1).equals("!")) {
      if (stra.length() >= 4 && stra.substring(1, 4).equals("d/d")) {
        result = 2;
      } else if (stra.length() >= 9 && stra.substring(1, 9).equals("simplify")) {
        result = 1;
      } else {
        result = 3;
      }
    } else {
      result = 4;
    }
    return result;
  }

  private char[] getSym(final String str) {
    // size�Ǳ�������ֵ�ĸ���
    int size = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == '+' || str.charAt(i) == '-') {
        size++;
      }
    }
    charEqualTemp = '-';
    if (str.charAt(0) != charEqualTemp) {
      size++;
    }

    char[] sym = new char[size];
    charEqualTemp = '-';
    if (str.charAt(0) != charEqualTemp) {
      sym[0] = '+';
    } else {
      sym[0] = '-';
    }

    int cnt = 1;
    for (int i = 1; i < str.length(); i++) {
      if (str.charAt(i) == '+' || str.charAt(i) == '-') {
        sym[cnt++] = str.charAt(i);// NOPMD
      }
    }

    return sym;
  }

  /**
   * ���ڻ������.
   */
  public void calculate(final String str, final String ord) { // NOPMD by liuyx on 15-10-11 ����9:15
    final String[] data = ord.substring(10).split("[=]|[ ]");

    String[] singleExp = str.split("[+]|[-]");
    /*
     * for (int i = 0;i < single_exp.length;i++){ for (int j = 0;j < data.length;j += 2)
     * single_exp[i] = single_exp[i].replaceAll(data[j], data[j+1]); }
     */

    final char[] symbol = getSym(str);

    if (symbol.length != singleExp.length) {
      System.arraycopy(singleExp, 1, singleExp, 0, singleExp.length - 1);
      // for (int i = 0; i < singleExp.length - 1; i++) {
      // singleExp[i] = singleExp[i + 1];
      // }
    }

    intEqualTemp = 1;
    final int singleExpNum = symbol.length;

    String[] var = new String[10];
    for (int i = 0; i < singleExpNum; i++) {

      float pro = 1;
      int cntforvar = 0;

      String[] single = singleExp[i].split("[*]");
      for (int j = 0; j < single.length; j++) {
        for (int k = 0; k < data.length; k += 2) {
          if (single[j].equals(data[k])) {
            single[j] = data[k + 1];
          }
        }
      }
      for (int j = 0; j < single.length; j++) {
        try {
          final float tmp = Float.parseFloat(single[j]);
          pro *= tmp;
        } catch (NumberFormatException exception) {
          var[cntforvar++] = single[j];
        }
      }
      String sing = "";

      if (cntforvar == 0) {
        sing += Float.toString(pro);
      } else if (pro != intEqualTemp) {
        sing += Float.toString(pro) + "*";
      }
      for (int j = 0; j < cntforvar; j++) {
        if (j == cntforvar - 1) {
          sing += var[j];
        } else {
          sing += var[j] + "*";
        }
      }
      singleExp[i] = sing;
    }

    /*
     * for (int i = 0;i < single_exp_num;i++) System.out.println(single_exp[i]);
     */

    String finalstr = "";
    float sum = 0;
    for (int i = 0; i < singleExpNum; i++) {
      try {
        final float tmp = Float.parseFloat(singleExp[i]);
        charEqualTemp = '+';
        if (symbol[i] == charEqualTemp) {
          sum += tmp;
        } else {
          sum -= tmp;
        }
      } catch (NumberFormatException exception) {
        finalstr += symbol[i] + singleExp[i];
      }
    }
    intEqualTemp = 0;
    if (sum != intEqualTemp) {
      finalstr += sum > 0 ? "+" + Float.toString(sum) : Float.toString(sum);
    }

    charEqualTemp = '+';
    if (finalstr.charAt(0) == charEqualTemp) {
      finalstr = finalstr.substring(1);
    }

    System.out.println(finalstr);
  }

  /**
   * �����󵼲�ֱ��������.
   */
  public void deri(final String str, final String ord) {
    final String var = ord.substring(4);
    String[] singleExp = str.split("[+]|[-]");
    final char[] symbol = getSym(str);

    // ��һ�������Ǽ��Ż�Ӻŵ�����
    if (symbol.length != singleExp.length) {
      // System.out.println("in");
      System.arraycopy(singleExp, 1, singleExp, 0, singleExp.length - 1);

      // for (int i = 0; i < singleExp.length - 1; i++) {
      // singleExp[i] = singleExp[i + 1];
      // }
    }
    boolean exist = false;
    final String[] all = str.split("[+]|[-]|[*]");
    for (int i = 0; i < all.length; i++) {
      // ��������ѭ����if����ִ�к�ֱ��break
      if (all[i].equals(var)) {
        exist = true;
      }
    }
    if (!exist) {
      System.out.println("No " + var);
      return;
    }
    final int singleExpNum = symbol.length;
    intEqualTemp = 1;
    final List<String> othervar = new ArrayList<String>();
    for (int i = 0; i < singleExpNum; i++) {
      boolean ext = false;
      final String[] data = singleExp[i].split("[*]");
      for (int j = 0; j < data.length; j++) {
        if (data[j].equals(var)) {
          ext = true;
        }
      }
      if (!ext) {
        singleExp[i] = "0";
      } else {
        int cnt = 0;
        // , index = 0;
        float factor = 1;
        // String othervar[] = new String[10];
        for (int j = 0; j < data.length; j++) {
          if (data[j].equals(var)) {
            cnt++;
          } else {
            try {
              final float tmp = Float.parseFloat(data[j]);
              factor *= tmp;
            } catch (NumberFormatException exception) {
              othervar.add(data[j]);
            }
          }
        }
        String sing = "";

        if (othervar.isEmpty() || cnt * factor != intEqualTemp) {
          sing += Float.toString(cnt * factor);
        }
        if (cnt > intEqualTemp) {
          sing += sing.isEmpty() ? var : "*" + var;
        }
        for (int j = 0; j < othervar.size(); j++) {
          sing += sing.isEmpty() ? othervar.get(j) : "*" + othervar.get(j);
        }
        singleExp[i] = sing;
      }
      othervar.clear();
    }
    charEqualTemp = '+';
    String finalstr = "";
    float sum = 0;
    for (int i = 0; i < singleExpNum; i++) {
      if (!singleExp[i].equals("0")) {
        try {
          final float tmp = Float.parseFloat(singleExp[i]);

          if (symbol[i] == charEqualTemp) {
            sum += tmp;
          } else {
            sum -= tmp;
          }
        } catch (NumberFormatException exception) {
          finalstr += symbol[i] + singleExp[i];
        }
      }
    }
    if (sum != 0) {
      finalstr += sum > 0 ? "+" + Float.toString(sum) : Float.toString(sum);
    }
    charEqualTemp = '+';
    if (finalstr.charAt(0) == charEqualTemp) {
      finalstr = finalstr.substring(1);
    }

    System.out.println(finalstr);
  }

  /**
   * ���ڼ�������Ƿ�����.
   */
  public boolean check(final String str) {
    int symNum = 0;
    int expNum = 0;
    boolean result = false;
    String strLower = str.toLowerCase();
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*'
          || str.charAt(i) >= '0' && str.charAt(i) <= '9'
          || strLower.charAt(i) >= 'a' && strLower.charAt(i) <= 'z') {
        if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*') {
          symNum++;
        }
      } else {
        result = false;
      }
    }
    charEqualTemp = '-';
    final String[] str2 = str.split("[+]|[-]|[*]");
    for (int i = 0; i < str2.length; i++) {
      if (str2[i] != null && !str2[i].isEmpty()) {
        expNum++;
        // System.out.println(sym_num + " " + exp_num);
      }
    }

    if (str.charAt(0) != charEqualTemp) {

      result = (symNum + 1 == expNum) ? true : false;
    } else {
      result = (symNum == expNum) ? true : false;
    }
    return result;
  }

  /**
   * �˺���Ϊִ�е������� .
   */

  public static void main(final String[] args) {
    String str = "";
    final Project1 poly = new Project1();
    final Scanner scan = new Scanner(System.in);

    int strLength = 4;
    while (true) {

      final String str1 = scan.nextLine();
      if (str1.length() >= strLength && str1.equals("exit")) {
        break;
      }
      // ����order����
      if (poly.order(str1) == ORDERFOUR) {
        str = str1.replaceAll("\\s*", "");
        // ����check����
        if (poly.check(str)) {
          System.out.println(str);
        } else {
          System.out.println("wrong expression");
          str = "";
        }
      } else if (poly.order(str1) == ORDERONE || poly.order(str1) == ORDERTWO) {
        if (poly.order(str1) == ORDERONE) {
          // System.out.println(str+" "+str1);
          // ����calculate����
          poly.calculate(str, str1);
        } else if (poly.order(str1) == ORDERTWO) {
          // ����deri����
          poly.deri(str, str1);
        }
      } else if (poly.order(str1) == ORDERTHREE) {
        System.out.println("Wrong order");
        // sc.close();
      }
    }
    scan.close();

  }

}
