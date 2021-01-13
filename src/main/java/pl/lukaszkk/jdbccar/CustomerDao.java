package pl.lukaszkk.jdbccar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CustomerDao {

    //Config config = new Config();
    private JdbcTemplate jdbcTemplate;

    //// @Autowired sprawia ze kod jest uruchamiany - automatycznie tworzy tą klasę, oraz wykorzystuje Bean getJdbcTemplate
    //// dla konstruktura
    @Autowired
    public CustomerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(Customer customer)
    {
        String sql = "INSERT INTO STUDENT01.customersgui (ident, first_name, last_name) VALUES (?,?,?)";

        jdbcTemplate.update(sql, new Object[]{
                customer.getIdent(),
                customer.getFirstName(),
                customer.getLastName()
        });
    }

    public List<Map<String, Object>> readByImie(String imie)
    {
        String sql = "SELECT ident, first_name, last_name FROM STUDENT01.CUSTOMERSGUI WHERE FIRST_NAME = ?";

        return jdbcTemplate.queryForList(sql, new Object[]{imie});
    }

    public List<Customer> readAll()
    {
        String sql = "SELECT ident, first_name, last_name FROM STUDENT01.CUSTOMERSGUI";

        //return jdbcTemplate.queryForList(sql);
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Customer(
                                                rs.getLong("ident"),
                                                rs.getString("first_name"),
                                                rs.getString("last_name")
                                            )
                );
    }


    @EventListener(ApplicationReadyEvent.class)
    public void dbInit()
    {
        System.out.println("dbInit - uruchamiamy");
//        add(new Customer(11, "Marta", "Karczewska-Kwiecień"));
//        add(new Customer(12, "Marta", "Karczewska-Kwiecień"));
//        add(new Customer(13, "Marta", "Karczewska-Kwiecień"));
//        add(new Customer(14, "Marta", "Karczewska-Kwiecień"));
//        add(new Customer(15, "Marta", "Karczewska-Kwiecień"));

        // usuniecie tabeli
        String sql = "DROP TABLE STUDENT01.customersgui";
//        jdbcTemplate.update(sql);

        // utworzenie tabeli
        sql = "CREATE TABLE STUDENT01.customersgui(\n" +
                "    ident INT,\n" +
                "    first_name VARCHAR(255),\n" +
                "    last_name VARCHAR(255)\n" +
                ")";
  //      jdbcTemplate.update(sql);

        // pierwszy insert
//// to nie dziala
//        sql="INSERT INTO STUDENT01.customersgui (ident, first_name, last_name) VALUES ('2','Jan','Kwiecien')\n"+
//            "INSERT INTO STUDENT01.customersgui (ident, first_name, last_name) VALUES ('3','Antoni','Kwiecien')";
        sql="INSERT INTO STUDENT01.customersgui (ident, first_name, last_name) VALUES ('2','Jan','Kwiecien')";
//        jdbcTemplate.update(sql);

        sql="INSERT INTO STUDENT01.customersgui (ident, first_name, last_name) VALUES ('3','Antoni','Kwiecien')";
//        jdbcTemplate.update(sql);

        sql="INSERT INTO STUDENT01.customersgui (ident, first_name, last_name) VALUES ('4','Jakub','Kwiecien')";
//        jdbcTemplate.update(sql);

        sql="INSERT INTO STUDENT01.customersgui (ident, first_name, last_name) VALUES ('5','Marta','Kwiecien')";
//        jdbcTemplate.update(sql);
    }
}
