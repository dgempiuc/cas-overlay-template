import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

@Component
public class MyHandler {

    public String validateUsername(RegisterModel registerModel, MessageContext messageContext) {
        String transitionValue = "success";

        String usr = registerModel.getUsername();
        if(usr.length() < 3) {
            messageContext.addMessage(
                    new MessageBuilder().error().source("username")
                            .defaultText(
                                    String.format("Username length must be higher than 3: %s (%d)", usr, usr.length())
                            ).build()
            );
            transitionValue = "failure";
        }

        return transitionValue;
    }

    public String validatePassword(RegisterModel registerModel, MessageContext messageContext) {
        String transitionValue = "success";

        if(registerModel.getPassword().isEmpty()) {
            messageContext.addMessage(
                    new MessageBuilder().error().source("password")
                            .defaultText(
                                    "Password cannot be null."
                            ).build()
            );
            transitionValue = "failure";
        }

        if(!registerModel.getPassword().equals(registerModel.getConfirm())) {
            messageContext.addMessage(
                    new MessageBuilder().error().source("password")
                            .defaultText(
                                    "Passwords are not equal to each other. Try again."
                            ).build()
            );
            transitionValue = "failure";
        }

        return transitionValue;
    }


}
