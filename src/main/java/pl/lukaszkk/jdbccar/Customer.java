package pl.lukaszkk.jdbccar;


public class Customer {

  private long ident;
  private String firstName;
  private String lastName;

  public Customer(long ident, String firstName, String lastName) {
    this.ident = ident;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public long getIdent() {
    return ident;
  }

  public void setIdent(long ident) {
    this.ident = ident;
  }


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

}
