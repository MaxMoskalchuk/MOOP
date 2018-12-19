
package ua.univ.lab8.Commands;

import ua.univ.lab8.Data.Models.User;

public interface SignUpLoginCommand extends AgencyCommand {
    User getUser();
}