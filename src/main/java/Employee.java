import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
  final String name;
  String userName;
  final String password;
  String email;

  public Employee(String name, String password) {
    this.name = name;
    if (checkName(name) == true) {
      setUserName(name);
      setEmail(name);
    } else {
      userName = "default";
      email = "user@oracleacademy.Test";
    }
    if (isValidPassword(password) == true) {

      this.password = password;

    } else {
      this.password = "pw";
    }
  }

  private void setUserName(String name) {
    char firstInitial = name.charAt(0);
    firstInitial = Character.toLowerCase(firstInitial);
    for (int i = 0; i < name.length(); i++) {
      if (name.charAt(i) == ' ') {
        this.userName = firstInitial + name.substring(i + 1).toLowerCase();

      }
    }
  }

  private void setEmail(String name) {


    for (int i = 0; i < name.length(); i++) {
      if (name.charAt(i) == ' ') {
        this.email = name.substring(0, i).toLowerCase() + "."
            + name.substring(i + 1).toLowerCase() + "@oracleacademy.Test";

      }
    }
  }

  private boolean checkName(String name) {
    for (int i = 0; i < name.length(); i++) {
      if (name.charAt(i) == ' ') {
        return true;
      }
    }
    return false;
  }

  private boolean isValidPassword(String password) {
    Pattern p = Pattern.compile("([a-z])([A-Z])(?=.*[\\W])");
    Matcher m = p.matcher(password);
    if (m.find()) {
      return true;
    } else {
      return false;
    }
  }




  @Override
  public String toString() {
    return "Employee Details" + '\n'
        + "Name : " + name + '\n'
        + "Username : " + userName + '\n'
        + "Email : " + email + '\n'
        + "Initial Password : " + password;

  }
}
