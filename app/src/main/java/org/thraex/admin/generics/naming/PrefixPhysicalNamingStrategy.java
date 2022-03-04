package org.thraex.admin.generics.naming;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author 鬼王
 * @date 2021/07/22 16:11
 */
@Component
public class PrefixPhysicalNamingStrategy extends SpringPhysicalNamingStrategy {

    @Value("${spring.jpa.hibernate.naming.prefix:}")
    private String prefix;

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        String lower = Optional.of(prefix).map(String::toLowerCase).get();
        Identifier identifier = Identifier.toIdentifier(String.format("%s%s", lower, name.getText()));

        return super.toPhysicalTableName(identifier, context);
    }

}
