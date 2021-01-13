package pl.lukaszkk.jdbccar;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import elemental.json.impl.JsonUtil;
import org.apache.catalina.valves.JsonErrorReportValve;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Route
public class ListGui extends VerticalLayout {

    // imie do wyszukania
    private TextField textFieldImie;

    // elementy gui
    private TextArea textArea;
    private Button button;

    private CustomerDao customerDao;

    @Autowired
    public ListGui(CustomerDao customerDao) {

        this.textFieldImie = new TextField("Wprowadz imię do wyszukania w bazie klientów: ");
        this.textArea = new TextArea("Wyniki zapytania");
        this.button = new Button("Pokaż wynik wyszukiwania");

        button.addClickListener(buttonClickEvent -> {
            List<Map<String, Object>> customersByImie = customerDao.readByImie(this.textFieldImie.getValue());
            textArea.setValue(customersByImie.toString());

            // and notification
            Notification notification = new Notification("Zapytanie zostało wykonane", 3000);
            notification.open();
        });

        this.customerDao = customerDao;

        add(textFieldImie, textArea, button);

    }



}
