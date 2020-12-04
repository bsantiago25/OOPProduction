/**
 * This is the class that makes employees.
 * Takes in name, and password and makes a whole new
 * account for them.
 * @author Brandon Santiago
 */

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
      email = "user@oracleacademy.test";
    }
    if (isValidPassword(password) == true) {

      this.password = password;

    } else {
      this.password = "pw";
    }
  }

  /**
   * This method sets the new username for employee.
   * @param name takes name of employee
   */
  private void setUserName(String name) {
    char firstInitial = name.charAt(0);
    firstInitial = Character.toLowerCase(firstInitial);
    for (int i = 0; i < name.length(); i++) {
      if (name.charAt(i) == ' ') {
        this.userName = firstInitial + name.substring(i + 1).toLowerCase();

      }
    }
  }

  /**
   * Makes a new email for new employee.
   * @param name takes the name of employee.
   */

  private void setEmail(String name) {


    for (int i = 0; i < name.length(); i++) {
      if (name.charAt(i) == ' ') {
        this.email = name.substring(0, i).toLowerCase() + "."
            + name.substring(i + 1).toLowerCase() + "@oracleacademy.test";

      }
    }
  }

  /**
   * This method checks to see if there is a white space in
   * the employee name. This helps separate first and last names.
   * @param name name of employee
   * @return true if there is whitespace, false if there isn't.
   */
  private boolean checkName(String name) {
    for (int i = 0; i < name.length(); i++) {
      if (name.charAt(i) == ' ') {
        return true;
      }
    }
    return false;
  }

  /**
   * This method checks to see if the password is valid.
   * @param password takes employees password
   * @return true if the password is valid, false if it isn't.
   */
  private boolean isValidPassword(String password) {
    Pattern p = Pattern.compile("([a-z])([A-Z])(?=.*[\\W])");
    Matcher m = p.matcher(password);
    if (m.find()) {
      return true;
    } else {
      return false;
    }
  }


  /**
   * This prints all employees details
   * @return employee details.
   */
  @Override
  public String toString() {
    return "Employee Details" + '\n'
        + "Name : " + name + '\n'
        + "Username : " + userName + '\n'
        + "Email : " + email + '\n'
        + "Initial Password : " + password;

  }
}
