package ua.univ.lab8.Data.Enums;

import ua.univ.lab8.Commands.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public enum Commands {

    CHANGE_DISCOUNT_COMMAND(ChangeDiscountCommand.class),
   // CONNECT_CALL_COMMAND(ConnectCallCommand.class),
    LOGIN_COMMAND(LoginCommand.class),
   // MAKE_CALL_COMMAND(MakeCallCommand.class),
    PAY_INVOICE_COMMAND(PayInvoiceCommand.class),
    SIGN_UP_COMMAND(SignUpCommand.class),
    BUY_TOUR_COMMAND(BuyTourCommand.class),
    ABORT_INVOICE_COMMAND(AbortInvoiceCommand.class),
    FIND_TOUR_COMMAND(FindTourCommand.class),
    ADD_TOUR_COMMAND(AddTourCommand.class);


    private Constructor ctor = null;

    private Commands(Class type) {
        try {
            this.ctor = type.getConstructor();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public AgencyCommand getCommand()
            throws IllegalAccessException, InstantiationException, InvocationTargetException
    {
        return (AgencyCommand) ctor.newInstance();
    }

}