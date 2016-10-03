package csmart.api.config.db;

import org.jooq.ExecuteContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultExecuteListener;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.jdbc.support.SQLStateSQLExceptionTranslator;

/**
 * <pre>
 * 1. Create a JOOQToSpringExceptionTransformer class which extends the DefaultExecuteListener class.
 * The DefaultExecuteListener class is the public default implementation of the ExecuteListener
 * interface which provides listener methods for different life cycle events of a single query execution.
 * 2. Override the exception(ExecuteContext ctx) method of the DefaultExecuteListener class.
 * This method is called if an exception is thrown at any moment of the execution life cycle.
 * Implement this method by following these steps:
 *   a. Get a SQLDialect object from the jOOQ configuration.
 *   b. Create an object which implements the SQLExceptionTranslator interface by following these rules:
 *       i. If the configured SQL dialect is found, create a new SQLErrorCodeSQLExceptionTranslator
 *       object and pass the name of the SQL dialect as a constructor argument.
 *       This class “selects” the right DataAccessException by analyzing vendor specific error codes.
 *
 *       ii. If the SQL dialect isn’t found, create a new SQLStateSQLExceptionTranslator object.
 *       This class “selects” the right DataAccessException by analyzing the SQL state stored to the SQLException.
 * 3. Create the DataAccessException object by using the created SQLExceptionTranslator object.
 * 4. Pass the thrown DataAccessException forward to the ExecuteContext object given as a method argument.
 * </pre>
 */

public class JOOQToSpringExceptionTransformer extends DefaultExecuteListener {

    @Override
    public void exception(ExecuteContext ctx) {
        if(ctx.sqlException() == null) return;

        SQLDialect dialect = ctx.configuration().dialect();
        SQLExceptionTranslator translator = (dialect != null)
                ? new SQLErrorCodeSQLExceptionTranslator(dialect.name())
                : new SQLStateSQLExceptionTranslator();

        ctx.exception(translator.translate("jOOQ", ctx.sql(), ctx.sqlException()));
    }
}
