public class Employee {

  StringBuilder name;
  String username;
  String password;
  String email;

  public Employee(StringBuilder name, String password) {
    this.name = name;
    this.password = password;
    checkName(name);
  }

  private void setUsername()
  {
    this.username = username;
  }

  private boolean checkName(StringBuilder name)
  {

    return true;
  }

  private void setEmail()
  {
    this.email = email;
  }

  private boolean isValidPassword(String password)
  {
    return true;
  }



}
