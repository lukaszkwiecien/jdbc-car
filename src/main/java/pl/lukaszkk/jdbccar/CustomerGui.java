package pl.lukaszkk.jdbccar;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class CustomerGui extends VerticalLayout {

    // trzy pola do wprowadzania danych
    private Text text;
    private TextField textFieldIdent;
    private TextField textFieldFirstName;
    private TextField textFieldLastName;
    private Button button;

    // prywatna instancja klasy Dao
    private CustomerDao customerDao;

    /// Autowired - obiekt dostarczany automatycznie przez Spring Boot
    @Autowired
    public CustomerGui(CustomerDao customerDao) {
        this.text = new Text("IBMPL.LKK.R2021. Strona służąca dodawaniu klientów do bazy danych RODO.");
        this.textFieldIdent = new TextField("identyfikator ");
        this.textFieldFirstName = new TextField("imię: ");
        this.textFieldLastName = new TextField("nazwisko: ");
        this.button = new Button("Dodaj !!!");

        // instancja obiektu przekazana Autowired
        this.customerDao = customerDao;

        // button listener
        // to do validation
        button.addClickListener(buttonClickEvent -> {
            Customer customer = new Customer(Long.parseLong(this.textFieldIdent.getValue()), textFieldFirstName.getValue(), this.textFieldLastName.getValue());
            customerDao.add(customer);

            // and notification
            Notification notification = new Notification("Klient został dodany do bazy", 3000);
            notification.open();
        });


        add(text, textFieldIdent, textFieldFirstName, textFieldLastName, button);

    }
}
