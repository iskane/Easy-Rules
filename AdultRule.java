import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Rule(name = "Adult Rule")
public class AdultRule {
    private Person person;
    private RuleEngine ruleEngine;
    private String action; // Updated variable for the action string

    public AdultRule(Person person, RuleEngine ruleEngine, String action) {
        this.person = person;
        this.ruleEngine = ruleEngine;
        this.action = action;
    }

    @Condition
    public boolean when() {
        return ruleEngine.isAdult(person);
    }

    @Action
    public void then() {
        // Interpret and invoke the Java method call
        try {
            Method method = AdultRule.class.getMethod(action, Person.class);
            method.invoke(this, person);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // Example method invoked as the action
    public void calculateAge(Person person) {
        int ageInMonths = person.getAge() * 12;
        System.out.println(person.getName() + "'s age in months is: " + ageInMonths);
    }
}
