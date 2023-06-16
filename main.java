import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        // Load the properties file
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("path/to/rules.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a person
        Person person = new Person();
        person.setName("John");
        person.setAge(25);

        // Create the rule engine
        RulesEngine rulesEngine = RulesEngineBuilder.aNewRulesEngine().build();

        // Create an instance of the rule based on the properties
        String ruleName = properties.getProperty("rule1.name");
        String condition = properties.getProperty("rule1.condition");
        String action = properties.getProperty("rule1.action");

        // Dynamically create the rule instance
        AdultRule adultRule = new AdultRule(person, new RuleEngine(), action);
        adultRule.setName(ruleName);
        adultRule.setCondition(condition);

        // Register the rule with the engine
        rulesEngine.registerRule(adultRule);

        // Fire the rules
        rulesEngine.fireRules();
    }
}
